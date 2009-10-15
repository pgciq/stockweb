// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

var jsRegion3 = {
    "main": [{
        "layout": [{
            "tag": "div",
            "id": "region03",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<div id='map_canvas' style='width: 180px; height: 100px'></div>",
            "style": ["top:10px", "left:10px", "width:200px", "height:200px", "cursor:default", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousedown": function(){
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar03",
            "params": [],
            "nodeRifer": "region03",
            "innerHTML": "",
            "style": ["top:-17px", "left:0px", "height:20px", "width:210px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('region03'), 'move');
                    document.getElementById('fixmodify002').src = 'img/fix_modify_enable.gif';
                },
                "onclick": ""
            }]
        }]
    
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar03",
            "params": [],
            "nodeRifer": "region03",
            "innerHTML": "",
            "style": ["top:15px", "left:0px", "height:15px", "width:210px", "backgroundImage:url(img/white-top-bottom.gif)","z-index:1"],
            "events": [{
                "onmousedown": function(){
//                    dragdrop.addEvent(document.getElementById('region03'), 'resize', buildDesign.renderize, ['region03', 'resize03', 'fixmodify03', 'menubar03', 'statusbar03']);
                },
                "onclick": ""
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "title",
            "params": [],
            "nodeRifer": "region03",
            "innerHTML": "<span id='title' class='title-blue'></span>",
            "style": ["position:absolute", "top:-15px", "left:10px"]
        }]
    
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize03",
            "params": [],
            "nodeRifer": "region03",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:15px", "left:190px"]
        
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "fixmodify03",
            "params": [],
            "nodeRifer": "region03",
            "innerHTML": "<img id='fixmodify003' src='img/fix_modify_enable.gif' width='14' height='16' onmousedown=\"this.src='img/fix_modify_disable.gif'\"/>",
            "style": ["position:absolute", "top:-15px", "left:190px"]
        }]
    }]
};


// ========================================================================================================				


var jsRegion2 = {
    "main": [{
        "layout": [{
            "tag": "div",
            "id": "region02",
            "params": [],
            "nodeRifer": "schema",
            "innerHTML": "<div id='map_canvas' style='width: 160px; height: 100px'></div>",
            "style": ["top:270px", "left:10px", "width:200px", "height:300px", "cursor:default", "backgroundColor:#f0f2f4"],
            "events": [{
                "onmousedown": function(){
                }
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "menubar02",
            "params": [],
            "nodeRifer": "region02",
            "innerHTML": "",
            "style": ["top:-17px", "left:0px", "height:20px", "width:210px", "backgroundImage:url(img/white-top-bottom.gif)"],
            "events": [{
                "onmousedown": function(){
                    dragdrop.addEvent(document.getElementById('region02'), 'move');
                    document.getElementById('fixmodify002').src = 'img/fix_modify_enable.gif';
                },
                "onclick": ""
            }]
        }]
    
    }, {
        "layout": [{
            "tag": "div",
            "id": "statusbar02",
            "params": [],
            "nodeRifer": "region02",
            "innerHTML": "",
            "style": ["top:15px", "left:0px", "height:15px", "width:210px", "backgroundImage:url(img/white-top-bottom.gif)","z-index:1"],
            "events": [{
                "onmousedown": function(){
                    //dragdrop.addEvent(document.getElementById('region02'), 'resize', buildDesign.renderize, ['region02', 'resize02', 'fixmodify02', 'menubar02', 'statusbar02']);
                },
                "onclick": ""
            }]
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "title",
            "params": [],
            "nodeRifer": "region02",
            "innerHTML": "<span id='title' class='title-blue'></span>",
            "style": ["position:absolute", "top:-15px", "left:10px"]
        }]
    
    }, {
        "layout": [{
            "tag": "div",
            "id": "resize02",
            "params": [],
            "nodeRifer": "region02",
            "innerHTML": "<img id='resize' src='img/resizediv.gif'/>",
            "style": ["position:absolute", "top:15px", "left:190px"]
        
        }]
    }, {
        "layout": [{
            "tag": "div",
            "id": "fixmodify02",
            "params": [],
            "nodeRifer": "region02",
            "innerHTML": "<img id='fixmodify002' src='img/fix_modify_enable.gif' width='14' height='16' onmousedown=\"this.src='img/fix_modify_disable.gif'\"/>",
            "style": ["position:absolute", "top:-15px", "left:190px"]
        }]
    }]
};



//===========================================================================================




