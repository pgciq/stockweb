package ai.agent.program;

import java.text.DecimalFormat;
import java.util.ArrayList;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.MaxMinIndex;

public class MaxMinIndexAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	private DecimalFormat df = new DecimalFormat("#.####");

	public MaxMinIndexAP() {

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

	public String getListMaxMinIndex() {
		PriceBar priceBar = null;
		String result = "";
		String token = "";

		MaxMinIndex maxMinIndex = new MaxMinIndex(history);

		// i=2 representa iniciar apartir do segundo candle
		for (int i = 1; i < history.getSize(); i++) {
			maxMinIndex.setLimitHistory(i);
			maxMinIndex.calculate();

			String maxMinIndex01 = String.valueOf(
					df.format(maxMinIndex.getMaxMinIndex01()))
					.replace(",", ".");
			String maxMinIndex02 = String.valueOf(
					df.format(maxMinIndex.getMaxMinIndex02()))
					.replace(",", ".");
			String maxMinIndex03 = String.valueOf(
					df.format(maxMinIndex.getMaxMinIndex03()))
					.replace(",", ".");
			String maxMinIndex04 = String.valueOf(
					df.format(maxMinIndex.getMaxMinIndex04()))
					.replace(",", ".");
			String maxMinIndex05 = String.valueOf(
					df.format(maxMinIndex.getMaxMinIndex05()))
					.replace(",", ".");

			priceBar = history.getPriceBar(i - 1);
			result += token + "{date:" + priceBar.getDate() + "," + " mmi01:"
					+ maxMinIndex01 + "," + " mmi02:" + maxMinIndex02 + ","
					+ " mmi03:" + maxMinIndex03 + "," + " mmi04:"
					+ maxMinIndex04 + "," + " mmi05:" + maxMinIndex05 + "}";
			token = "|";

		}
		return result;
	}

	public String getMaxMinIndex() {

		MaxMinIndex maxMinIndex = new MaxMinIndex(history);

		String maxMinIndex01 = String.valueOf(
				df.format(maxMinIndex.getMaxMinIndex01())).replace(",", ".");
		String maxMinIndex02 = String.valueOf(
				df.format(maxMinIndex.getMaxMinIndex02())).replace(",", ".");
		String maxMinIndex03 = String.valueOf(
				df.format(maxMinIndex.getMaxMinIndex03())).replace(",", ".");
		String maxMinIndex04 = String.valueOf(
				df.format(maxMinIndex.getMaxMinIndex04())).replace(",", ".");
		String maxMinIndex05 = String.valueOf(
				df.format(maxMinIndex.getMaxMinIndex05())).replace(",", ".");

		priceBar = history.getPriceBar(0);
		String result = "{date:" + priceBar.getDate() + "," + " mmi01:"
				+ maxMinIndex01 + "," + " mmi02:" + maxMinIndex02 + ","
				+ " mmi03:" + maxMinIndex03 + "," + " mmi04:" + maxMinIndex04
				+ "," + " mmi05:" + maxMinIndex05 + "}";

		return result;
	}

}
