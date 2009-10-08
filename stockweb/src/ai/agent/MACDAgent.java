package ai.agent;

import ai.Agent;
import ai.Percept;
import ai.agent.program.MACDAP;
import chart.study.QuoteHistory;

public class MACDAgent extends Agent{

	public MACDAgent() {
		super(new MACDAP());
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

		return String.valueOf(((MACDAP)super.program).getMACD());
	}
	
	public String valuated(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		double macd = ((MACDAP)super.program).getMACD();
		
		QuoteHistory history = ((MACDAP)super.program).history;
		double lastPrice = history.getLastPriceBar().getClose();
		double previewPrice = history.getPriceBar(history.size()-2).getClose();

		System.out.println("previewPrice = " + previewPrice);
		System.out.println("lastPrice = " + lastPrice);
		System.out.println("MACD = " + macd);
		
/*		
		if((previewPrice < macd) && (lastPrice > macd)) 
			return StatusAgent.Buy.toString();

		if((previewPrice > macd) && (lastPrice < macd)) 
			return StatusAgent.Sell.toString();
*/
		return StatusAgent.Hold.toString();

	}

}
