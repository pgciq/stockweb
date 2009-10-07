package ai.agent;

import chart.study.QuoteHistory;

import ai.Agent;
import ai.Percept;
import ai.agent.program.EMAAP;
import ai.agent.program.HMAAP;
import ai.agent.program.MACDTriggerAP;
import ai.agent.program.NoiseAdjustedRSIAP;

public class NoiseAdjustedRSIAgent extends Agent{

	public NoiseAdjustedRSIAgent() {
		super(new NoiseAdjustedRSIAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		result = (modal.equals("web")) ? resultIndex(p, result) : valuated(p, result);
		return result;
	}

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return String.valueOf(((NoiseAdjustedRSIAP)super.program).getRSIAdjusted());
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double rsi = ((NoiseAdjustedRSIAP)super.program).getRSIAdjusted();
		
		QuoteHistory history = ((NoiseAdjustedRSIAP)super.program).history;
		double lastPrice = history.getLastPriceBar().getClose();
		double previewPrice = history.getPriceBar(history.size()-2).getClose();

		System.out.println("previewPrice = " + previewPrice);
		System.out.println("lastPrice = " + lastPrice);
		System.out.println("RSI Adjusted = " + rsi);
		
/*		
		if((previewPrice < ema) && (lastPrice > ema)) 
			return StatusAgent.Buy.toString();

		if((previewPrice > ema) && (lastPrice < ema)) 
			return StatusAgent.Sell.toString();
*/
		return StatusAgent.Hold.toString();

	}

}
