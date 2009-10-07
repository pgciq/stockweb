
DragDrop = function(){
	var mousex = 0;
	var mousey = 0;
	var grabx = 0;
	var graby = 0;
	var orix = 0;
	var oriy = 0;
	var elex = 0;
	var eley = 0;
	var algor = 0;
	
	var dragobj = null;
	
	function falsefunc(){
		return false;
	}
	function init(){
		document.onmousemove = update;
		update();
	}
	
	function clear(){
		mousex = 0;
		mousey = 0;
		grabx = 0;
		graby = 0;
		orix = 0;
		oriy = 0;
		elex = 0;
		eley = 0;
		algor = 0;
	}
	function getMouseXY(e){
		if (!e) 
			e = window.event;
		if (e) {
			if (e.pageX || e.pageY) {
				mousex = e.pageX;
				mousey = e.pageY;
				algor = '[e.pageX]';
				if (e.clientX || e.clientY) 
					algor += ' [e.clientX] '
			}
			else 
				if (e.clientX || e.clientY) {
					mousex = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
					mousey = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
					algor = '[e.clientX]';
					if (e.pageX || e.pageY) 
						algor += ' [e.pageX] '
				}
		}
	}
	
	function update(e){
		try{
			getMouseXY(e);
			}catch(e){}
	}
	
	function grab(context){
		try {
			document.onmousedown = falsefunc;
			dragobj = context;
			dragobj.style.zIndex = 10;
			document.onmousemove = drag;
			document.onmouseup = drop;
			grabx = mousex;
			graby = mousey;
			elex = orix = dragobj.offsetLeft;
			eley = oriy = dragobj.offsetTop;
			update();
		} 
		catch (e) {
		}
	}
	
	function drag(e){
		try {
			if (dragobj) {
				elex = orix + (mousex - grabx);
				eley = oriy + (mousey - graby);
				dragobj.style.position = "absolute";
				dragobj.style.left = (elex).toString(10) + 'px';
				dragobj.style.top = (eley).toString(10) + 'px';
			}
			update(e);
		} 
		catch (e) {
		}
		return false;
	}
	
	function drop(){
		if (dragobj) {
			dragobj.style.zIndex = 0;
			dragobj = null;
		}
		update();
		document.onmousemove = update;
		document.onmouseup = null;
		document.onmousedown = null;
	}
	
	
	this.getMouseX = function(){
		return mousex;
	}
	this.getMouseY = function(){
		return mousey;
	}
	this.getMouseCoord = function(){
		window.onmousemove = function(event){
			mousex = event.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			mousey = event.clientY + document.body.scrollTop + document.documentElement.scrollTop;
			window.onmousemove = null;
		}
	}
	this.addEvent = function(object, event, func){
	
		if (event == "move") {
/*			object.onmousedown = null;
			object.onmousemove = null;
*/
			removeListener(object, "mousedown", eventMouseDown);
			removeListener(object, "mousemove", eventMouseMove);

			grab(object);
			if (func != null) 
				func();
			return;
		}
		if (event == "resize") {
			addListener(object, "mousedown", eventMouseDown);			
			addListener(object, "mouseup", eventMouseUp);
			listener.subscribe(dragdrop.resizePanel);
			/*
			 object.onmousedown = function(e){
			 object.onmousemove = function(e){
			 getMouseXY(e);
			 object.style.height = (mousey - object.style.top.replace('px', '') + 10) + "px";
			 object.style.width = (mousex - object.style.left.replace('px', '') + 10) + "px";
			 func(param[0], param[1], param[2], param[3], param[4]);
			 }
			 }
			 */
			
		}
	}
	
	this.resizePanel = function(param){
		if(param[0] != "mousemove") return;


		object = param[1];
		getMouseXY(object);
		object.style.height = (mousey - object.style.top.replace('px', '') + 10) + "px";
		object.style.width = (mousex - object.style.left.replace('px', '') + 10) + "px";
		
		
		var cmpPanel = object.getElementsByTagName("div");
		buildDesign.renderize(object.id, cmpPanel[4].id, cmpPanel[2].id, cmpPanel[1].id, cmpPanel[3].id);
		//resize, fixedmodify, menu, _statusbar		
	}
}

	var dragdrop = new DragDrop();



// ==================== EVENT BROKER ====================
	// Metodo unico para adicionar um evento a um objeto
	function addListener(obj, event, func){
		if (window.addEventListener) {
			obj.addEventListener(event, func, false); // Mozilla, Netscape, Firefox
			return;
		}
		try{
			obj.attachEvent("on" + event, func); //IE
		}catch(e){log(e.description);}
	}

	// Metodo unico para remover um evento a um objeto
	function removeListener(obj, event, func){
		if (window.addEventListener) {
			obj.removeEventListener(event, func, false); // Mozilla, Netscape, Firefox
			return;
		}
		obj.detachEvent("on" + event, func); //IE
	}
	
	function eventMouseDown(evt){

		obj = (utils.navigatorName.indexOf("Microsoft") != -1) ? evt.srcElement.parentNode: evt.currentTarget;
		if(utils.navigatorName.indexOf("Microsoft") != -1){
			if((obj.id.indexOf("window")==-1)&&(obj.id.indexOf("statusbar")==-1)) return;		
			if((obj.id.indexOf("statusbar")!=-1)&&(utils.navigatorName.indexOf("Microsoft") > -1)) obj = obj.parentNode;  
		}
		addListener(obj, "mousemove", eventMouseMove);

	}
	function eventMouseMove(evt){
		obj = (utils.navigatorName.indexOf("Microsoft") != -1) ? evt.srcElement : evt.currentTarget;
		if(utils.navigatorName.indexOf("Microsoft") != -1){
			//BugFix IE6 - No IE o evt (evento) retorna o objeto que o mouse se encontra e nao aquele que foi setado (window0) para disparar o evento
			if((obj.id.indexOf("window")==-1)&&(obj.id.indexOf("statusbar")==-1)) return;		
			if((obj.id.indexOf("statusbar")!=-1)&&(utils.navigatorName.indexOf("Microsoft") > -1)) obj = obj.parentNode;  
		}
		listener.fire(["mousemove", obj]);
	}
	function eventMouseUp(evt){
//		log("###### eventMouseUp");

		obj = (utils.navigatorName.indexOf("Microsoft") != -1) ? evt.srcElement : evt.currentTarget;
		if(utils.navigatorName.indexOf("Microsoft") != -1){
			if((obj.id.indexOf("window")==-1)&&(obj.id.indexOf("statusbar")==-1)) return;		
			if((obj.id.indexOf("statusbar")!=-1)&&(utils.navigatorName.indexOf("Microsoft") == -1)) obj = obj.parentNode;
		}
//		removeListener(obj, "mousedown", eventMouseDown);
		removeListener(obj, "mousemove", eventMouseMove);
		listener.unsubscribe(dragdrop.resizePanel);		
	}
	function eventClick(){
	}


/*
 	this.addListener(object, "mousedown", eventMouseDown, false);
	this.addListener(object, "mousemove", eventMouseMove, false);
	this.addListener(object, "click", eventClick, false);
*/	




