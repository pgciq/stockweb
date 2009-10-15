package ai.agent.program;

import java.text.DecimalFormat;
import java.util.ArrayList;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.BollingerLower;
import chart.study.indicator.BollingerMiddle;
import chart.study.indicator.BollingerUpper;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class BandsBollingerAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	private DecimalFormat df = new DecimalFormat("#.####");

	public BandsBollingerAP() {

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

	public String getBandsBollinger() {

		BollingerUpper upper = new BollingerUpper(history, 21, 2.0);
		BollingerMiddle middle = new BollingerMiddle(history, 21);
		BollingerLower lower = new BollingerLower(history, 21, 2.0);

		String _upper = String.valueOf(df.format(upper.calculate())).replace(
				",", ".");
		String _middle = String.valueOf(df.format(middle.calculate())).replace(
				",", ".");
		String _lower = String.valueOf(df.format(lower.calculate())).replace(
				",", ".");

		return "{type:bandsbollinger, lower:" + _lower + ", middle:" + _middle
				+ ", upper:" + _upper + ", date:"
				+ history.getLastPriceBar().getDate() + "}";
		// return _lower + "|" + _middle + "|" + _upper;
	}

	public String getListBandsBollinger() {
		PriceBar priceBar = null;
		String result = "";
		String token = "";

		BollingerUpper upper = new BollingerUpper(history, 21, 2.0);
		BollingerMiddle middle = new BollingerMiddle(history, 21);
		BollingerLower lower = new BollingerLower(history, 21, 2.0);

		String _upper = "0";
		String _middle = "0";
		String _lower = "0";

		for (int i = 21; i < history.getSize(); i++) {
			upper.setLimitHistory(i);
			middle.setLimitHistory(i);
			lower.setLimitHistory(i);

			_upper = String.valueOf(df.format(upper.calculate())).replace(",",
					".");
			_middle = String.valueOf(df.format(middle.calculate())).replace(
					",", ".");
			_lower = String.valueOf(df.format(lower.calculate())).replace(",",
					".");

			priceBar = history.getPriceBar(i - 21);
			result += token + "{date:" + priceBar.getDate() + "," + " upper:"
					+ _upper + "," + " middle:" + _middle + "," + " lower:"
					+ _lower + "}";
			token = "|";

		}
		return result;
	}

}
