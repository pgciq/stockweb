
// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

// --------------------  ScriptEngine --------------------
var $script = {
  id:0,
  name:"",
  script:"",
  descr:"",
  param:"",

  load: function(type, object) {
  	var field = ["id","name","script","descr","param"];
  	
  	for(var x=0; x<field.length; x++){
	  	if(type == "input=object")
	  		dwr.util.setValue((field[x]+"_script"), object[field[x]]);
	  	if(type == "object=input")
	  		this[field[x]] = dwr.util.getValue(field[x] + "_script");
	  		
  	}
  },


  clear: function() {
  	var field = ["id","name","script","descr","param"];
  	for(var i=0; i<field.length; i++)
			this[field[i]+"_script"] = "";
		dwr.util.setValues(this, this);
  },

  save: function() {
		//dwr.util.getValues(this);
 		this.load("object=input"); 		
		ScriptEngineImpl.save(this, null);
		this.list();
  },

  rm: function() {
		dwr.util.getValues(this);
		ScriptEngineImpl.remove(this, null);
		this.list();		

  },

  list: function() {
	    ScriptEngineImpl.list(function(scripts) {
	      var account;
	      var tableName = ["rsTableScript","rsTableDataScript"];

	      document.getElementById(tableName[0]).innerHTML = "";
	      document.getElementById(tableName[1]).innerHTML = "";
	      
	      createHeaderTable("title", "", ["<b>Name</b>","<b>Return</b>"], ["200px","300px"], tableName);
	      for (var i = 0; i < scripts.length; i++) {
	        script = scripts[i];
	        
					var field = [script.name, script.param];
					var fieldLength = ["200px","300px"];
					createHeaderTable("result", script.id, field, fieldLength, tableName, $script );
					

	      }
	    });
   },
   
  get: function(key) {
  	if(key == null){
  		key = dwr.util.getValue("name_script");
  	}
  	fieldKey = ((isNaN(key))?"name":"id");
  	dwr.util.setValue( fieldKey + "_script",key);
    this.load("object=input");
    
    if(fieldKey == "id"){
	    ScriptEngineImpl.getById(this, function(script) {
					$script.load("input=object", script);    
	    });
	  }
    if(fieldKey == "name"){
	    ScriptEngineImpl.get(this, function(script) {
					$script.load("input=object", script);    
	    });
	  }
	  
	  
  },   
   
    
  getx: function(key, func) {

  	if(key == null){
  		key = dwr.util.getValue("name");
  		name = key;
  	}else{
  		dwr.util.setValue("id",key);
	  }
	  
    dwr.util.getValues(this);
 		if(func!=null){
			  ScriptEngineImpl.get(this, func);
		 }else{
			    ScriptEngineImpl.getById(this, function(script) {
							dwr.util.setValues(script);
			    });
	    }
  }
    

  };



// --------------------  Device --------------------
var $device = {
  imei : "",
  userId : "",
  model : "",
  visible : "",
  datetime : "",
  note : "",
  login:"",
  
  load: function(type, object) {
  	var field = ["imei","userId","model","visible","datetime","note","login"];
  	
  	for(var x=0; x<field.length; x++){
	  	if(type == "input=object")
	  		dwr.util.setValue((field[x]+"_device"), object[field[x]]);
	  	if(type == "object=input")
	  		this[field[x]] = dwr.util.getValue(field[x] + "_device");

  		if(field[x] == "login")
  			alert("login = " + this.login + " - userId = " + this.userId);
	  		
  	}
  },

  clear: function() {
  
  	var field = ["imei_device","userId_device","model_device","visible_device","datetime_device","note_device","login_device"];
  	for(var i=0; i<field.length; i++)
			this[field[i]] = "";
		dwr.util.setValues(this, this);
  },

  save: function() {
//		dwr.util.getValues(this);
		this.load("object=input");
		DeviceImpl.save(this, null);
		this.list();
  },

  rm: function() {
		dwr.util.getValues(this);
		DeviceImpl.remove(this, null);
		this.list();		

  },

  list: function() {
  		dwr.util.getValues(this);
	    DeviceImpl.list(this, function(devices) {
	      var device;
	      var tableName = ["rsTableDevice","rsTableDataDevice"];
	      var fieldLength = ["110px","100px","60px","50px","80px"];
	      
	      document.getElementById(tableName[0]).innerHTML = "";
	      document.getElementById(tableName[1]).innerHTML = "";
	      
	      createTable("title", "", ["<b>Imei</b>","<b>Login</b>","<b>Modelo</b>","<b>Ativo</b>","<b>Data</b>"], fieldLength, tableName);
	      for (var i = 0; i < devices.length; i++) {
		        device = devices[i];
						var field = [device.imei, device.login, device.model, device.visible, device.datetime];
						
						createTable("result", device.imei, field, fieldLength, tableName, $device);
	      }
	    });
   },

  get: function(key) {
  	if(key == null){
  		key = dwr.util.getValue("imei_device");
  	}
  	dwr.util.setValue("imei_device",key);
    this.load("object=input");
    //dwr.util.getValues(this);
    DeviceImpl.get(this, function(device) {
				$device.load("input=object", device);    
				//dwr.util.setValues(device);
    });
  }
    
  };


// --------------------  Enterprise --------------------
var $enterprise = {
  name : "",
  telContact :"",
  state : "",
  country : "",
  note : "",
  email:"",
	activate:"",
	
  clear: function() {
  	var field = ["name","telContact","state","country","note","email"];
  	for(var i=0; i<field.length; i++)
			this[field[i]] = "";
		dwr.util.setValues(this, this);
  },

  save: function() {
		dwr.util.getValues(this);
		EnterpriseImpl.save(this, null);
		this.list();
  },

  rm: function() {
		dwr.util.getValues(this);
		EnterpriseImpl.remove(this, null);
		this.list();		

  },

  list: function() {
	    EnterpriseImpl.list(function(enterprises) {
	      var enterprise;

	      var tableName = ["rsTableEnterprise","rsTableDataEnterprise"];
	      document.getElementById(tableName[0]).innerHTML = "";
	      document.getElementById(tableName[1]).innerHTML = "";
	      
	      var fieldLength = ["100px","80px","200px","40px","40px"];
	      createTable("title", "", ["<b>Empresa</b>","<b>Telefone</b>","<b>Email</b>","<b>Estado</b>","<b>Pais</b>"], fieldLength, tableName);
	      for (var i = 0; i < enterprises.length; i++) {
	        enterprise = enterprises[i];
	        
					var field = [enterprise.name, enterprise.telContact,enterprise.email,enterprise.state,enterprise.country];
					createTable("result", enterprise.name, field, fieldLength, tableName, $enterprise);

	      }
	    });
   },
    
  get: function(key, type, func) {

  	if(key == null){
  		key = dwr.util.getValue("name");
  	}
  	dwr.util.setValue("name",key);
    dwr.util.getValues(this);
 		if(func!=null){
			  EnterpriseImpl.get(this, func);
		 }else{
			    EnterpriseImpl.get(this, function(account) {
							dwr.util.setValues(account);
							if(dwr.util.getValue("activate") == "0")
								document.getElementById("act").checked = false;
							else
								document.getElementById("act").checked = true;
			    });
	    }
  }
    

  };



// --------------------  Employee --------------------
var $employee = {
  firstName : "",
  lastName : "",
  email : "",
  login : "",
  password : "",
  businessId : "",
  profileId : "",
	activate:"",
	telContact:"",
	note:"",
	profileName:"",
	businessName:"",
	
  clear: function() {
  	var field = ["firstName","lastName","email","login","password","businessId","businessName","profileId","profileName","activate","telContact","note"];
  	for(var i=0; i<field.length; i++)
			this[field[i]] = "";
		dwr.util.setValues(this, this);
  },

  save: function() {
    dwr.util.setValue("profileId", dwr.util.getValue("profileName"));
    dwr.util.setValue("businessId", dwr.util.getValue("businessName"));
		dwr.util.getValues(this);
		EmployeeImpl.save(this, function(){$employee.clear(); $employee.list();});
  },

  rm: function() {
		dwr.util.getValues(this);
		EmployeeImpl.remove(this, function(){$employee.list();});
				

  },

  list: function() {
	    EmployeeImpl.list(function(employees) {
	      var employee;
	      var tableName = ["rsTableEmployee","rsTableDataEmployee"];
	      document.getElementById(tableName[0]).innerHTML = "";
	      document.getElementById(tableName[1]).innerHTML = "";

	      var fieldLength = ["50px","100px","150px","100px"];
	      createTable("title", "", ["<b>Ativo</b>","<b>Login</b>","<b>Nome</b>","<b>Empresa</b>"], fieldLength, tableName);
	      for (var i = 0; i < employees.length; i++) {
	        employee = employees[i];
					var field = [((employee.activate==1)?"true":"false"), employee.login, employee.firstName + " " + employee.lastName, employee.businessName];
					createTable("result", employee.login, field, fieldLength, tableName, $employee);

	      }
	    });
   },
    
  listBusiness: function() {
	    EnterpriseImpl.list(function(enterprises) {
	    	var option = "";
	    	var select = "<select id='businessName' style='width:150px; font-size:12px; font-family:\"Courier New\", Courier, monospace;'>";
	      for (var i = 0; i < enterprises.length; i++) {
	        enterprise = enterprises[i];
					option += "<option value='" + enterprise.id + "'>" + enterprise.name + "</option>";					
				}
				select += option + "</select>"; 	 
				document.getElementById("businessdiv").innerHTML = select;   	
	    });
   },
    
  listProfile: function() {
	    ProfileImpl.list(function(profiles) {
	    	var option = "";
	    	var select = "<select id='profileName' style='width:150px; font-size:12px; font-family:\"Courier New\", Courier, monospace;' >";
	      for (var i = 0; i < profiles.length; i++) {
	        profile = profiles[i];
					option += "<option value='" + profile.id + "'>" + profile.name + "</option>";					
				}
				select += option + "</select>"; 	 
				document.getElementById("profilediv").innerHTML = select;   	
	    });
   },


  get: function(key, func) {

  	if(key == null){
  		key = dwr.util.getValue("login");
  	}
  	dwr.util.setValue("login",key);
    dwr.util.getValues(this);
 		if(func!=null){
			  EmployeeImpl.get(this, func);
		 }else{
			    EmployeeImpl.get(this, function(employee) {
							dwr.util.setValues(employee);
							if(dwr.util.getValue("activate") == "0")
								document.getElementById("act").checked = false;
							else
								document.getElementById("act").checked = true;
			    });
	    }
  }
  };


// --------------------  LoggerPoints --------------------
var $loggerPoints = {
  imei : "",
  latitude : "",
  longitude : "",
  date : "",
  time : "",
  speed : "",
  direction : "",
  typeService : "",

  save: function() {
		dwr.util.getValues(this);
    LoggerPointsImpl.save(this, null);
    alert("SAVE - OK"); 
  },

  get: function() {
  	dwr.util.getValues(this);
    this.name = dwr.util.getValue("name");
    LoggerPointsImpl.get(this, function(points) {
	      document.write("imei = " + points.imei);
    });
  },


  list: function() {
  	dwr.util.getValues(this);
  	
    LoggerPointsImpl.list(dwr.util.getValue("imei"), dwr.util.getValue("dateI"), dwr.util.getValue("dateF"), function(loggerPoints) {
			alert(loggerPoints.length);
      var points;
      var result=""; //Solo per test
      for (var i = 0; i < loggerPoints.length; i++) {
        points = loggerPoints[i];
      	document.write(points.imei);  //Solo per test
      }
      
    });
    }

  };

// --------------------  Notification --------------------
var $notification = {
  employeeId : 0,
  description : "",
  imeiDevice : "",
  status : "",

  save: function() {
		dwr.util.getValues(this);
    NotificationImpl.save(this, null);
    alert("SAVE- OK");  //Solo per test
  },

  list: function() {
    NotificationImpl.list(function(notification) {
      var notification;
      var result=""; //Solo per test
      for (var i = 0; i < notification.length; i++) {
        notification = notification[i];
        result = result + notification.employeeId + "<br>";
      }
      document.write(result);  //Solo per test
    });
    }

  };

// --------------------  Profile --------------------
var $profile = {
  name:"",
  note:"",
  codeAuth:"",
  
  clear: function() {
  	var field = ["name","note","codeAuth"];
  	for(var i=0; i<field.length; i++)
			this[field[i]] = "";
		dwr.util.setValues(this, this);
  },

  save: function() {
		dwr.util.getValues(this);
		ProfileImpl.save(this, function(){$profile.clear(); $profile.list();});
  },

  rm: function() {
		dwr.util.getValues(this);
		ProfileImpl.remove(this, function(){$profile.clear(); $profile.list();});
  },

  list: function() {
	    ProfileImpl.list(function(profiles) {
	      var device;
	      var tableName = ["rsTableProfile","rsTableDataProfile"];
	      document.getElementById(tableName[0]).innerHTML = "";
	      document.getElementById(tableName[1]).innerHTML = "";
	      var fieldLength = ["300px","100px"];
	      createTable("title", "", ["<b>Nome do Perfil</b>","<b>Codigo Perfil</b>"], fieldLength, tableName);
	      for (var i = 0; i < profiles.length; i++) {
		        profile = profiles[i];
						var field = [profile.name, profile.codeAuth];
						createTable("result", profile.name, field, fieldLength, tableName, $profile);
	      }
	    });
   },

  get: function(key) {
  	if(key == null){
  		key = dwr.util.getValue("name");
  	}
  	dwr.util.setValue("name",key);
    dwr.util.getValues(this);
 		
    ProfileImpl.get(this, function(profile) {
				dwr.util.setValues(profile);
    });
  }
    
  };


function createTable(type, scripts, fieldLength, tablesname, object){

	className = (type == "result")?"label":"labelRis";
	tableName = (type == "result")?tablesname[1]:tablesname[0];
	
	 var objTable = document.createElement("TABLE");
   for (var i = 0; i < scripts.length; i++) {
     script = scripts[i];
        
 		 var field = [script.name, script.param];
		
			var objTR = document.createElement("TR");
			objTable.cellspacing="0";
			objTable.style["cursor"]="pointer";
			objTR.align="left";
		
			if(type != "result"){
				objTable.style["borderStyle"] = "double";
			}else{
				objTR["onmousemove"] = function(){this.style.backgroundColor='#CCCCCC'};
				objTR["onmouseout"] = function(){this.style.backgroundColor='#FFFFFF'};	
				objTR["onclick"] = function(){ object.get(script.id) };	
			}
			
			for(var x=0; x < field.length; x++){
				var objTD = document.createElement("TD");
				objTD.style["width"]=fieldLength[x];
				objTD.style["font-size"]="14px";
				objTD.className=className;
				objTD.innerHTML = field[x];
				objTR.appendChild(objTD);	
			}
			objTable.appendChild(objTR);
	}
	document.getElementById(tableName).appendChild(objTable);

}



function createHeaderTable(type, key, field, fieldLength, tablesname, object){

	className = (type == "result")?"label":"labelRis";
	tableName = (type == "result")?tablesname[1]:tablesname[0];
	
	var objTable = document.createElement("TABLE");
	var objTR = document.createElement("TR");
	objTable.cellspacing="0";
	objTable.style["cursor"]="pointer";
	objTR.align="left";

	if(type != "result"){
		objTable.style["borderStyle"] = "double";
	}else{
		objTR["onmousemove"] = function(){this.style.backgroundColor='#CCCCCC'};
		objTR["onmouseout"] = function(){this.style.backgroundColor='#FFFFFF'};	
		objTR["onclick"] = function(){ object.get(key) };	
	}
	
	for(var i=0; i < field.length; i++){
		var objTD = document.createElement("TD");
		objTD.style["width"]=fieldLength[i];
		objTD.style["font-size"]="14px";
		objTD.className=className;
		objTD.innerHTML = field[i];
		objTR.appendChild(objTD);	
	}
	
	objTable.appendChild(objTR);
	document.getElementById(tableName).appendChild(objTable);

}


