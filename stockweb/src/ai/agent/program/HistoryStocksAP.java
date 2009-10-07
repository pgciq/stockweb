package ai.agent.program;

import java.util.ArrayList;

import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.BollingerLower;
import chart.study.indicator.BollingerMiddle;
import chart.study.indicator.BollingerUpper;
import chart.study.indicator.EMA;
import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;

public class HistoryStocksAP extends AgentProgram{

	public HistoryStocksAP() {
		
	}
	
	@Override
	public String execute(Percept percept, String modal) {
		double open, high, low, close;
		long date, volume;
		
		String type = (String) percept.getAttribute("typeObject");
		if((type==null) || (type.indexOf("Stock") == -1))
			return "";
		
		if(type.equals("StockList")){
			ArrayList lsSotck = (ArrayList) percept.getAttribute("ArrayStockObject");
			for(int i=0;i<lsSotck.size();i++){
				apply((Stock)lsSotck.get(i));		
			}
			return "ok";
		}
		
		apply((Stock)percept.getAttribute("StockObject"));
		
		return "ok";
	}

	private void apply(Stock stock) {
		double open;
		double high;
		double low;
		double close;
		long date;
		long volume;

		date   = Long.parseLong(stock.getDataPregao());
		open   = stock.getPreabe();
		high   = stock.getPremax();
		low    = stock.getPremin();
		close  = stock.getPreult();
		volume = Long.parseLong(stock.getVoltot());

		history = History.getQuoteHistory(stock.getCodneg());
		priceBar = new PriceBar(date, open, high, low, close, volume);
		history.addHistoricalPriceBar(priceBar);
	}
	
	public String getStocks(){
		PriceBar priceBar = null;
		String result = "";
		String token = "";
		// Devo converter o resultado do history em json, mas possui mais de um record.
		for(int i=0; i<history.getSize(); i++){
			priceBar = history.getPriceBar(i);
			result += token + "{open:"+priceBar.getOpen()+"," +
					  " close:"+priceBar.getClose()+"," +
					  " high:"+priceBar.getHigh()+"," +
					  " low:"+priceBar.getLow()+"," +
					  " vol:"+priceBar.getVolume()+"," +
					  " date:"+priceBar.getDate()+
					  "}";
			token = "|";
			
		}
		return result;
	}
	
	private PriceBar priceBar = null;
	public QuoteHistory history = null; 
	 

}
