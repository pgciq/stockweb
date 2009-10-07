package ai.agent;


import java.util.StringTokenizer;

import org.json.JSONObject;

import ai.Agent;
import ai.Percept;
import ai.agent.program.BandsBollingerAP;

public class BollingerAgent extends Agent{

	public BollingerAgent() {
		super(new BandsBollingerAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		if(modal.equals("web"))
			result = resultIndex(p, result);
		else
			result = valuated(p, result);
		
		return result;
	}	

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return ((BandsBollingerAP)super.program).getListBandsBollinger();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		String bands = ((BandsBollingerAP)super.program).getBandsBollinger();
		double upper, lower, middle;
		JSONObject json = new JSONObject(bands);

		lower = json.getDouble("lower");
		middle = json.getDouble("middle");
		upper = json.getDouble("upper");
		
/*		lower = Double.parseDouble(token.nextToken());
		middle = Double.parseDouble(token.nextToken());
		upper = Double.parseDouble(token.nextToken());
*/
		System.out.println("upper = " + upper + "\n" + "lower = " + lower);
		System.out.println("\n Percentual : " + (((upper / lower) * 100) - 100) + " %");
		
		double premin = ((BandsBollingerAP)super.program).history.getLastPriceBar().getLow();
		double premax = ((BandsBollingerAP)super.program).history.getLastPriceBar().getHigh();
		double differPremin = (100 - (((upper - premin)/(upper-lower))*100));
		double differPremax = (100 - (((upper - premax)/(upper-lower))*100));
		
		System.out.println("premin = " + premin);
		System.out.println("premax = " + premax);
		System.out.println("(MAX - PreMin) / (MAX - MIN) \n" + differPremin + " %" );
		System.out.println("(MAX - PreMax) / (MAX - MIN) \n" + differPremax + " %" );

		if(premin <= lower)
			return StatusAgent.Buy.toString() + "|" + StatusAgent.Alto.toString();
		if(differPremin <= 15)
			return StatusAgent.Buy.toString() + "|" + StatusAgent.MedioAlto.toString();
		if(differPremin <= 25)
			return StatusAgent.Buy.toString() + "|" + StatusAgent.Medio.toString();
		
		
		if(premax >= upper)
			return StatusAgent.Sell.toString() + "|" + StatusAgent.Alto.toString();
		if((upper/differPremax) >= 15)
			return StatusAgent.Sell.toString() + "|" + StatusAgent.MedioAlto.toString();
		if((upper/differPremax) >= 25)
			return StatusAgent.Sell.toString() + "|" + StatusAgent.Medio.toString();
		
		return StatusAgent.Hold.toString() + "|" + StatusAgent.Medio.toString();

	}

	private BandsBollingerAP bollingerAP;
	
}
