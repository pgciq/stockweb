package ai.agent.program;

import java.text.DecimalFormat;
import java.util.ArrayList;

import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.*;
import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;

public class VariationVolumeAP extends AgentProgram{

	public VariationVolumeAP() {
		
	}
	
	@Override
	public String execute(Percept percept, String modal) {
		
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
	
	public String getListVariationVolume(){
		PriceBar priceBar = null;
		String result = "";
		String token = "";
 
		VariationVolume vv = new VariationVolume(history, 5);

		for(int i=5; i<history.getSize(); i++){
			vv.setLimitHistory(i);
			vv.calculate();
			
			String differMaxMin = String.valueOf(df.format(vv.getDifferMaxMin())).replace(",", ".");
			String differOpenClose = String.valueOf(df.format(vv.getDifferOpenClose())).replace(",", ".");
			String mediaMax = String.valueOf(df.format(vv.getMediaMax())).replace(",", ".");
			String mediaMin = String.valueOf(df.format(vv.getMediaMin())).replace(",", ".");
			String percMaxIndex = String.valueOf(df.format(vv.getPercMaxIndex())).replace(",", ".");
			
			priceBar = history.getPriceBar(i-5);
			result += token + "{date:"+priceBar.getDate()+"," +
					  " dMM:"+differMaxMin+"," +
					  " dOC:"+differOpenClose+"," +
					  " mMax:"+mediaMax+"," +
					  " mMin:"+mediaMin+"," +
					  " pMI:"+percMaxIndex+
					  "}";
			token = "|";
			
		}
		return result;
	}
	
	public String getVariationVolume(){

		PriceBar priceBar = null;
		String result = "";
 
		VariationVolume vv = new VariationVolume(history, 5);

		String differMaxMin = String.valueOf(df.format(vv.getDifferMaxMin())).replace(",", ".");
		String differOpenClose = String.valueOf(df.format(vv.getDifferOpenClose())).replace(",", ".");
		String mediaMax = String.valueOf(df.format(vv.getMediaMax())).replace(",", ".");
		String mediaMin = String.valueOf(df.format(vv.getMediaMin())).replace(",", ".");
		String percMaxIndex = String.valueOf(df.format(vv.getPercMaxIndex())).replace(",", ".");
			
		result = "{type:variationvolume," +
				 " date:"+priceBar.getDate()+"," +
				 " dMM:"+differMaxMin+"," +
				 " dOC:"+differOpenClose+"," +
				 " mMax:"+mediaMax+"," +
				 " mMin:"+mediaMin+"," +
				 " pMI:"+percMaxIndex+"}";

		return result;
	}
	
	private PriceBar priceBar = null;
	public QuoteHistory history = null; 
	private DecimalFormat df = new DecimalFormat("#.####");
 

}
