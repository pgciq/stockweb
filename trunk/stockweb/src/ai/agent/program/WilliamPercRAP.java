package ai.agent.program;

import java.util.ArrayList;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.WilliamPercR;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class WilliamPercRAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	public WilliamPercRAP() {

	}

	private void apply(Stock stock) {
		double open;
		double high;
		double low;
		double close;
		long date;
		long volume;

		date = Long.parseLong(stock.getDataPregao());
		open = stock.getPreabe();
		high = stock.getPremax();
		low = stock.getPremin();
		close = stock.getPreult();
		volume = Long.parseLong(stock.getVoltot());

		history = History.getQuoteHistory(stock.getCodneg());
		priceBar = new PriceBar(date, open, high, low, close, volume);
		history.addHistoricalPriceBar(priceBar);
	}

	@Override
	public String execute(Percept percept, String modal) {

		String type = (String) percept.getAttribute("typeObject");
		if (type == null || type.indexOf("Stock") == -1) {
			return "";
		}

		if (type.equals("StockList")) {
			ArrayList lsSotck = (ArrayList) percept
					.getAttribute("ArrayStockObject");
			for (int i = 0; i < lsSotck.size(); i++) {
				apply((Stock) lsSotck.get(i));
			}
			return "ok";
		}

		apply((Stock) percept.getAttribute("StockObject"));

		return "ok";
	}

	public double getPercRLast() {
		WilliamPercR percR = new WilliamPercR(history, 5);
		return percR.calculate();
	}

	// ------------------------ DEBUG -----------------------------------------

	public double getPercRLast(QuoteHistory history) {
		WilliamPercR percR = new WilliamPercR(history, 5);
		return percR.calculate();
	}

	public double getPercRPreview() {
		WilliamPercR percR = new WilliamPercR(history, 5, "preview", 1);
		return percR.calculate();
	}

	public double getPercRPreview(QuoteHistory history) {
		WilliamPercR percR = new WilliamPercR(history, 5, "preview", 1);
		return percR.calculate();
	}

	public double searchTrend(int level) {
		WilliamPercR percR = new WilliamPercR(history, 5, "search", 1 + level);
		return percR.calculate();
	}

	public double searchTrend(QuoteHistory history, int level) {
		WilliamPercR percR = new WilliamPercR(history, 5, "search", 1 + level);
		return percR.calculate();
	}

}
