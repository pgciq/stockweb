// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

// Simula a funcionalidade do HashMap em Java
Map = function(){
    var keys = new Array();
    var values = new Array();
    this.get = function(key){
        return values[keys.indexOf(key)];
    }
    this.elementAt = function(index){
        return values[index];
    }
    this.listKeys = function(){
        return keys;
    }
    this.put = function(key, value){
        var index = keys.indexOf(key);
        
        if (index >= 0) {
            values[index] = value;
            return;
        }
        keys[keys.length] = key;
        values[values.length] = value;
    }
    this.size = function(){
        return keys.length;
    }
    
    // tipo de formato: "str/str", "str/obj"
    this.toString = function(format){
        var delim = "";
        var txt = "";
        for (var i = 0; i < keys.length; i++) {
            if (format == "str/obj") 
                txt += delim + "\"" + keys[i] + "\":" + values[i];
            else 
                txt += delim + "\"" + keys[i] + ":" + values[i] + "\"";
            
            delim = ",";
        }
        return txt;
    }
    //bugfix - only IE not support indexOf
   
    
}


BuilderDesigner = function(){
	
    this.create = function(json){
// 		widgets.put(json.main[i]);   
        var nodeRifer = document.getElementById("schema"); // Deve passar via parametro o node de referencia
        var i = 0;
        var html = "";
        while (json.main[i] != null) {
            var key, value;
            var newdiv = document.createElement(json.main[i].layout[0].tag);
            newdiv.setAttribute("id", json.main[i].layout[0].id);
            
            try {
                for (var x = 0; x < json.main[i].layout[0].style.length; x++) {
                    txtStyle = json.main[i].layout[0].style[x];
                    key = txtStyle.substring(0, txtStyle.indexOf(":"));
                    value = txtStyle.substring(txtStyle.indexOf(":") + 1, txtStyle.length);
                    newdiv.style[key] = value;
                }
            } 
            catch (ex) {
            }
            
            /*                if (nodeRifer.width.replace("px", "") != "") 
             newdiv.style.width = (nodeRifer.style.width.replace("px", "") + 10) + "px";
             */
            try {
                for (var x = 0; x < json.main[i].layout[0].params.length; x++) {
                    txtParams = json.main[i].layout[0].params[x];
                    key = txtParams.substring(0, txtParams.indexOf(":"));
                    value = txtParams.substring(txtParams.indexOf(":") + 1, txtParams.length);
                    newdiv[key] = "'" + value + "'";
                }
            } 
            catch (ex) {
            }
            try {
                var events = json.main[i].layout[0].events[0];
                newdiv["onmousedown"] = (events.onmousedown != null) ? events.onmousedown : null;
                newdiv["onmouseup"] = (events.onmouseup != null) ? events.onmouseup : null;
                newdiv["onmousemove"] = (events.onmousemove != null) ? events.onmousemove : null;
                newdiv["onmouseout"] = (events.onmouseout != null) ? events.onmouseout : null;
                newdiv["onclick"] = (events.onclick != null) ? events.onclick : null;
            } 
            catch (ex) {
            }

            newdiv.innerHTML = json.main[i].layout[0].innerHTML;
            document.getElementById(json.main[i].layout[0].nodeRifer).appendChild(newdiv);
            i++;
        }
    }
}

Layout = function(){
    this.renderize = function(region, resize, fixedmodify, menu, _statusbar){
    		log("region = " + region);
    		log("resize = " + resize);
    		log("fixedmodify = " + fixedmodify);
    		log("menu = " + menu);
    		log("_statusbar = " + _statusbar);

//        try {
        
            document.getElementById(resize).style.left = (document.getElementById(region).style.width.replace('px', '') - 15) + "px";
            document.getElementById(resize).style.top = (document.getElementById(region).style.height.replace('px', '') - 0) + "px";
            
            document.getElementById(fixedmodify).style.left = (document.getElementById(region).style.width.replace('px', '') - 25) + "px";
            document.getElementById(menu).style.width = document.getElementById(region).style.width;
            
            document.getElementById(_statusbar).style.width = document.getElementById(region).style.width;
            document.getElementById(_statusbar).style.top = document.getElementById(region).style.height;
/*        } 
        catch (ex) {
        }
 */       
    }
}

// Insere cada div criado em um mapa com a chave "div.id" 
var allWidgetsCreated = new Map();
JsonBind = function(){
	
    var comma = "";
    var elements = new Map();
	var structBegin = "{\"main\": [";
	var structEnd = "]}";
    var txtJson = "";
    
    this.addElements = function(jsonSchema){
        elements.put(jsonSchema.id, jsonSchema);
		allWidgetsCreated.put(jsonSchema.id, jsonSchema);
        //		alert("jsonSchema.id = " + jsonSchema.id);
    }
    
    //Return string no formato Json
    this.toString = function(){
        for (var i = 0; i < elements.size(); i++) {
            jsonSchema = elements.elementAt(i);
            generateJson(jsonSchema);
        }
        return structBegin + txtJson + structEnd;
    }
    

    this.getJsonWidget = function(idWidgets){
		txtJson = "";
		comma = "";
		for (var x = 0; x < idWidgets.length; x++) {
			jsonSchema = allWidgetsCreated.get(idWidgets[x]);
			generateJson(jsonSchema);
			comma = ",";
		}
//        return txtJson += "]};";
        return txtJson;		
    }

    function generateJson(json){
        txtJson += comma + "{ \"layout\": [{";
        txtJson += "\"tag\":\"" + json.tag + "\"," + "\"id\":\"" + json.id + "\",";
        txtJson += "\"params\":[";
        if (json.param != null) 
            txtJson += json.param.toString();
        
        txtJson += "],";
        txtJson += "\"nodeRifer\":\"" + json.nodeRifer + "\",";
        txtJson += "\"innerHTML\":\"" + json.innerHTML + "\",";
        txtJson += "\"style\":[" + json.style.toString() + "],";
        
        txtJson += "\"events\":[{";
        if (json.events != null) 
            txtJson += json.events.toString("str/obj");
        txtJson += "}]";
        
//        document.getElementById("result").text = json.events.toString();
        txtJson += "}]}";
        comma = ",";
    }
    
}

//A estrutura do Json para cada div a ser criado
JsonSchema = function(){

    this.tag = "";
    this.id = "";
    this.params = new Map();
    this.nodeRifer = "";
    this.innerHTML = "";
    this.style = new Map();
    this.events = new Map();
    
    
    
}

Widget = function(){
	var bind = null;
	//Um mapa que contem o ultimo ID de cada tipo de widget
    var mapWidgetLastId = new Map();

    this.getJsonBind = function(){ return bind; }
	//Um mapa que contem o ultimo ID de cada tipo de widget
	
	// Contem a lista de todos id_widgets[key] criados e tipo[value] (panel, treeview, etc..)
    var mapWidgetId = new Map();
	
	this.widgets = function(){
		return mapWidgetId;
	}
    this.createPanel = function(widgetId){
        var count = 0;
        var menubar, resize, fixmodify, statusbar, title;

        if (mapWidgetLastId.get(widgetId) != null) 
            count = mapWidgetLastId.get(widgetId) + 1;
        
        mapWidgetLastId.put(widgetId, count);
        mapWidgetId.put(widgetId+count, widgetId);

        widgetId += count;

        
        menubar = "menubar" + count;
        title = "title" + count;
        resize = "resize" + count;
        fixmodify = "fixmodify" + count;
        statusbar = "statusbar" + count;

        bind = new JsonBind();
        
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "div";
        jsonSchema.id = widgetId;
        jsonSchema.nodeRifer = "schema";
        jsonSchema.innerHTML = "&nbsp;<div id='content"+widgetId+"'/>"
        
        jsonSchema.style.put("top", "150px");
        jsonSchema.style.put("width", "200px");
        jsonSchema.style.put("height", "100px");
        jsonSchema.style.put("left", "300px");
        jsonSchema.style.put("position", "absolute");
        jsonSchema.style.put("backgroundColor", "#f0f2f4");
		jsonSchema.events.put("onmousemove", "eventMouseMove");
/*        jsonSchema.events.put("onmousemove", "function(){" +
													" listener.fire(['idwidget','"+widgetId+"']); " +
										        "}");
        jsonSchema.events.put("onmousedown", "function(){" +
													" listener.fire(['attachwidget']); " +
										        "}");

*/        bind.addElements(jsonSchema);
        
        // Cria Menu-Bar default
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "div";
        jsonSchema.id = menubar;
        jsonSchema.nodeRifer = widgetId;
        
        jsonSchema.style.put("top", "-10px");
        jsonSchema.style.put("left", "0px");
        jsonSchema.style.put("height", "20px");
        jsonSchema.style.put("width", "200px");
        jsonSchema.style.put("backgroundImage", "url(img/white-top-bottom.gif)");
        
        jsonSchema.events.put("onmousedown", "function(){" +
											        " dragdrop.addEvent(document.getElementById('" + widgetId + "'), 'move'); " +
											        " document.getElementById('_"+fixmodify+"').src = 'img/fix_modify_enable.gif'; " +
													" listener.fire('changeProperties'); " +
													" listener.fire(['mousedown','"+widgetId+"']); " +
										        "}");

        bind.addElements(jsonSchema);
        
        
        // Cria Icon Fix default
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "div";
        jsonSchema.id = fixmodify;
        jsonSchema.nodeRifer = widgetId;
        jsonSchema.innerHTML = "<img id='_"+fixmodify+"' src='img/fix_modify_enable.gif' width='14' height='16'/>";
        
        jsonSchema.style.put("position", "absolute");
        jsonSchema.style.put("top", "-10px");
        jsonSchema.style.put("left", "179px");
        
        jsonSchema.events.put("onmousedown", "function(){" +
        " 	document.getElementById('_"+fixmodify+"').src='img/fix_modify_disable.gif'; " +
        "		" +
        "}");
        
        
        bind.addElements(jsonSchema);
        
        // Cria Caption default
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "span";
        jsonSchema.id = title;
        jsonSchema.nodeRifer = widgetId;
//        jsonSchema.innerHTML = "<span id='" + title + "' class='title-blue'>"+widgetId+"</span>";
        jsonSchema.innerHTML = widgetId;
        
        jsonSchema.style.put("position", "absolute");
        jsonSchema.style.put("top", "-8px");
        jsonSchema.style.put("left", "10px");

        jsonSchema.style.put("color", "#466F93");
        jsonSchema.style.put("fontWeight", "bold");
        jsonSchema.style.put("fontFamily", "verdana, arial, sans serif");
        jsonSchema.style.put("lineHeight", "16px");
        jsonSchema.style.put("fontSize", "12px");
        
        bind.addElements(jsonSchema);
        
        
        //Cria StatusBar default
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "div";
        jsonSchema.id = statusbar;
        jsonSchema.nodeRifer = widgetId;
        
        jsonSchema.style.put("top", "95px");
        jsonSchema.style.put("left", "0px");
        jsonSchema.style.put("height", "15px");
        jsonSchema.style.put("width", "196px");
        jsonSchema.style.put("backgroundImage", "url(img/white-top-bottom.gif)");
        
        jsonSchema.events.put("onmousedown", "function(){" +
												 " dragdrop.addEvent(document.getElementById('" + widgetId + "'), 'resize'); " +
												 " listener.fire(['resize',this.parentNode.id]); " +
									        "}");
        
        bind.addElements(jsonSchema);
        
        //Cria Image de resize default
        jsonSchema = new JsonSchema();
        jsonSchema.tag = "div";
        jsonSchema.id = resize;
        jsonSchema.nodeRifer = widgetId;
        jsonSchema.innerHTML = "<img id='resize' src='img/resizediv.gif'/>";
        
        jsonSchema.style.put("position", "absolute");
        jsonSchema.style.put("top", "95px");
        jsonSchema.style.put("left", "180px");
        
        bind.addElements(jsonSchema);
        
        buildDesign.create(eval("json = " + bind.toString()));

        var json_ = "json = {\"main\": [" + bind.getJsonWidget([widgetId,menubar,title,resize,fixmodify,statusbar]) + "]}";
	     	//buildDesign.create(eval(json_));
/*
        var json_ = "json = {\"main\": [" + bind.getJsonWidget(widgetId).replace("]};","")
					  + bind.getJsonWidget(menubar).replace("]};","")
					  + bind.getJsonWidget(title).replace("]};","")
					  + bind.getJsonWidget(resize).replace("]};","")
					  + bind.getJsonWidget(fixmodify).replace("]};","")
					  + bind.getJsonWidget(statusbar) + "]}";


*/	 
	 	listener.fire("newWidget");   
    }
}

	//Initialization
    if (!Array.indexOf) {
        Array.prototype.indexOf = function(obj){
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                    return i;
                }
            }
            return -1;
        }
    }



	widget = new Widget();
	
	//class Layout extends BuilderDesigner
	Layout.prototype = new BuilderDesigner;
	buildDesign = new Layout;


	