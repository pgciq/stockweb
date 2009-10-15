<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 
 @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 @modify: 
 -->
 
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<br>
<br>
<input type="button" value="Chart Summary" onclick="getChartSummary()">

<input type="button" value="Label On/Off" onclick="activeLabelOn()">
<br>

<center>
<input type="button" value="+" onclick="selectChart('montepremi'); addRecord('chart_montepremi');    reloadParameter('montepremi')">
<input type="button" value="-" onclick="selectChart('montepremi'); removeRecord('chart_montepremi'); reloadParameter('montepremi')">
&nbsp;&nbsp;&nbsp;
<input type="button" value="<<" onclick="selectChart('montepremi'); prevRecord('chart_montepremi');  reloadParameter('montepremi')">
<input type="button" value=">>" onclick="selectChart('montepremi'); nextRecord('chart_montepremi');  reloadParameter('montepremi')">
</center>
<div id="chart_montepremi" > </div>
<div id="chartSummary" > </div>




<br><br>
<input type="button" value="Label On/Off" onclick="activeLabelOn()">
<br>

<center>
<input type="button" value="+" onclick="selectChart('vinc3'); addRecord('chart_vinc3');    reloadParameter('vinc3')">
<input type="button" value="-" onclick="selectChart('vinc3'); removeRecord('chart_vinc3'); reloadParameter('vinc3')">
&nbsp;&nbsp;&nbsp;
<input type="button" value="<<" onclick="selectChart('vinc3'); prevRecord('chart_vinc3');  reloadParameter('vinc3')">
<input type="button" value=">>" onclick="selectChart('vinc3'); nextRecord('chart_vinc3');  reloadParameter('vinc3')">
</center>
<div id="chart_vinc3" > </div>


<script>

      var rcValues, rcLabels;  // Lista di record per creare il chart
      var widthChart = "1245";
      var heightChart = "200";
      var divChart = "charts";
    var actLabelOn = "true";
    var labelVisible = "true";

    var values_montepremi = '<c:out value="${montepremi}"/>';
    var labels_montepremi = '<c:out value="${periodo_montepremi}"/>';
    var totRecordMontepremi = 20;
    var startRecordMontepremi = 0;

    var values_vinc3 = '<c:out value="${vinc3}"/>';
    var labels_vinc3 = '<c:out value="${periodo_vinc3}"/>';
    var totRecordVinc3 = 20;
    var startRecordVinc3 = 0;


    var totRecord = 20;
    var startRecord = 0;
    var parameterString = "";
    var xmlHttp;
    
    
    var values = "";
    var labels = "";

//link =  "http://localhost:8101/ChartServlet?" +
pathUrl = "<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>";
link =  pathUrl + "/ChartServlet?" +
        "chart=line&" +
        "width=$width&" +
        "height=$height&" +
        "background=white&" +
        "valueLinesOn=true&" +
        "rangeAxisLabel=_&" +
        "rangeAxisLabelAngle=270&" +
        "sampleValues=$values&" +
        "sampleAxisLabel=_&" +
        "sampleLabels=$labels&" +
        "sampleLabelAngle=270&" +
        "autoLabelSpacingOn=true&" +
        "sampleLabelsOn=$labelVisible&" +
        "valueLabelsOn=$actLabelOn";

  function activeLabelOn(){
      actLabelOn = (actLabelOn == "true") ? "false" : "true";
      applyAddRemove();
  }
 
  function selectChart(name){
    if(name == "montepremi"){
        values = values_montepremi;  
        labels = labels_montepremi;  
        totRecord = totRecordMontepremi;
        startRecord = startRecordMontepremi;
    }
    
    if(name == "vinc3"){
        values = values_vinc3;  
        labels = labels_vinc3;
        totRecord = totRecordVinc3;
        startRecord = startRecordVinc3;
    }
  }
 
  function reloadParameter(name){
    if(name == "montepremi"){
        totRecordMontepremi = totRecord;
        startRecordMontepremi = startRecord;
    }
    
    if(name == "vinc3"){
        totRecordVinc3 = totRecord;
        startRecordVinc3 = startRecord;
    }
 
  }
 
 
  function loadChart(){
          showMessage("005");
        var newImg=document.createElement("img");
        newImg.name = "test";
        newImg.src = pathUrl + "/servlet/RequestChartServlet?action=getimg&id=" + imgLink;
          showMessage("005.1");
        document.getElementById(divChart).innerHTML = " ";
          showMessage("005.2");
        document.getElementById(divChart).appendChild(newImg);
          showMessage("005.3");
  }
 
  function getChartSummary(){
    showMessage("001.1");
    tmpLabelOn = actLabelOn;
    tmpTotRecord = totRecord;
    heightChart = "80";
    labelVisible = "false";
    divChart = "chartSummary";
    actLabelOn = "false";
    totRecord = values.split(",").length/3;
    showMessage(values.split(",").length/3);
    applyAddRemove();
    actLabelOn = tmpLabelOn;
    totRecord = tmpTotRecord;
  }
 
  function addRecord(divName){
    heightChart = "200";
      divChart = divName;
    labelVisible = "true";
      totRecord += 1;
      applyAddRemove();
  }
  function removeRecord(divName){
    heightChart = "200";
      divChart = divName;
    labelVisible = "true";
      totRecord -= 1;
      applyAddRemove();
  }

  function nextRecord(divName){
    heightChart = "200";
      divChart = divName;
    labelVisible = "true";
//    if(startRecord)
      startRecord += 1;
      totRecord += 1;
      applyAddRemove();
  }

  function prevRecord(divName){
    heightChart = "200";
      divChart = divName;
    labelVisible = "true";
    if(startRecord > 0)
          startRecord -= 1;
      totRecord -= 1;
      applyAddRemove();
  }


  function applyAddRemove(){
    showMessage("003");
 
      rcValues = "";
      rcLabels = "";
 
      var lsValues, lsLabels;
      lsLabels = labels.split(",");
      lsValues = values.split(",");

    var token = "";
      for(var i=startRecord; i<totRecord; i++){
        rcValues += token + lsValues[i];              
        rcLabels += token + lsLabels[i];
        token = ",";
      }
      
//      parameterString = "action=getid&link=" + escape(link.replace("$values", rcValues).replace("$labels", rcLabels).replace("$actLabelOn",actLabelOn));
      getImageLink();
    }

  function ajaxFunction(){
     try{
          // Firefox, Opera 8.0+, Safari
          xmlHttp=new XMLHttpRequest();
         }
        catch (e){
          // Internet Explorer
          try{
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
            }catch (e){
                try{
                      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                  }catch (e){
                      alert("Your browser does not support AJAX!");
                      return false;
                  }
            }
         }
      
        xmlHttp.onreadystatechange=function(){
            if(xmlHttp.readyState==4){
                  imgLink = xmlHttp.responseText.replace(/\t/g,"").replace(/\r/g,"");
                  loadChart();
              }else{ showMessage("ERROR: " + xmlHttp.readyState + " - " + xmlHttp.responseTex)}
             
        }
  }    
    var count = 0;
    function getImageLink(){
        ajaxFunction();    
          showMessage("004");
        count++;
        parameterString = "opc="+count+"&action=getid&link=" + escape(link.replace("$values", rcValues).replace("$labels", rcLabels).replace("$actLabelOn",actLabelOn).replace("$width",widthChart).replace("$height",heightChart).replace("$labelVisible",labelVisible));
        //alert(unescape(parameterString));    

          xmlHttp.open("POST","/servlet/RequestChartServlet", true);
          showMessage("004.1");
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        
//        xmlHttp.setRequestHeader("MessageType", "CALL");
        
          showMessage("004.2");
        xmlHttp.setRequestHeader("Content-length",parameterString.length);
//        xmlHttp.setRequestHeader("Content-length","image/png");
          showMessage("004.3");
//        xmlHttp.setRequestHeader("Connection", "close");  
         //xmlHttp.open("POST","http://localhost:8101/servlet/RequestChartServlet?action=getimg&id=" + );
          showMessage("004.4");
        xmlHttp.send(parameterString);
          showMessage("004.5");
        
    }

    
  ajaxFunction();
    
</script>



<script>
  function showMessage(msg){
      //alert(msg);
  }


</script>
    </body>
</html>


