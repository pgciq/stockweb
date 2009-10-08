package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * Upper Bollinger Band
 */
public class BollingerUpper extends Indicator {
	private final int length;
	private final double deviations;
	private int sizeLimitHistory = 0;

	public BollingerUpper(QuoteHistory qh, int length, double deviations) {
		super(qh);
		this.length = length;
		this.deviations = deviations;
	}

	@Override
	public double calculate() {
		int lastBar = qh.size() - 1;

		if (sizeLimitHistory != 0) {
			lastBar = sizeLimitHistory - 1;
		}

		int firstBar = lastBar - length + 1;
		double squareSum = 0;
		double sum = 0;

		for (int bar = firstBar; bar <= lastBar; bar++) {
			double barClose = qh.getPriceBar(bar).getClose();
			sum += barClose;
			squareSum += barClose * barClose;
		}

		double stDev = length * squareSum - sum * sum;
		stDev /= length * (length - 1);
		stDev = Math.sqrt(stDev);

		value = sum / length + deviations * stDev;
		return value;
	}

	public void setLimitHistory(int size) {
		this.sizeLimitHistory = size;
	}
}
