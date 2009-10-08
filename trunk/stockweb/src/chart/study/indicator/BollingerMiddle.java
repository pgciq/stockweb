package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * Middle Bollinger Band
 */
public class BollingerMiddle extends Indicator {
	private final int length;
	private int sizeLimitHistory = 0;

	public BollingerMiddle(QuoteHistory qh, int length) {
		super(qh);
		this.length = length;
	}

	@Override
	public double calculate() {
		int lastBar = qh.size() - 1;

		if (sizeLimitHistory != 0) {
			lastBar = sizeLimitHistory - 1;
		}

		int firstBar = lastBar - length + 1;
		double sum = 0;
		for (int bar = firstBar; bar <= lastBar; bar++) {
			sum += qh.getPriceBar(bar).getClose();
		}

		value = sum / length;
		return value;
	}

	public void setLimitHistory(int size) {
		this.sizeLimitHistory = size;
	}
}
