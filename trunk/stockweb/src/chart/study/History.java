package chart.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class History {

	private static Map<String, QuoteHistory> mapQuoteHistory = new HashMap<String, QuoteHistory>();
	
	public History() {
	
	}
	public static QuoteHistory getQuoteHistory(String stockcod){

		if(mapQuoteHistory.containsKey(stockcod))
			return mapQuoteHistory.get(stockcod);
		
		QuoteHistory quoteHistory = new QuoteHistory(stockcod);
		mapQuoteHistory.put(stockcod, quoteHistory);	
		return quoteHistory;
	}
	
	public static void clearStock(String stock){
		if(stock.equals("all")){
			mapQuoteHistory.clear();
			return;
		}
		
		mapQuoteHistory.remove(stock);
	}
	
	
}
