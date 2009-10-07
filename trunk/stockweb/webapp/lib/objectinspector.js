	var widgetFocus = "";
	// ========================= Object Inspector =================================
	function log(text){
//		document.getElementById("result").innerHTML += "<br>" + text;
	}
	//Atualiza a lista de widgets no Object Inspector cada vez que é criado um novo widget(Window)
	function listWidgetsOI(event){
		 var combobox = document.getElementById("comboWidgets");
		 combobox.options.length=0
		 var mapWidget = widget.widgets();
		 for(var x=0; x<mapWidget.size(); x++){
		 	combobox.options[combobox.options.length] = new Option(mapWidget.elementAt(x) + ": " + mapWidget.listKeys()[x], mapWidget.listKeys()[x]);
		 }
	}
	
	var cmdProperties = new Map();
	cmdProperties.put("id", function (key, widgetid){
								return "&nbsp;ID," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById(widgetid).getAttribute("id") + "' readonly>";
							});
	cmdProperties.put("width", function (key, widgetid){
								return "&nbsp;Width," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById(widgetid).style.width + "' " +
									    " onchange=\"listener.fire('onchange|width|"+document.getElementById(widgetid).getAttribute("id")+"');\"> ";
							});
	cmdProperties.put("height", function (key, widgetid){
								return "&nbsp;Height," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById(widgetid).style.height + "' " +
									    " onchange=\"listener.fire('onchange|height|"+document.getElementById(widgetid).getAttribute("id")+"');\"> ";
							});
	cmdProperties.put("left", function (key, widgetid){
								return "&nbsp;Left," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById(widgetid).style.left + "' " +
									    " onchange=\"listener.fire('onchange|left|"+document.getElementById(widgetid).getAttribute("id")+"');\"> ";
							});
	cmdProperties.put("top", function (key, widgetid){
								return "&nbsp;Top," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById(widgetid).style.top + "' " +
									    " onchange=\"listener.fire('onchange|top|"+document.getElementById(widgetid).getAttribute("id")+"');\"> ";
							});
	
	cmdProperties.put("innerHTML", function (key, widgetid){
								return "&nbsp;innerHTML," + 
									   "<input type='text' style='width:100px; height:16px;' id='edt"+document.getElementById(widgetid).getAttribute("id")+"_"+key+"' value='" + document.getElementById('content'+widgetid).innerHTML + "' " +
									    " onchange=\"listener.fire('onchange|innerHTML|"+document.getElementById(widgetid).getAttribute("id")+"');\"> ";
									   
							});
	
	function firePropertiesOI(event){
		if(event != "changeProperties") return;
		loadPropertiesOI(document.getElementById("comboWidgets")[document.getElementById("comboWidgets").selectedIndex].value);
	}
	function fireWidgetsOI(event){
		if(event != "newWidget") return;
		listWidgetsOI(event);
	}	
	
	function fireEvent(param){
		if(param.indexOf("|")!=-1)
			param = param.split("|");

		if ((param[0] == "resize")&&(widgetFocus!=null) && (param[1] == document.getElementById("googlemaps").parentNode.id)) {
			document.getElementById("googlemaps").style.width = widgetFocus.style.width;
			document.getElementById("googlemaps").style.height = (widgetFocus.style.height.replace("px","") - 25) + "px";
			map.checkResize();
		}
		
		if (param[0] == "mousedown") {
			// Atualiza o combobox com os nomes das janelas criadas
			var combobox = document.getElementById("comboWidgets");
			for (var x = 0; x < combobox.options.length; x++) {
				if (combobox.options[x].value == param[1]) 
					combobox.options[x].selected = "true";
				firePropertiesOI("changeProperties");
			}
		}
		
		if(param[0] == "mousemove"){ 
			widgetFocus = param[1];
		}

		if (param[0] == "onchange") {
			var id = param[2].substring(6);
			if(param[1] == "innerHTML"){
				document.getElementById("content"+param[2]).innerHTML = document.getElementById("edt"+param[2]+"_"+param[1]).value;
			}else{
				document.getElementById(param[2]).style[param[1]] = document.getElementById("edt"+param[2]+"_"+param[1]).value;
			}
	        buildDesign.renderize(param[2], "resize"+id, "fixmodify"+id, "menubar"+id, "statusbar"+id);
		}		
		
	}	
	
	function attachWidget(widgetId, windowId){
		try{
		id = windowId.id.substring(6);
		document.getElementById(widgetId).style.visibility = "visible";
		windowId.appendChild(document.getElementById(widgetId));
//        buildDesign.renderize("region"+id, "resize"+id, "fixmodify"+id, "menubar"+id, "statusbar"+id);
		}catch(e){ alert("Este componente ainda nao foi implementado");}
	}
	
	function loadPropertiesOI(widgetid){
		var attributes = "id,width,height,left,top,innerHTML".split(","); 
		var objParams = document.getElementById("OIParams");
		objParams.innerHTML = "";
		elemTB = document.createElement("table");
		elemTBody = document.createElement("tbody");

		for(var x=0; x<attributes.length; x++){
				var token = cmdProperties.get(attributes[x]).call("", attributes[x], widgetid).split(",");
			
				 elemTR = document.createElement("tr");
				 elemTR.style["backgroundColor"] = "#CCCCCC";
				 elemTR.style["height"] = "10px";
				 elemTD = document.createElement("td");
				 elemTD.style["fontSize"] = "11px";
				 elemTD.style["width"] = "100px";
				 elemTR.appendChild(elemTD);
				 
				 elemTD.innerHTML = token[0];
				 elemTR.appendChild(elemTD);
				 elemTD = document.createElement("td");
				 elemTD.style["width"] = "100px";
				 elemTD.innerHTML = token[1];
				 elemTR.appendChild(elemTD);
			
				 elemTBody.appendChild(elemTR);
		 }
		 elemTB.appendChild(elemTBody);
		 objParams.appendChild(elemTB);
	}
	
	// Criar um efeito drag&drop apòs selecionar um item do Toolbox
	function loadEffectItemSelected(){
		var tmpWidget = "<div id='tmpWidget' style='visibility:hidden;top:0px;left:0px;'><img id='imgWidgetTmp' src='img/chart_organisation.gif'></div>";
		if(document.getElementById("tmpWidget")==null)
			document.getElementById("schema").innerHTML += tmpWidget;

		var rows = document.getElementById("components").getElementsByTagName("td");
        for (var i = 1; i < rows.length; i++) {
			var idItem = rows[i].parentNode.getElementsByTagName("td")[0].id;
			document.getElementById(idItem).onclick = function(e){
				showIconWidgetSelected(this.parentNode.getElementsByTagName("img")[0].getAttribute("comp"), this.parentNode.getElementsByTagName("img")[0].src);
			}
		}
		
	}
	
	function showIconWidgetSelected(widgetId, srcImg){
			document.getElementById("imgWidgetTmp").src = srcImg;
			dragdrop.getMouseCoord();
			var objWidgetLayer = document.getElementById("tmpWidget");
			objWidgetLayer.style.left = (dragdrop.getMouseX() + 6) + "px";
			objWidgetLayer.style.top = (dragdrop.getMouseY() + 6) + "px";
			objWidgetLayer.style.visibility = "visible";

			dragdrop.addEvent(document.getElementById('tmpWidget'), 'move', function(){
				document.onmouseup = function(){
					document.getElementById('tmpWidget').style.visibility = "hidden";
					if (widgetFocus.id.indexOf("window") != -1)	attachWidget(widgetId, widgetFocus);
					document.onmouseup = null;
				};
			});
	}

	// ========================================================================	

	// Object Inspector - Add properties dynamic
	listener.subscribe(fireWidgetsOI);
	listener.subscribe(firePropertiesOI);
	listener.subscribe(fireEvent);
