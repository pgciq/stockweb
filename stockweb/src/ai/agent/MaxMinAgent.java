package ai.agent;

import ai.Agent;
import ai.Percept;
import ai.agent.program.MaxMinAP;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class MaxMinAgent extends Agent{

	public MaxMinAgent() {
		super(new MaxMinAP());
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
		return null;
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

//		double max = ((MaxMinAP)super.program).getMax("alta");
//		double min = ((MaxMinAP)super.program).getMin();
		
		
/*		QuoteHistory history = ((MaxMinAP)super.program).history;
		double lastPrice = history.getLastPriceBar().getClose();
		double previewPrice = history.getPriceBar(history.size()-2).getClose();
*/
		
		
/*		System.out.println("previewPrice = " + previewPrice);
		System.out.println("lastPrice = " + lastPrice);
		System.out.println("MAX = " + max);
*/
		
/*		
		if((previewPrice < ema) && (lastPrice > ema)) 
			return StatusAgent.Buy.toString();

		if((previewPrice > ema) && (lastPrice < ema)) 
			return StatusAgent.Sell.toString();
*/
		return StatusAgent.Hold.toString();

	}

}
