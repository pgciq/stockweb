// ----------------------------------------------------------
// @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
// @modify: 
// ----------------------------------------------------------

var Utils = function(){
	this.navigatorName = navigator.appName;
	this.numberVersion = navigator.appVersion;
	this.os = navigator.platform;
	this.chartStock;
	this.chartSettings;
	
	this.setChartStock = function(obj){
		this.chartStock = obj;
	}
	this.setChartSettings = function(obj){
		this.chartSettings = obj;
	}
	this.getChartStock = function(){
		return this.chartStock;
	}
	this.getChartSettings = function(){
		return this.chartSettings;
	}
	
	this.nextDay = function(data){
		
		while(true){
			data = this.addDay(data);
			if(!this.isDayWeekend(data))
				break;
		}
		return data
	}
	
	this.prevDay = function(data){
		
		while(true){
			data = this.rmDay(data);
			if(!this.isDayWeekend(data))
				break;
		}
		return data
	}

	// Add 1 day
	this.addDay = function(data){
		
	   var d = new Date();
	   var ano = parseInt(data.substr(0,4));
	   var mes = parseInt((data.substr(4,1)=="0")?data.substr(5,1):data.substr(4,2));
	   var dia = parseInt((data.substr(6,1) == "0") ? data.substr(7,1) : data.substr(6,2));

	   d.setFullYear(ano,mes-1,dia);
	   d.setDate(d.getDate()+1);

	   dia = d.getDate();
	   mes = d.getMonth()+1;
	   ano = d.getFullYear();
	   dia = (dia < 10) ? "0"+dia : dia;
	   mes = (mes < 10) ? "0"+mes : mes;

	   return ano+""+mes+""+dia;
	}
	
	// Remove 1 day
	this.rmDay = function(data){
		
	   var d = new Date();
	   var ano = parseInt(data.substr(0,4));
	   var mes = parseInt((data.substr(4,1)=="0")?data.substr(5,1):data.substr(4,2));
	   var dia = parseInt((data.substr(6,1) == "0") ? data.substr(7,1) : data.substr(6,2));

	   d.setFullYear(ano,mes-1,dia);
	   d.setDate(d.getDate()-1);

	   dia = d.getDate();
	   mes = d.getMonth()+1;
	   ano = d.getFullYear();
	   dia = (dia < 10) ? "0"+dia : dia;
	   mes = (mes < 10) ? "0"+mes : mes;

	   return ano+""+mes+""+dia;
	}

	this.currDate = function(){
		
	   var d = new Date();
//	   d.setDate(d.getDate()+1);

	   dia = d.getDate();
	   mes = d.getMonth()+1;
	   ano = d.getFullYear();
	   dia = (dia < 10) ? "0"+dia : dia;
	   mes = (mes < 10) ? "0"+mes : mes;

	   return ano+""+mes+""+dia;
	}

	this.dateSetup = function(data, intervalMonth){
		
		   var d = new Date();
		   var ano = parseInt(data.substr(0,4));
		   var mes = parseInt((data.substr(4,1)=="0")?data.substr(5,1):data.substr(4,2));
		   var dia = parseInt((data.substr(6,1) == "0") ? data.substr(7,1) : data.substr(6,2));

		   d.setFullYear(ano,mes-1,dia);

		   dia = d.getDate();
		   mes = (d.getMonth()+1)+intervalMonth;
		   ano = d.getFullYear();
		   
		   dia = (dia < 10) ? "0"+dia : dia;
		   mes = (mes < 10) ? "0"+mes : mes;

		   return ano+""+mes+""+dia;
	}


	this.dayWeek = function(data){

	   var ano = parseInt(data.substr(0,4));
	   var mes = parseInt((data.substr(4,1)=="0")?data.substr(5,1):data.substr(4,2));
	   var dia = parseInt((data.substr(6,1) == "0") ? data.substr(7,1) : data.substr(6,2));

	   var a = parseInt((14 - mes) / 12);
	   var y = ano - a;
	   var m = mes + parseInt(12*a) - 2;
	   var q = dia + parseInt(parseInt(31*m)/12) + y + parseInt(y/4) - parseInt(y/100) + parseInt(y/400);
	   var d = q % 7;

	   return d;
	}
	
	this.isDayWeekend = function(data){
	   var result = this.dayWeek(data);
	   return (result == 0 || result == 6);
	}

	this.descrDayWeek = function(data){
	   var result = this.dayWeek(data);
	   var days = ["domenica","lunedi","martedi","mercoledi","giovedi","venerdi","sabato"];
	   return days[result];
	}
}

var ChartStockSimulated = function(objUtil, interval, objName){
	this.dateI;
	this.dateF;
	this.utils = objUtil;
	this.intervalMonth = interval;
	this.frameName = objName;
	
	this.getDate = function(){
		return this.dateF;
	}
	this.setDate = function(data){
		this.dateI = this.utils.dateSetup(data, this.intervalMonth);
		this.dateF = data;
	}
	
	this.nextDay = function(){
		this.dateF = this.utils.nextDay(this.dateF);
		this.dateI = this.utils.dateSetup(this.dateF, this.intervalMonth);
		this.zoom();
	}
	
	this.prevDay = function(){
		this.dateF = this.utils.prevDay(this.dateF);
		this.dateI = this.utils.dateSetup(this.dateF, this.intervalMonth);
		this.zoom();
	}
	
	this.zoom = function(){
		if(this.frameName == "frameChartStock")
			top.frames.frameChartStock.setZoom(this.dateI, this.dateF);
		if(this.frameName == "frameChartSetting")
			top.frames.frameChartSetting.setZoom(this.dateI, this.dateF);
	}
	
}


utils = new Utils();
chartStockSimulated = new ChartStockSimulated(utils,-2,"frameChartStock");
chartStockChartSimulated = new ChartStockSimulated(utils,-2,"frameChartSetting");


