package ai.agent;


import ai.Agent;
import ai.Percept;
import ai.agent.program.CandlestickAP;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class CandlestickAgent extends Agent{

	public CandlestickAgent() {
		super(new CandlestickAP());
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

		return ((CandlestickAP)super.program).getIdCandlestick();
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";
		// Lista todos os possiveis tipos de candles 

/*		
		String bands = ((CandlestickAP)super.program).getIdCandlestick();

		double upper, lower, middle;
		JSONObject json = new JSONObject(bands);

		lower = json.getDouble("lower");
		middle = json.getDouble("middle");
		upper = json.getDouble("upper");
		
		System.out.println("upper = " + upper + "\n" + "lower = " + lower);
		System.out.println("\n Percentual : " + (((upper / lower) * 100) - 100) + " %");
		
		double premin = ((CandlestickAP)super.program).history.getLastPriceBar().getLow();
		double premax = ((CandlestickAP)super.program).history.getLastPriceBar().getHigh();
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
*/
		return "";
	}

}
