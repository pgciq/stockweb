package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import persistence.dao.ChartSettingEngineDAO;
import persistence.dao.ResourcesEngineDAO;
import persistence.impl.ChartSettingEngineImpl;
import persistence.session.ChartSettingEngineSession;
import persistence.vo.Script;

import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ChartInterfaceDynamic {
	private String pathname = "";
	private String chartName = "";
	private Map<String, String> mapSetting = new HashMap<String, String>();
	private StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	
	private ChartSettingEngineDAO chartDAO = null;
	

	public ChartInterfaceDynamic(String pathname, String chartName, Map<String, String> mapParameters) {
		this.pathname = pathname;
		this.mapSetting = mapParameters;
		this.chartName = chartName;
		chartDAO = new ChartSettingEngineDAO();
		mapSetting.put("column_setup", "date,open,high,low,close,volume");

	}

	private void checkNodes(Node node) throws Exception {
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			if (node.getNodeName().equals("#document") || node.getNodeName().equals("#text")) {
				continue;
			}

			String paramId = !((Element) node).getAttribute("id").equals("") ? " id='" + ((Element) node).getAttribute("id") + "' " : "";
			sb.append("<" + node.getNodeName() + paramId + ">");
			if (!((Element) node).getAttribute("filename").equals("")) {
				sb.append(applySettings(((Element) node).getAttribute("filename") , ((Element) node)));
			}
			checkNodes(node);
			sb.append("</" + node.getNodeName() + ">");
		}

	}

	private String applySettings(String filename, Element elem) throws Exception{

		String content = (new ResourcesEngineDAO()).getObjectByName(filename);
		if(elem.getAttribute("filename").equals("settings")){
			//_STRATEGYNAME_&stock=_STOCK_&agent=_AGENTS_
			content = content.replace("_STRATEGYNAME_", elem.getAttribute("strategyname"));
//			content = content.replace("_STOCK_", elem.getAttribute("stock"));
//			content = content.replace("_AGENTS_", elem.getAttribute("chartname"));
		}

		if(elem.getAttribute("filename").equals("templates_chart")){
			content = content.replace("_TITLE_", elem.getAttribute("chartname"));
		}

		if(elem.getAttribute("filename").indexOf("templates_graph") != -1){
			String params = elem.getAttribute("graphsetting");
			
			content = content.replace("_COLOR_", (params.length()==0) ? "" : params.substring(1,params.indexOf(",")));
			content = content.replaceAll("_GRAPHNAME_", elem.getAttribute("graphname"));
		}
		return content;
		
	}
	public String getXmlSettings() {

		try {

			Document doc = createAmstocksSettings();//getDoc(getContentFile("amstock_settings.xml"));
			Element elem = (Element) XPathAPI.selectSingleNode(doc,"//settings");
			sb.append("<" + elem.getNodeName() + ">");
			sb.append(applySettings(elem.getAttribute("filename"), elem));

			NodeList nodeList = elem.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeName().equals("#document") || node.getNodeName().equals("#text")) {
					continue;
				}

				String paramId = !((Element) node).getAttribute("id").equals("") ? " id='" + ((Element) node).getAttribute("id") + "' " : "";
				sb.append("<" + node.getNodeName() + paramId + ">");
				if (!((Element) node).getAttribute("filename").equals("")) {
					sb.append( applySettings(((Element) node).getAttribute("filename"), ((Element) node)) );
				}

				checkNodes(node);
				sb.append("</" + node.getNodeName() + ">");
			}

			sb.append("</" + elem.getNodeName() + ">");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();
	}

	public String loadSettings() {
		String xml = getXmlSettings();

		String columns = "";
		StringTokenizer tokens = new StringTokenizer(mapSetting.get("column_setup"),",");
		while(tokens.hasMoreTokens()){
			columns += "<column>"+tokens.nextToken()+"</column>";
		}
		
		xml = xml.replaceAll("_COLUMN_", columns);
		xml = xml.replaceAll("_STOCK_", mapSetting.get("stock"));
//		xml = xml.replaceAll("_AGENTS_", mapSetting.get("agents"));
		
		return xml;
	}

	private void init() {

	}
	
	private Document createAmstocksSettings() throws Exception{
		List<Script> lsResult = chartDAO.getListObject();
		int totCharts = lsResult.size();
//		String agentsScriptName = "", token = "";
		Document doc = null; 
		try {
//			doc = getDoc(getContentFile("amstock_settings.xml"));
			doc = getDoc((new ResourcesEngineDAO()).getObjectByName("templates_amstock_settings"));

			Node charts = XPathAPI.selectSingleNode(doc, "//charts");

			for(int nChart=0; nChart<totCharts; nChart++){
				Script script = lsResult.get(nChart);	
				//agentsScriptName += token + String.valueOf(lsResult.get(nChart).getId());
				//token = "_";
				
				String params = script.getParam();
				String settingChart = script.getSettingchart();
				StringTokenizer tkParams = new StringTokenizer(params,",");

				
		        Element chart = doc.createElement("chart");
		        chart.setAttribute("id", String.valueOf(nChart));
		        chart.setAttribute("filename", "templates_chart");
		        chart.setAttribute("type", "");
		        chart.setAttribute("chartname", script.getName());
		        charts.appendChild(chart);
		        
		        Element graphs = doc.createElement("graphs");
				StringTokenizer tkSettingChart = new StringTokenizer(settingChart,"|");

				int nGraph=0; 
				while(tkParams.hasMoreTokens()){
					String paramSettingChart = tkSettingChart.nextToken();
			        String graphname = tkParams.nextToken(); 
					Element graph = doc.createElement("graph");
			        graph.setAttribute("id", String.valueOf(nGraph));
			        graph.setAttribute("filename", (paramSettingChart.indexOf("histogram") != -1) ? "templates_graph_histogram":"templates_graph_line");
			        graph.setAttribute("graphname", graphname);
			        graph.setAttribute("graphsetting", paramSettingChart);
			        graphs.appendChild(graph);

			        mapSetting.put("column_setup", mapSetting.get("column_setup") + "," + graphname);

			        nGraph++;
				}
		        chart.appendChild(graphs);
				
			}
			
			//mapSetting.put("agents",agentsScriptName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}

	/*	private Document createAmstocksSettings(){
		Document doc = null; 
		try {
			doc = getDoc(getContentFile("amstock_settings.xml"));

			Node charts = XPathAPI.selectSingleNode(doc, "//charts");

			for(int nChart=0; nChart<5; nChart++){
		        Element chart = doc.createElement("chart");
		        chart.setAttribute("id", String.valueOf(nChart));
		        chart.setAttribute("filename", "chart.txt");
		        chart.setAttribute("type", "");
		        charts.appendChild(chart);
		        
		        Element graphs = doc.createElement("graphs");
		        chart.appendChild(graphs);
		        
				for(int nGraph=0; nGraph<5; nGraph++){ 
			        Element graph = doc.createElement("graph");
			        graph.setAttribute("id", String.valueOf(nGraph));
			        graph.setAttribute("filename", "graph.txt");
			        graphs.appendChild(graph);
				}
			}			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc;
	}
*/
	
/*
			<chart id="0" filename="chart_variationvolume.txt" type="volume">
				<graphs>
					<graph id="0" filename="graph_variationvolume.txt"></graph>
				</graphs>
			</chart>

	
	

	private String getContentFile(String filename) throws Exception {
		String xml = "";
		FileReader file = new FileReader(new File(this.pathname + filename));
		BufferedReader buffer = new BufferedReader(file);
		while (buffer.ready()) {
			xml += buffer.readLine();
		}
		buffer.close();
		return xml;
	}
*/
	private Document getDoc(String xml) throws Exception {
		String xmlString;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(xml)));
	}

	private String getXml(Document doc) throws Exception {
		Source source = new DOMSource(doc);
		StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(source, result);
		return stringWriter.getBuffer().toString();
	}
	
	

}
