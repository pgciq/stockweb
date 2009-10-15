package ai.agent;

import ai.Agent;
import ai.Percept;
import ai.agent.program.EMAAP;
import chart.study.QuoteHistory;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class EMAAgent extends Agent{

	public EMAAgent() {
		super(new EMAAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web")) ? resultIndex(p, result) : valuated(p, result);
		return valuated(p, result);
	}	

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return String.valueOf(((EMAAP)super.program).getEMA());
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double ema = ((EMAAP)super.program).getEMA();
		
		QuoteHistory history = ((EMAAP)super.program).history;
		double lastPrice = history.getLastPriceBar().getClose();
		double previewPrice = history.getPriceBar(history.size()-2).getClose();

		System.out.println("previewPrice = " + previewPrice);
		System.out.println("lastPrice = " + lastPrice);
		System.out.println("EMA = " + ema);
		
		
		if((previewPrice < ema) && (lastPrice > ema)) 
			return StatusAgent.Buy.toString();

		if((previewPrice > ema) && (lastPrice < ema)) 
			return StatusAgent.Sell.toString();

		return StatusAgent.Hold.toString();

	}

}
