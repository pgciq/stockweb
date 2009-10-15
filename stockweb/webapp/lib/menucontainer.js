// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

// Precisa importar o framework para usar a classe de MAP			
			this.properties = function(){
				this.id = "";
				this.task = null;
				this.heightFix = 0;
				this.diffHeight = 0;
				this.status = "";
				this.count = 0;
			}

			this.Effect = function(){
				var item, differHeight, count;
				var mapSubMenu = new Map();

				this.addListener = function(element, event){
					object = document.getElementById("sub"+element);
					prop = new properties();
					prop.id = "sub" + element; 
					prop.heightFix = object.style["height"];
					prop.diffHeight = (object.style["height"].replace("px","")) / 10;
					mapSubMenu.put(object.id, prop);

					document.getElementById(element)["onclick"] = function(){
						var prop = mapSubMenu.get("sub" + this.id);
						prop.status = (prop.status == "open")?"close": "open";
						scroller(prop.id, prop.status);
					};
				}

				function scroller(id, status){
					var prop = mapSubMenu.get(id);
					prop.diffHeight = document.getElementById(id).style.height.replace("px","") / 10;
					item = document.getElementById(id).getElementsByTagName("li");
					var func = "";
					if (status == "open") {
						prop.count = item.length;
						func = "effect.scrollUp('"+id+"')";
						prop.task = setInterval(func,30);						
						return;
					}
					
					prop.count = -1;
					func = "effect.scrollDown('"+id+"')";
					prop.task = setInterval(func,30);
				}				
				
				this.scrollUp = function(id){
					try{
						var object = document.getElementById(id);
						var prop = mapSubMenu.get(id);
						prop.count--;
						object.style.height = (object.style.height.replace("px","") - prop.diffHeight) + "px";
						if (object.style.height.replace("px","") <= 0) {
							clearInterval(prop.task);
						}
						item[prop.count].style.visibility = "hidden";
					}catch(e){}
				}

				this.scrollDown = function(id){
					try{
						var object = document.getElementById(id);
						var prop = mapSubMenu.get(id);
						prop.count++;
						object.style.height = (parseInt(object.style.height.replace("px","")) + 20) + "px";
						if (object.style.height.replace("px","") >= 200) {
							clearInterval(prop.task);
						}
						item[prop.count].style.visibility = "visible";
					}catch(e){}
				}
			}


			var effect = new Effect();			
			effect.addListener("menuitem0", "onclick", "scroller()");
			effect.addListener("menuitem1", "onclick", "scroller()");

			