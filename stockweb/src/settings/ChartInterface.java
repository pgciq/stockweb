package settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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

import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ChartInterface {
	private String pathname = "";
	private Map<String, String> mapSetting = new HashMap<String, String>();
	private StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

	public ChartInterface(String pathname, Map<String, String> mapParameters) {
		this.pathname = pathname;
		this.mapSetting = mapParameters;
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
				sb.append(getContentFile(((Element) node).getAttribute("filename")));
			}
			checkNodes(node);
			sb.append("</" + node.getNodeName() + ">");
		}

	}

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

	public String getXmlSettings() {

		try {

			Document doc = getDoc(getContentFile("amstock_settings.xml"));
			Element elem = (Element) XPathAPI.selectSingleNode(doc,"//settings");
			sb.append("<" + elem.getNodeName() + ">");
			sb.append(getContentFile(elem.getAttribute("filename")));

			NodeList nodeList = elem.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeName().equals("#document") || node.getNodeName().equals("#text")) {
					continue;
				}

				String paramId = !((Element) node).getAttribute("id").equals("") ? " id='" + ((Element) node).getAttribute("id") + "' " : "";
				sb.append("<" + node.getNodeName() + paramId + ">");
				if (!((Element) node).getAttribute("filename").equals("")) {
					sb.append(getContentFile(((Element) node).getAttribute("filename")));
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

	private void init() {
		// mapSetting.put("chart", "");
	}

	public String loadSettings() {
		String xml = getXmlSettings();
		xml = xml.replaceAll("_STOCK_", mapSetting.get("stock"));
		return xml;
	}

}
