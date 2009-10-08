package servlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import settings.ChartInterface;

import com.twolattes.json.Json;

import ai.TestAI;

/**
 * Servlet implementation class RequestAgents
 */
public class RequestAgents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Properties prop = null;
    public RequestAgents() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void loadStockName(){
		try{
			if(prop != null)
				return;
			
			prop = new Properties();
			String filename = getServletContext().getRealPath("finance_yahoo.properties").toString();
			prop.load(new FileReader(new File(filename)));
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.setProperty("http.proxyHost", "proxypac.sisal.it");
		System.setProperty("http.proxyPort", "80");
		System.setProperty("http.proxyUsername", "rocha");
		System.setProperty("http.proxyPassword", "987654321");
		
		loadStockName();		
		
		String pathname = this.getServletContext().getRealPath("/")+"resources\\";
		TestAI testAI = new TestAI(pathname);
		//interval = d(diario), w(semanal), m(mensal)
		
		if(request.getParameter("stock") != null)
			testAI.executeAgent(request.getParameter("stock"), "d", "20080101", "20101213", "HistoryStocksAgent,CandlestickAgent,BollingerAgent,MaxMinIndexAgent,VariationVolumeAgent,VariationPriceIndexAgent,ExecuteScriptAgent");
		else	
			testAI.executeAgents(prop, "d", "20080501", "20101213", "HistoryStocksAgent,CandlestickAgent,BollingerAgent,MaxMinIndexAgent,VariationVolumeAgent,VariationPriceIndexAgent,ExecuteScriptAgent");

		//testAI.getResponseAgents(getAgentName(request.getParameter("agent")) + "Agent")
		formatterResult(testAI,request,response);

		//		formatterResultCandle(testAI.getResponseAgents("BollingerAgent"),request,response);
		
		System.out.println(">>>>>>> " + testAI.getResponseAgents("HistoryStocksAgent"));
		System.out.println(">>>>>>> " + testAI.getResponseAgents("BollingerAgent"));
		System.out.println(">>>>>>> " + testAI.getResponseAgents("MaxMinIndexAgent"));
		System.out.println(">>>>>>> " + testAI.getResponseAgents("VariationVolumeAgent"));
		System.out.println(">>>>>>> " + testAI.getResponseAgents("VariationPriceIndexAgent"));
		System.out.println(">>>>>>> " + testAI.getResponseAgents("ExecuteScriptAgent"));
		System.out.println("Pathname = " + pathname);		
	}
	
	private void formatterResult(TestAI object, HttpServletRequest request, HttpServletResponse response){
		StringBuffer sbResultHistory = null;
		StringBuffer sbResultBollinger = null;
		StringBuffer sbResultVarVolume = null;
		StringBuffer sbResultVarPriceIndex = null;
		StringBuffer sbResultMaxMinIndex = null;
		

		StringTokenizer tkResultHistory = null;
		StringTokenizer tkResultBollinger = null;
		StringTokenizer tkResultVarVolume = null;
		StringTokenizer tkResultVarPriceIndex = null;
		StringTokenizer tkResultMaxMinIndex = null;
		
		String agent = request.getParameter("agent");

		if(agent.indexOf("history") >= 0){
			sbResultHistory = formatterResultCandle(object.getResponseAgents("HistoryStocksAgent"), request, response);
			tkResultHistory = new StringTokenizer(sbResultHistory.toString(), "\n");		
		}

		if(agent.indexOf("bollinger") >= 0){
			sbResultBollinger = formatterResultBollinger(object.getResponseAgents("BollingerAgent"), request, response);
			tkResultBollinger = new StringTokenizer(sbResultBollinger.toString(), "\n");		
		}

		if(agent.indexOf("maxminindex") >= 0){
			sbResultMaxMinIndex = formatterResultMaxMinIndex(object.getResponseAgents("MaxMinIndexAgent"), request, response);
			tkResultMaxMinIndex = new StringTokenizer(sbResultMaxMinIndex.toString(), "\n");
		}

		if(agent.indexOf("variationvolume") >= 0){
			sbResultVarVolume = formatterResultVariationVolume(object.getResponseAgents("VariationVolumeAgent"), request, response);
			tkResultVarVolume = new StringTokenizer(sbResultVarVolume.toString(), "\n");
		}

		if(agent.indexOf("variationpriceindex") >= 0){
			sbResultVarPriceIndex = formatterResultVariationPriceIndex(object.getResponseAgents("VariationPriceIndexAgent"), request, response);
			tkResultVarPriceIndex = new StringTokenizer(sbResultVarPriceIndex.toString(), "\n");
		}

		try{
			PrintWriter out = response.getWriter();
			while(tkResultHistory.hasMoreTokens() 
					&& tkResultBollinger.hasMoreTokens()
					&& tkResultVarVolume.hasMoreTokens()
					&& tkResultVarPriceIndex.hasMoreTokens()
					&& tkResultMaxMinIndex.hasMoreTokens() ){
				
				if(agent.indexOf("history") >= 0)
					out.print(tkResultHistory.nextToken());
				if(agent.indexOf("bollinger") >= 0)
					out.print(","+tkResultBollinger.nextToken());
				if(agent.indexOf("variationvolume") >= 0)
					out.print(","+tkResultVarVolume.nextToken());
				if(agent.indexOf("variationpriceindex") >= 0)
					out.print(","+tkResultVarPriceIndex.nextToken());
				if(agent.indexOf("maxminindex") >= 0)
					out.print(","+tkResultMaxMinIndex.nextToken());

				out.println("");
			}
			out.flush();
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

}	
	
	private StringBuffer formatterResultCandle(String history, HttpServletRequest request, HttpServletResponse response){
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
	
	private void formatterResultCandle2(String history, HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			StringTokenizer tokens = new StringTokenizer(history, "|");
			String tmp = "";
			while (tokens.hasMoreTokens()) {
				String result = (String) tokens.nextToken();
				JSONObject json = new JSONObject(result);

				if(tmp.equals("")){
					tmp = String.valueOf(json.get("close"));
					System.out.println(">>>>>>> " + getLastData(String.valueOf(json.get("date"))));
					out.print(getLastData(String.valueOf(json.get("date"))));
					out.print("," + tmp);
					out.print("," + tmp);
					out.print("," + tmp);
					out.print("," + tmp);
					out.print(",0");
					out.println("");
				}
				
				out.print(String.valueOf(json.get("date")));
				out.print("," + String.valueOf(json.get("open")));
				out.print("," + String.valueOf(json.get("high")));
				out.print("," + String.valueOf(json.get("low")));
				out.print("," + String.valueOf(json.get("close")));
				out.print("," + String.valueOf(json.get("vol")));
				out.println("");
				
			}
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private StringBuffer formatterResultBollinger(String history, HttpServletRequest request, HttpServletResponse response){
		StringBuffer sbResult = new StringBuffer();
		StringTokenizer tokens = new StringTokenizer(history, "|");
		
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);

			sbResult.append(String.valueOf(json.get("date")));
			sbResult.append("," + String.valueOf(json.get("upper")));
			sbResult.append("," + String.valueOf(json.get("middle")));
			sbResult.append("," + String.valueOf(json.get("lower")));
			sbResult.append("\n");
			
		}
		return sbResult;
	
	}

	private StringBuffer formatterResultMaxMinIndex(String history, HttpServletRequest request, HttpServletResponse response){
		StringBuffer sbResult = new StringBuffer();
		StringTokenizer tokens = new StringTokenizer(history, "|");
		String tmp = "";
			
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);

			sbResult.append(String.valueOf(json.get("date")));
			sbResult.append("," + String.valueOf(json.get("mmi01")));
			sbResult.append("," + String.valueOf(json.get("mmi02")));
			sbResult.append("," + String.valueOf(json.get("mmi03")));
			sbResult.append("," + String.valueOf(json.get("mmi04")));
			sbResult.append("," + String.valueOf(json.get("mmi05")));
			sbResult.append("\n");
			
		}
		return sbResult;
	}

	private StringBuffer formatterResultVariationVolume(String history, HttpServletRequest request, HttpServletResponse response){
		StringBuffer sbResult = new StringBuffer();
		StringTokenizer tokens = new StringTokenizer(history, "|");
		
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);

			sbResult.append(String.valueOf(json.get("date")));
			sbResult.append("," + String.valueOf(json.get("dMM")));
			sbResult.append("," + String.valueOf(json.get("dOC")));
			sbResult.append("," + String.valueOf(json.get("mMax")));
			sbResult.append("," + String.valueOf(json.get("mMin")));
			sbResult.append("," + String.valueOf(json.get("pMI")));
			
			sbResult.append("\n");
			
		}
		return sbResult;
		
	}	
	
	private StringBuffer formatterResultVariationPriceIndex(String history, HttpServletRequest request, HttpServletResponse response){
		StringBuffer sbResult = new StringBuffer();
		StringTokenizer tokens = new StringTokenizer(history, "|");
		
		while (tokens.hasMoreTokens()) {
			String result = (String) tokens.nextToken();
			JSONObject json = new JSONObject(result);

			sbResult.append(String.valueOf(json.get("date")));
			sbResult.append("," + String.valueOf(json.get("dMM")));
			sbResult.append("," + String.valueOf(json.get("mMax")));
			sbResult.append("," + String.valueOf(json.get("mMin")));
			
			sbResult.append("\n");
			
		}
		return sbResult;
		
	}	
	
	private String getLastData(String data){
		String dateFormat = "yyyyMMdd";		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(data.substring(0,4)), Integer.parseInt(data.substring(4,6))-1 , Integer.parseInt(data.substring(6,8))); 		
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return sdf.format(calendar.getTime());
	}
}
