package ai;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import ai.agent.*;
import ai.environment.ReadFileEnvironmentView;
import ai.environment.YahooEnvironmentView;


public class TestAI {

	private List<Agent> lsAgent = new ArrayList<Agent>();
	private Environment env = new Environment();
	private String pathname = "";
	
/*	public static void main(String args[]){
		System.getProperties().put("proxyPort","80");
	    System.getProperties().put("proxyHost","proxypac.sisal.it");
	    System.getProperties().put("username","rocha");
	    System.getProperties().put("password","humberto12");
		
		TestAI ai = new TestAI();
	}
*/	
	public TestAI(String pathname){
		this.pathname = pathname;
/*		String agents = "BollingerAgent,ADXAgent";
		executeAgent(agents);
		//env.getResIndicator(agentName);
		
		System.out.println("Response: " + getResponseAgents("BollingerAgent"));
*/	}
	
	public String getResponseAgents(String agentName){
		return env.getResponseAgents(agentName);
	}
	
	public void executeAgents(Properties propStocks, String interval, String filterDI, String filterDF, String agents){
		//Agent ag = new Agent();
		//System.out.println("##### " + (new Date()));
		
		try{
			String stocks = propStocks.getProperty("ITALY");
			env.registerView(new YahooEnvironmentView());
//			env.registerView(new ReadFileEnvironmentView(this.pathname));ù

			StringTokenizer tokenAgents = new StringTokenizer(agents, ",");
			while(tokenAgents.hasMoreTokens())
				env.addAgent((Agent)Class.forName("ai.agent." + tokenAgents.nextToken()).newInstance());

			StringTokenizer tokenStock = new StringTokenizer(stocks, ",");
			while(tokenStock.hasMoreTokens()){
				String stock = tokenStock.nextToken();
				env.updateViews("{symbol:"+tokenStock.nextToken()+", filename:COTAHIST_A2009.TXT, interval:"+interval+", filterDI:"+filterDI+", filterDF:"+filterDF+"}");

				env.step("web");
				System.out.println("Stock = " + propStocks.getProperty(stock) + " | " + env.getResponseAgents("CandlestickAgent"));
				System.out.println(">>>>>>> " + getResponseAgents("HistoryStocksAgent"));
				System.out.println(">>>>>>> BollingerAgent = " + getResponseAgents("BollingerAgent"));

			}
			
			
	//		env.addAgent(new BollingerAgent());
	//		env.addAgent(new ADXAgent());
	//		env.addAgent(new EMAAgent());
	//		env.addAgent(new ForceIndexAgent());
	//		env.addAgent(new HMAAgent());
	//		env.addAgent(new NoiseAdjustedRSIAgent());
			//env.addAgent(new MaxMinAgent());
	//		env.addAgent(new WilliamPercRAgent());		
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("ERROR : " + ex.getMessage());
		}
		System.out.println("##### " + (new Date()));
		
	}


	public void executeAgent(String stock, String interval, String filterDI, String filterDF, String agents){
		//Agent ag = new Agent();
		//System.out.println("##### " + (new Date()));
		
		try{
			env.registerView(new YahooEnvironmentView());
//			env.registerView(new ReadFileEnvironmentView(this.pathname));ù

			StringTokenizer tokenAgents = new StringTokenizer(agents, ",");
			while(tokenAgents.hasMoreTokens())

				env.addAgent((Agent)Class.forName("ai.agent." + tokenAgents.nextToken()).newInstance());
				env.updateViews("{symbol:"+stock+", filename:YAHOO, interval:"+interval+", filterDI:"+filterDI+", filterDF:"+filterDF+"}");
				env.step("web");
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("ERROR : " + ex.getMessage());
		}
		System.out.println("##### " + (new Date()));
		
	}


}
