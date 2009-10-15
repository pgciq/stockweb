// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

// ---------------------- New version --------------------------
// JavaScript Document
this.Observer = function(){
    this.fns = [];
    
    this.subscribe = function(fn){
        this.fns.push(fn);
    }
    
    this.unsubscribe = function(fn){
        this.fns = this.fns.filter(function(el){
            if (el !== fn) {
                return el;
            }
        })
    }
    this.fire = function(o, thisObj){
        var scope = thisObj || window;
        
        function ForEach(fns, scope){
            var len = fns.length;
            for (var n = 0; n < len; n++) {
                var r = fns[n].call(scope, o);
                if (r !== undefined) 
                    return r;
            }
        }
		
		ForEach(this.fns, scope);
        
/* ---- BugFix IE6 - Array.forEach not supported 
        this.fns.forEach(function(el){
            alert("Observer.fire1");
            el.call(scope, o);
            alert("Observer.fire2");
        })
 */       
        
    }
}

	listener = new Observer();


