package runner_ai;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import org.directwebremoting.json.types.JsonArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ai.Environment;
import ai.agent.BollingerAgent;
import ai.agent.WilliamPercRAgent;
import ai.environment.YahooEnvironmentView;

public class AI {

	@Before
	public void setup() {
		
		stock = "F.MI";
		interval = "d";
		filterDI = "20090101";
		filterDF = "20101231";

		env = new Environment();
		// Add Environment - is possible add more of the one
		env.registerView(new YahooEnvironmentView());

		// Add Agents
		env.addAgent(new BollingerAgent());
		env.addAgent(new WilliamPercRAgent());
		/*
		 * env.addAgent(new ADXAgent()); 
		 * env.addAgent(new EMAAgent());
		 * env.addAgent(new ForceIndexAgent()); 
		 * env.addAgent(new HMAAgent());
		 * env.addAgent(new NoiseAdjustedRSIAgent()); 
		 * env.addAgent(new MaxMinAgent());
		 */
	}

	@Test
	public void testAI() {
		Properties propStocks;
		System.out.println("##### " + (new Date()));

		try {
			env.updateViews("{symbol:" + stock
							+ ", filename:COTAHIST_A2009.TXT, interval:"
							+ interval + ", filterDI:" + filterDI
							+ ", filterDF:" + filterDF + "}");

			env.step("web");

			StringTokenizer tokens = new StringTokenizer(env.getResponseAgents("BollingerAgent"), "|");

			while (tokens.hasMoreTokens()) {
				String result = (String) tokens.nextToken();
				JSONObject json = new JSONObject(result);
				System.out.println(json.get("date"));
				if( String.valueOf(json.get("date")).equals("20091009") ){
					assertEquals(json.get("upper"), "");
					assertEquals(json.get("middle"), "");
					assertEquals(json.get("lower"), "");
				}
			}

			// System.out.println("Stock = " + stock + " | " + env.getResponseAgents("CandlestickAgent"));

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR : " + ex.getMessage());
		}

	}

	private Environment env = null;
	private String stock;
	private String interval;
	private String filterDI;
	private String filterDF;

}
