// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------
 
	var stockCod1 = "ALLL4,BRAP3,CESP3,CMIG3,EALT4,EBTP3,PIBB11,VALE5";   
	var setup1 = "20080610|60,61,68,324|20080611|71,60,79,324|20080612|83,59,92,324|20080613|95,60,103,324|20080616|105,62,113,324|20080617|116,61,125,324|20080618|128,60,135,324|20080619|140,61,147,324|20080620|151,60,159,324|20080623|161,62,168,324|20080624|173,59,181,324|20080625|185,58,191,324|20080626|196,58,204,324|20080627|207,59,214,324|20080630|218,62,224,324|20080701|229,64,238,324|20080702|241,62,249,324|20080703|251,63,260,324|20080704|262,62,270,324|20080707|275,62,282,324|20080708|285,61,295,324|20080710|308,62,315,324|20080711|318,64,326,324|20080714|331,65,337,324|20080715|341,62,349,324|20080716|353,64,360,324|20080717|364,64,371,324|20080718|375,64,384,324|20080721|386,64,394,324|20080722|398,61,407,324|20080723|409,62,416,324|20080724|421,64,429,324|20080725|431,62,438,324|20080728|442,62,451,324|20080729|453,63,462,324|20080730|466,61,473,324|20080731|476,62,484,324|20080801|487,61,495,324|20080804|498,61,506,324|20080805|510,60,519,324|20080806|521,63,529,324|20080807|533,62,539,324|20080808|544,64,555,324|20080811|555,62,563,324|20080812|567,61,574,324|20080813|578,58,585,324|20080814|588,61,596,324|20080815|600,60,606,324|20080818|611,63,619,324|20080819|623,62,630,324|20080820|634,63,642,324|20080821|644,64,653,324|20080822|657,63,664,324|20080825|668,62,676,324|20080826|679,63,686,324|20080827|690,65,698,324|20080828|702,63,710,324|20080829|712,60,720,324|20080901|724,63,731,324|20080902|734,63,742,324|20080903|747,64,753,324|20080904|758,63,766,324|20080905|769,62,783,324"; 

// MGEL4,PETR4
	var stockCod11 = "MGEL4,PETR4,BSCT6";   
	var setup11 = "20080610|60,61,68,324|20080611|71,60,79,324|20080612|83,59,92,324|20080613|95,60,103,324|20080616|105,62,113,324|20080617|116,61,125,324|20080618|128,60,135,324|20080619|140,61,147,324|20080620|151,60,159,324|20080623|161,62,168,324|20080624|173,59,181,324|20080625|185,58,191,324|20080626|196,58,204,324|20080627|207,59,214,324|20080630|218,62,224,324|20080701|229,64,238,324|20080702|241,62,249,324|20080703|251,63,260,324|20080704|262,62,270,324|20080707|275,62,282,324|20080708|285,61,295,324|20080710|308,62,315,324|20080711|318,64,326,324|20080714|331,65,337,324|20080715|341,62,349,324|20080716|353,64,360,324|20080717|364,64,371,324|20080718|375,64,384,324|20080721|386,64,394,324|20080722|398,61,407,324|20080723|409,62,416,324|20080724|421,64,429,324|20080725|431,62,438,324|20080728|442,62,451,324|20080729|453,63,462,324|20080730|466,61,473,324|20080731|476,62,484,324|20080801|487,61,495,324|20080804|498,61,506,324|20080805|510,60,519,324|20080806|521,63,529,324|20080807|533,62,539,324|20080808|544,64,555,324|20080811|555,62,563,324|20080812|567,61,574,324|20080813|578,58,585,324|20080814|588,61,596,324|20080815|600,60,606,324|20080818|611,63,619,324|20080819|623,62,630,324|20080820|634,63,642,324|20080821|644,64,653,324|20080822|657,63,664,324|20080825|668,62,676,324|20080826|679,63,686,324|20080827|690,65,698,324|20080828|702,63,710,324|20080829|712,60,720,324|20080901|724,63,731,324|20080902|734,63,742,324|20080903|747,64,753,324|20080904|758,63,766,324|20080905|769,62,783,324"; 

	var stockCod2 = "BBDC4,BRTP4,BTOW3,CCIM3,CCRO3,CESP6,CEMIG4,CNFB4,CSAN3,CSMG3,CYRE3,BRTP4,CESP6,CGAS5,CMIG4,CNFB4,CSAN3,CSMG3,DAYC4,CYRE3,DSUL3,ECOD3,ELET3,ELET6,EMBR3,EVEN3,GETI4,GFSA3,GGBR4,GOAU4,GOLL4,LREN3,MARI3,MTIG4,NATU3,NETC4,PINE4,PLAS3,TCSL4";   
	var setup2 		= "20080610|52,61,62,324|20080611|64,60,70,324|20080612|76,59,92,324|20080613|87,60,103,324|20080616|98,62,113,324|20080617|110,61,125,324|20080618|120,60,135,324|20080619|132,61,147,324|20080620|144,60,159,324|20080623|155,62,168,324|20080624|166,59,181,324|20080625|178,58,191,324|20080626|190,58,204,324|20080627|199,59,214,324|20080630|212,62,224,324|20080701|223,64,238,324|20080702|234,62,249,324|20080703|246,63,260,324|20080704|257,62,270,324|20080707|268,62,282,324|20080708|280,61,295,324|20080710|302,62,315,324|20080711|314,64,326,324|20080714|325,65,337,324|20080715|337,62,349,324|20080716|348,64,360,324|20080717|359,64,371,324|20080718|370,64,384,324|20080721|383,64,394,324|20080722|395,61,407,324|20080723|393,62,416,324|20080724|416,64,429,324|20080725|427,62,438,324|20080728|438,62,451,324|20080729|451,63,462,324|20080730|462,61,473,324|20080731|473,62,484,324|20080801|485,61,495,324|20080804|496,61,506,324|20080805|507,60,519,324|20080806|519,63,529,324|20080807|530,62,539,324|20080808|540,64,555,324|20080811|550,62,563,324|20080812|563,61,574,324|20080813|574,58,585,324|20080814|588,61,596,324|20080815|598,60,606,324|20080818|610,63,619,324|20080819|620,62,630,324|20080820|631,63,642,324|20080821|643,64,653,324|20080822|655,63,664,324|20080825|667,62,676,324|20080826|676,63,686,324|20080827|689,65,698,324|20080828|700,63,710,324|20080829|710,60,720,324|20080901|724,63,731,324|20080902|734,63,742,324|20080903|747,64,753,324|20080904|758,63,766,324|20080905|769,62,783,324"; 

// CCPR3,GETI3,GETI4,
	var stockCod22 = "CCPR3,GETI3,GETI4,EQTL3";   
	var setup22 		= "20080610|52,61,68,324|20080611|64,60,79,324|20080612|76,59,92,324|20080613|87,60,103,324|20080616|98,62,113,324|20080617|110,61,125,324|20080618|120,60,135,324|20080619|132,61,147,324|20080620|144,60,159,324|20080623|155,62,168,324|20080624|166,59,181,324|20080625|178,58,191,324|20080626|190,58,204,324|20080627|199,59,214,324|20080630|212,62,224,324|20080701|223,64,238,324|20080702|234,62,249,324|20080703|246,63,260,324|20080704|257,62,270,324|20080707|268,62,282,324|20080708|280,61,295,324|20080710|302,62,315,324|20080711|314,64,326,324|20080714|325,65,337,324|20080715|337,62,349,324|20080716|348,64,360,324|20080717|359,64,371,324|20080718|370,64,384,324|20080721|383,64,394,324|20080722|395,61,407,324|20080723|393,62,416,324|20080724|416,64,429,324|20080725|427,62,438,324|20080728|438,62,451,324|20080729|451,63,462,324|20080730|462,61,473,324|20080731|473,62,484,324|20080801|485,61,495,324|20080804|496,61,506,324|20080805|507,60,519,324|20080806|519,63,529,324|20080807|530,62,539,324|20080808|540,64,555,324|20080811|550,62,563,324|20080812|563,61,574,324|20080813|574,58,585,324|20080814|588,61,596,324|20080815|598,60,606,324|20080818|610,63,619,324|20080819|620,62,630,324|20080820|631,63,642,324|20080821|643,64,653,324|20080822|655,63,664,324|20080825|667,62,676,324|20080826|676,63,686,324|20080827|689,65,698,324|20080828|700,63,710,324|20080829|710,60,720,324|20080901|724,63,731,324|20080902|734,63,742,324|20080903|747,64,753,324|20080904|758,63,766,324|20080905|769,62,783,324"; 


// CMIG3,CTNM4
//	var stockCod3 = "CMIG3,CTNM4";   
//	var setup3 		= "20080610|66,61,68,324|20080611|76,60,79,324|20080612|87,59,92,324|20080613|99,60,103,324|20080616|110,62,113,324|20080617|122,61,125,324|20080618|132,60,135,324|20080619|144,61,147,324|20080620|154,60,159,324|20080623|165,62,168,324|20080624|176,59,181,324|20080625|188,58,191,324|20080626|200,58,204,324|20080627|210,59,214,324|20080630|222,62,224,324|20080701|233,64,238,324|20080702|244,62,249,324|20080703|256,63,260,324|20080704|265,62,270,324|20080707|276,62,282,324|20080708|287,61,295,324|20080710|312,62,315,324|20080711|322,64,326,324|20080714|333,65,337,324|20080715|345,62,349,324|20080716|356,64,360,324|20080717|365,64,371,324|20080718|367,64,384,324|20080721|380,64,394,324|20080722|390,61,407,324|20080723|400,62,416,324|20080724|410,64,429,324|20080725|423,62,438,324|20080728|433,62,451,324|20080729|445,63,462,324|20080730|457,61,473,324|20080731|466,62,484,324|20080801|479,61,495,324|20080804|490,61,506,324|20080805|515,60,519,324|20080806|525,63,529,324|20080807|530,62,539,324|20080808|540,64,555,324|20080811|550,62,563,324|20080812|563,61,574,324|20080813|574,58,585,324|20080814|588,61,596,324|20080815|598,60,606,324|20080818|610,63,619,324|20080819|620,62,630,324|20080820|631,63,642,324|20080821|643,64,653,324|20080822|655,63,664,324|20080825|667,62,676,324|20080826|676,63,686,324|20080827|689,65,698,324|20080828|700,63,710,324|20080829|710,60,720,324|20080901|724,63,731,324|20080902|734,63,742,324|20080903|747,64,753,324|20080904|758,63,766,324|20080905|769,62,783,324"; 
	

function loadSettingsMapImage(){
	  mapImage = new Map();
	  
		if(stockCod1.indexOf(stocks[index]) != -1){
				tokens = setup1.split("|");
				for(var x=0; x<tokens.length;x+=2){
					mapImage.put(tokens[x],tokens[x+1]);
				}
		}

		if(stockCod11.indexOf(stocks[index]) != -1){
				tokens = setup11.split("|");
				for(var x=0; x<tokens.length;x+=2){
					mapImage.put(tokens[x],tokens[x+1]);
				}
		}

		if(stockCod2.indexOf(stocks[index]) != -1){
				tokens = setup2.split("|");
				for(var x=0; x<tokens.length;x+=2){
					mapImage.put(tokens[x],tokens[x+1]);
				}
		}

		if(stockCod22.indexOf(stocks[index]) != -1){
				tokens = setup22.split("|");
				for(var x=0; x<tokens.length;x+=2){
					mapImage.put(tokens[x],tokens[x+1]);
				}
		}

/*
		if(stockCod3.indexOf(stocks[index]) != -1){
				tokens = setup3.split("|");
				for(var x=0; x<tokens.length;x+=2){
					mapImage.put(tokens[x],tokens[x+1]);
				}
		}
*/
}

	var maparea = "";
	function createMapArea(){
			var stock;
			var area = "<area shape='rectangle' alt='$formatdate' id='$date' coords='$coords' onmousemove='actionArea(this)' style='' onclick='breakIndicator=true; showStockDetails(this)'/>";
			loadSettingsMapImage();
			
			for(var x=0; x<stocks.length; x++){
				 stock = mapBovespa.elementAt(x);
				 tmp = area.replace('$coords',mapImage.get(stock.dataPregao));
				 tmp = tmp.replace('$date', stock.dataPregao);
				 tmp = tmp.replace('$formatdate', stock.dataPregao.substring(6,8) + "/" + stock.dataPregao.substring(4,6) + "/" + stock.dataPregao.substring(0,4));
				 maparea += tmp; 
			}		
			
			document.getElementById("mapareaimg").innerHTML = maparea.replace("undefined","");
			//maparea = "";
	}
	
	
	function createComboCandles(){
/*		for(x=0;x<mapCandle.listKeys().length; x++)
			addOption(document.getElementById("tipocandler"), mapCandle.elementAt(x).tipo, mapCandle.elementAt(x).id);
*/	}
	
	function addOption(selectbox, text, value )
	{
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}
	
	function applyTrainning(){

			var currentTime = new Date();
			var month = currentTime.getMonth() + 1;
			var day = currentTime.getDate();
			var year = currentTime.getFullYear();

			var evaluatedCandles = {
					user_id:1,
					candle_id: document.getElementById("tipocandler").options[document.getElementById("tipocandler").selectedIndex].value,
					measure_id: mapBovespa.get(document.getElementById("stockid_selected").value).idMeasure,
					datetime: (day + "/" + month + "/" + year)
			};
			waitReasponse(true, "loading01");
			EvaluatedCandleBean.persist(evaluatedCandles, function(result){waitReasponse(false, "loading01");});			
	}
	
	
	function waitReasponse(opc, objname){
		
		if(opc==true){
			document.getElementById("loaddetails").style.visibility = "visible";
//			document.getElementById("title01").innerHTML = document.getElementById("title01").innerHTML + " - waiting...";
		}
		else{
			document.getElementById("loaddetails").style.visibility = "hidden";
//			document.getElementById("title01").innerHTML = document.getElementById("title01").innerHTML.replace(" - waiting...", "");
		}		
	}
	

var jsonStock = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "window0",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "&nbsp;<div id='contentwindow0'/>",
            "style": ["top:50px", "width:620px", "height:670px", "left:2px", "position:absolute", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousemove": eventMouseMove
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar0",
            "params": [],
            "nodeRifer": "window0",
            "innerHTML": "",
            "style": ["top:-10px", "left:0px", "height:20px", "width:620px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('window0'), 'move');
                    listener.fire(['mousedown', 'window0']);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "span",
            "id": "title0",
            "params": [],
            "nodeRifer": "window0",
            "innerHTML": "<div id='loadchart' style='visibility:hidden'><img src='img/loading.gif'/></div>&nbsp;&nbsp;Stocks",
            "style": ["position:absolute", "top:-8px", "left:10px", "color:#466F93", "fontWeight:bold", "fontFamily:verdana, arial, sans serif", "lineHeight:16px", "fontSize:12px"],
            "events": [{}]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar0",
            "params": [],
            "nodeRifer": "window0",
            "innerHTML": "",
            "style": ["top:655px", "left:0px", "height:15px", "width:620px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('window0'), 'resize');
                    //listener.fire(['resize', this.parentNode.id]);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "close0",
            "params": [],
            "nodeRifer": "window0",
            "innerHTML": "<img id='resize' src='img/close.gif'/>",
            "style": ["position:absolute", "top:-7px", "left:600px", "cursor:pointer"],
            "events": [{
                "onclick": function(){
                    document.getElementById('window0').style.visibility = 'hidden';
                }
            }]
        }]        
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize0",
            "params": [],
            "nodeRifer": "window0",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:655px", "left:605px"],
            "events": [{}]
        }]

    }    
    ]
}




var jsonScript = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "window01",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "&nbsp;<div id='contentwindow01'/>",
            "style": ["top:200px", "width:500px", "height:510px", "left:700px", "position:absolute", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousemove": eventMouseMove
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar01",
            "params": [],
            "nodeRifer": "window01",
            "innerHTML": "",
            "style": ["top:-10px", "left:0px", "height:20px", "width:500px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('window01'), 'move');
                    listener.fire(['mousedown', 'window01']);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "span",
            "id": "title01",
            "params": [],
            "nodeRifer": "window01",
            "innerHTML": "",
            "style": ["position:absolute", "top:-8px", "left:10px", "color:#466F93", "fontWeight:bold", "fontFamily:verdana, arial, sans serif", "lineHeight:16px", "fontSize:12px"],
            "events": [{}]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar01",
            "params": [],
            "nodeRifer": "window01",
            "innerHTML": "",
            "style": ["top:500px", "left:0px", "height:15px", "width:500px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('window0'), 'resize');
                    //listener.fire(['resize', this.parentNode.id]);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize01",
            "params": [],
            "nodeRifer": "window01",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:500px", "left:485px"],
            "events": [{}]
        }]

    }    
    ]
}

var jsonWidgetColor = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "window02",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "&nbsp;<div id='contentwindow02'/>",
            "style": ["top:30px", "width:375px", "height:280px", "left:630px", "position:absolute", "backgroundColor:#100C72"],
            "events": [{
                "onmousemove": eventMouseMove
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar02",
            "params": [],
            "nodeRifer": "window02",
            "innerHTML": "",
            "style": ["top:0px", "left:0px", "height:20px", "width:375px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('window02'), 'move');
                    listener.fire(['mousedown', 'window02']);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "span",
            "id": "title02",
            "params": [],
            "nodeRifer": "window02",
            "innerHTML": "",
            "style": ["position:absolute", "top:-8px", "left:10px", "color:#466F93", "fontWeight:bold", "fontFamily:verdana, arial, sans serif", "lineHeight:16px", "fontSize:12px"],
            "events": [{}]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "close01",
            "params": [],
            "nodeRifer": "window01",
            "innerHTML": "<img id='resize' src='img/close.gif'/>",
            "style": ["position:absolute", "top:-7px", "left:480px", "cursor:pointer"],
            "events": [{
                "onclick": function(){
                    document.getElementById('window01').style.visibility = 'hidden';
                }
            }]
        }]
        
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar02",
            "params": [],
            "nodeRifer": "window02",
            "innerHTML": "",
            "style": ["top:280px", "left:0px", "height:15px", "width:375px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('window0'), 'resize');
                    //listener.fire(['resize', this.parentNode.id]);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "close02",
            "params": [],
            "nodeRifer": "window02",
            "innerHTML": "<img id='resize' src='img/close.gif'/>",
            "style": ["position:absolute", "top:4px", "left:355px", "cursor:pointer"],
            "events": [{
                "onclick": function(){
                    document.getElementById('window02').style.visibility = 'hidden';
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize02",
            "params": [],
            "nodeRifer": "window02",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:280px", "left:360px"],
            "events": [{}]
        }]

    }    
    ]
}

var jsonMenuDesktop = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "menuicon01",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<img id='resize' src='img/icon_chart.png'/>",
            "style": ["top:50px", "width:128px", "height:128px", "left:10px", "position:absolute", "cursor:pointer"],
            "events": [{
                "onmousemove": eventMouseMove,
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('menuicon01'), 'move');
                    listener.fire(['mousedown', 'menuicon01']);
                },
                "onclick": function(){
                    document.getElementById('window0').style.visibility = 'visible';
                }
                
            }]
        }]
    
    },{
        "layout": [{
            "tag": "div",
            "id": "menuicon02",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<img id='resize' src='img/icon_script.png'/>",
            "style": ["top:200px", "width:128px", "height:128px", "left:10px", "position:absolute", "cursor:pointer"],
            "events": [{
                "onmousemove": eventMouseMove,
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('menuicon02'), 'move');
                    listener.fire(['mousedown', 'menuicon02']);
                },
                "onclick": function(){
                    document.getElementById('window01').style.visibility = 'visible';
                }
            }]
        }]
        
    },{
        "layout": [{
            "tag": "div",
            "id": "menuicon03",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<img id='resize' src='img/icon_search.png'/>",
            "style": ["top:350px", "width:128px", "height:128px", "left:10px", "position:absolute", "cursor:pointer"],
            "events": [{
                "onmousemove": eventMouseMove,
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('menuicon03'), 'move');
                    listener.fire(['mousedown', 'menuicon03']);
                },
                "onclick": function(){
                    document.getElementById('window02').style.visibility = 'visible';
                }

            }]
        }]
    
    },{    
        "layout": [{
            "tag": "div",
            "id": "menuicon04",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<img id='resize' src='img/icon_chart.png'/>",
            "style": ["top:500px", "width:128px", "height:128px", "left:10px", "position:absolute", "cursor:pointer"],
            "events": [{
                "onmousemove": eventMouseMove,
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('menuicon04'), 'move');
                    listener.fire(['mousedown', 'menuicon04']);
                },
                "onclick": function(){
                    document.getElementById('window04').style.visibility = 'visible';
                }

            }]
        }]
    
    }
            
    ]
}


var jsonSetupChart = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "window03",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "&nbsp;<div id='contentwindow03'/>",
            "style": ["top:30px", "width:310px", "height:330px", "left:630px", "position:absolute", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousemove": eventMouseMove
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar03",
            "params": [],
            "nodeRifer": "window03",
            "innerHTML": "",
            "style": ["top:0px", "left:0px", "height:20px", "width:310px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('window03'), 'move');
                    listener.fire(['mousedown', 'window02']);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "span",
            "id": "title03",
            "params": [],
            "nodeRifer": "window03",
            "innerHTML": "",
            "style": ["position:absolute", "top:-8px", "left:10px", "color:#466F93", "fontWeight:bold", "fontFamily:verdana, arial, sans serif", "lineHeight:16px", "fontSize:12px"],
            "events": [{}]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar03",
            "params": [],
            "nodeRifer": "window03",
            "innerHTML": "",
            "style": ["top:330px", "left:0px", "height:15px", "width:310px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('window0'), 'resize');
                    //listener.fire(['resize', this.parentNode.id]);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "close03",
            "params": [],
            "nodeRifer": "window03",
            "innerHTML": "<img id='resize' src='img/close.gif'/>",
            "style": ["position:absolute", "top:4px", "left:290px", "cursor:pointer"],
            "events": [{
                "onclick": function(){
                    document.getElementById('window03').style.visibility = 'hidden';
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize03",
            "params": [],
            "nodeRifer": "window03",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:330px", "left:295px"],
            "events": [{}]
        }]

    }    
    ]
}


var jsonChartSetting = {
    'main': [{
        "layout": [{
            "tag": "div",
            "id": "window04",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "&nbsp;<div id='contentwindow04'/>",
            "style": ["top:50px", "width:620px", "height:670px", "left:2px", "position:absolute", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousemove": eventMouseMove
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar04",
            "params": [],
            "nodeRifer": "window04",
            "innerHTML": "",
            "style": ["top:-10px", "left:0px", "height:20px", "width:620px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('window04'), 'move');
                    listener.fire(['mousedown', 'window04']);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "span",
            "id": "title04",
            "params": [],
            "nodeRifer": "window04",
            "innerHTML": "<div id='loadchart' style='visibility:hidden'><img src='img/loading.gif'/></div>&nbsp;&nbsp;Stocks",
            "style": ["position:absolute", "top:-8px", "left:10px", "color:#466F93", "fontWeight:bold", "fontFamily:verdana, arial, sans serif", "lineHeight:16px", "fontSize:12px"],
            "events": [{}]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar04",
            "params": [],
            "nodeRifer": "window04",
            "innerHTML": "",
            "style": ["top:655px", "left:0px", "height:15px", "width:620px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('window04'), 'resize');
                    //listener.fire(['resize', this.parentNode.id]);
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "close04",
            "params": [],
            "nodeRifer": "window04",
            "innerHTML": "<img id='resize' src='img/close.gif'/>",
            "style": ["position:absolute", "top:-7px", "left:600px", "cursor:pointer"],
            "events": [{
                "onclick": function(){
                    document.getElementById('window04').style.visibility = 'hidden';
                }
            }]
        }]        
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize0",
            "params": [],
            "nodeRifer": "window04",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:655px", "left:605px"],
            "events": [{}]
        }]

    }    
    ]
}
