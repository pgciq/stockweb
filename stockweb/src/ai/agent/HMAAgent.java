package ai.agent;

import ai.Agent;
import ai.Percept;
import ai.agent.program.HMAAP;
import chart.study.QuoteHistory;

public class HMAAgent extends Agent{

	public HMAAgent() {
		super(new HMAAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web"))? resultIndex(p, result) : valuated(p, result);
		return result;
	}

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return String.valueOf(((HMAAP)super.program).getHMA());
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double ema = ((HMAAP)super.program).getHMA();
		
		QuoteHistory history = ((HMAAP)super.program).history;
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
