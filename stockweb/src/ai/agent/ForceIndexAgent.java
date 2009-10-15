package ai.agent;

import ai.Agent;
import ai.Percept;
import ai.agent.program.ForceIndexAP;
import chart.study.QuoteHistory;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ForceIndexAgent extends Agent{

	public ForceIndexAgent() {
		super(new ForceIndexAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web"))? resultIndex(p, result) : valuated(p, result);		
		return valuated(p, result);
	}	

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return String.valueOf(((ForceIndexAP)super.program).getForceIndex());
	}
	

	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double forceIndex = ((ForceIndexAP)super.program).getForceIndex();
		
		QuoteHistory history = ((ForceIndexAP)super.program).history;
		double lastPrice = history.getLastPriceBar().getClose();
		double previewPrice = history.getPriceBar(history.size()-2).getClose();
		
		System.out.println("previewPrice = " + previewPrice);
		System.out.println("lastPrice = " + lastPrice);
		
		System.out.println("Force Index = " + forceIndex);
/*
		if((previewPrice < ema) && (lastPrice > ema)) 
			return StatusAgent.Buy.toString();

		if((previewPrice > ema) && (lastPrice < ema)) 
			return StatusAgent.Sell.toString();

*/
		return StatusAgent.Hold.toString();

	}

}
