package ai.agent;

import chart.study.QuoteHistory;

import ai.Agent;
import ai.Percept;
import ai.agent.program.ADXAP;
import ai.agent.program.EMAAP;
import ai.agent.program.HistoryStocksAP;

public class HistoryStocksAgent extends Agent{

	public HistoryStocksAgent() {
		super(new HistoryStocksAP());
	}
	
	public String execute(Percept p, String modal) {
		String result = super.execute(p, modal);
		return resultIndex(p, result);
	}	

	//Retorna apenas o valor do indicador
	public String resultIndex(Percept percept, String result){
		if(!result.equals("ok"))
			return "";

		return String.valueOf(((HistoryStocksAP)super.program).getStocks());
	}
	
}
