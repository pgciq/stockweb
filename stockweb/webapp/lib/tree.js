// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------
			
            function flatten(treeid, action){ 
                var ultags = document.getElementById(treeid).getElementsByTagName("ul");
                for (var i = 0; i < ultags.length; i++) {
                    ultags[i].style.display = (action == "expand") ? "block" : "none";
                    var relvalue = (action == "expand") ? "open" : "closed";
                    ultags[i].setAttribute("rel", relvalue);
                }
            }
            
            function createTree(treeid){
                var ultags = document.getElementById(treeid).getElementsByTagName("ul");
                for (var i = 0; i < ultags.length; i++) 
                    buildSubTree(treeid, ultags[i], i);
            }
            
            
            function buildSubTree(treeid, ulelement, index){
                ulelement.parentNode.className = "submenu";

                ulelement.parentNode.onclick = function(e){
					var submenu = this.getElementsByTagName("ul")[0];
					submenu.style.display = (submenu.getAttribute("rel") == "closed")?"block":"none";
					var rel = (submenu.getAttribute("rel") == "closed")?"open":"closed";
					submenu.setAttribute("rel", rel);
                }
                
                ulelement.onclick = function(e){
                    preventpropagate(e)
                }
            }
            
            function preventpropagate(e){ //prevent action from bubbling upwards
                if (typeof e != "undefined") 
                    e.stopPropagation()
                else 
                    event.cancelBubble = true
            }