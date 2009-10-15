package ai.agent.program;

import java.util.ArrayList;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.NoiseAdjustedRSI;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class NoiseAdjustedRSIAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	public NoiseAdjustedRSIAP() {

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
		double open, high, low, close;
		long date, volume;

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

	public double getRSIAdjusted() {
		NoiseAdjustedRSI adjustedRSI = new NoiseAdjustedRSI(history, 15);
		return adjustedRSI.calculate();
	}

}
