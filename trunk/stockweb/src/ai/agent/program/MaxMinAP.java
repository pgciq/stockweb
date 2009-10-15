package ai.agent.program;

import java.util.ArrayList;
import java.util.StringTokenizer;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import ai.agent.WilliamPercRAgent;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.MaxMinPivot;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class MaxMinAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	private MaxMinPivot pivot = null; // TEST

	public MaxMinAP() {

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
			WilliamPercRAgent agent = new WilliamPercRAgent(); // DEBUG
			for (int i = 0; i < lsSotck.size(); i++) {
				apply((Stock) lsSotck.get(i));
				// -------------------- DEBUG -----------------------
				if (i > 10) {
					String result = agent.executeForDebug(this.history);
					System.out.println("#####################  " + result);

					StringTokenizer token = new StringTokenizer(result, "|");
					token.nextToken();
					int nCandle = Integer.parseInt(token.nextToken());
					nCandle = nCandle <= 1 ? 2 : 3;
					pivot = new MaxMinPivot(history, nCandle); // TEST

					// pivot = new MaxMinPivot(history,2); // TEST
					pivot.calculate();
				}
				// ---------------------------------------------------
			}

			double perc = MaxMinPivot.NO / (MaxMinPivot.NO + MaxMinPivot.OK)
					* 100;

			System.out.println("Quandidade de \n ACERTOS: " + MaxMinPivot.OK
					+ " = " + (perc - 100) * -1 + "\n ERROS: " + MaxMinPivot.NO
					+ " = " + perc + "\n\n ");
			System.out
					.println("Ganho porcentual = " + MaxMinPivot.totalPercent);
			System.out.println("Perda porcentual = "
					+ MaxMinPivot.totalPerdaPercent);

			return "ok";
		}

		apply((Stock) percept.getAttribute("StockObject"));

		return "ok";
	}

	public double getMax() {
		MaxMinPivot pivot = new MaxMinPivot(history, 3);
		return pivot.calculate();
	}
}
