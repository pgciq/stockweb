package chart.study.indicator;

import java.util.List;

import chart.study.QuoteHistory;

/**
 * Lower Bollinger Band
 */
public class Candlestick extends Indicator {
	private List<String> lsCandle = null;

	public Candlestick(QuoteHistory qh, List<String> lsCandle) {
		super(qh);
		this.lsCandle = lsCandle;
	}

	@Override
	public double calculate() {
		double result = 0;
		double lastResult = 0;
		try {
			for (int x = 0; x < lsCandle.size(); x++) {

				Object obj = Class.forName(
						"chart.study.indicator.candles." + lsCandle.get(x))
						.newInstance();
				((Indicator) obj).init(qh);
				result = ((Indicator) obj).calculate();
				if (result != 0) {
					lastResult = result;
					System.out.println(qh.getStrategyName() + " --> "
							+ lsCandle.get(x) + " = " + result);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lastResult;
	}

}
