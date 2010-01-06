package servlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import persistence.dao.ChartSettingEngineDAO;
import persistence.vo.Script;

import ai.TestAI;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Servlet implementation class RequestAgents
 */
public class RequestAgentsScripts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Properties prop = null;

	public RequestAgentsScripts() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void formatterResult(TestAI object, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StringBuffer sbResultHistory = null;
		StringBuffer sbResultScripts = null;

		StringTokenizer tkResultHistory = null;
		StringTokenizer tkResultScripts = null;

		String agent = request.getParameter("agent");

//		if (agent.indexOf("history") >= 0) {
			sbResultHistory = formatterResultCandle(object.getResponseAgents("HistoryStocksAgent"), request, response);
			tkResultHistory = new StringTokenizer(sbResultHistory.toString(), "\n");
//		}

		if (agent.indexOf("scripts") >= 0) {
			sbResultScripts = formatterResultScripts(object.getResponseAgents("ExecuteScriptAgent"), request, response);
			tkResultScripts = new StringTokenizer(sbResultScripts.toString(), "\n");
		}

		try {
			PrintWriter out = response.getWriter();
			while (tkResultHistory.hasMoreTokens()
					&& tkResultScripts.hasMoreTokens()
				  ) {

//				if (agent.indexOf("history") >= 0) {
					out.print(tkResultHistory.nextToken());
//				}
				if (agent.indexOf("scripts") >= 0) {
					out.print("," + tkResultScripts.nextToken());
				}

				out.println("");
			}
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private StringBuffer formatterResultScripts(String history, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapResult = new HashMap<String,String>();
		StringBuffer sbResult = new StringBuffer();
		String token = "";
		List<Script> lsObject = (new ChartSettingEngineDAO()).getListObject();
		
/*		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			String date = result.substring(result.indexOf("date:")+5,8);
			mapResult.put(date, result);
		}
*/
		String lastDate = null;
		StringTokenizer tokens = new StringTokenizer(history, "|");
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);
			
			try{
				if(result.equals("{}"))
					continue;
				if((lastDate!=null) && (!lastDate.equals(json.getString("date")))){
					sbResult.append("\n");
					token = "";
				}
				
				lastDate = json.getString("date");

				for(Script script : lsObject){
						if(result.indexOf(script.getName()) == -1) 
							continue;
						
						StringTokenizer tkParams = new StringTokenizer(script.getParam(), ",");
						while(tkParams.hasMoreTokens()){
							sbResult.append(token + String.valueOf(json.get(tkParams.nextToken())));
							token = ",";
						}

					}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		sbResult.append("\n");
		return sbResult;

	}

	private StringBuffer formatterResultCandle(String history, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sbResult = new StringBuffer();

		StringTokenizer tokens = new StringTokenizer(history, "|");
		String tmp = "";
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);

			sbResult.append(String.valueOf(json.get("date")));
			sbResult.append("," + String.valueOf(json.get("open")));
			sbResult.append("," + String.valueOf(json.get("high")));
			sbResult.append("," + String.valueOf(json.get("low")));
			sbResult.append("," + String.valueOf(json.get("close")));
			sbResult.append("," + String.valueOf(json.get("vol")));
			sbResult.append("\n");

		}

		return sbResult;
	}

	private String getLastData(String data) {
		String dateFormat = "yyyyMMdd";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(data.substring(0, 4)), Integer.parseInt(data.substring(4, 6)) - 1, Integer.parseInt(data.substring(6, 8)));
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return sdf.format(calendar.getTime());
	}

	private void loadStockName() {
		try {
			if (prop != null) {
				return;
			}

			prop = new Properties();
			String filename = getServletContext().getRealPath("finance_yahoo.properties").toString();
			prop.load(new FileReader(new File(filename)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {

		try{
			System.setProperty("http.proxyHost", "proxypac.sisal.it");
			System.setProperty("http.proxyPort", "80");
			System.setProperty("http.proxyUsername", "rocha");
			System.setProperty("http.proxyPassword", "9876543210");
	
			loadStockName();
	
			String pathname = this.getServletContext().getRealPath("/") + "resources\\";
			TestAI testAI = new TestAI(pathname);
			// interval = d(diario), w(semanal), m(mensal)
	
			if (request.getParameter("stock") != null) {
				testAI.executeAgent(
								request.getParameter("stock"),
								"d",
								"20090701",
								"20101213",
								"HistoryStocksAgent,BollingerAgent,ExecuteScriptAgent");
			} else {
				testAI.executeAgents(
								prop,
								"d",
								"20090701",
								"20101213",
								"HistoryStocksAgent,BollingerAgent,ExecuteScriptAgent");
			}
	
			// testAI.getResponseAgents(getAgentName(request.getParameter("agent")) + "Agent")
			formatterResult(testAI, request, response);
	
			// formatterResultCandle(testAI.getResponseAgents("BollingerAgent"),request,response);
	
			System.out.println(">>>>>>> " + testAI.getResponseAgents("HistoryStocksAgent"));
//			System.out.println(">>>>>>> " + testAI.getResponseAgents("BollingerAgent"));
			System.out.println(">>>>>>> " + testAI.getResponseAgents("ExecuteScriptAgent"));
			System.out.println("Pathname = " + pathname);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}


/*
	List<Script> lsObject = new ArrayList<Script>((new ChartSettingEngineDAO()).getListObject());
*/