package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * Trend divergence indicator
 */
public class TrendDivergence extends Indicator {
	private final int shorterPeriod;
	private final int longerPeriod;

	public TrendDivergence(QuoteHistory qh, int shorterPeriod, int longerPeriod) {
		super(qh);
		this.shorterPeriod = shorterPeriod;
		this.longerPeriod = longerPeriod;
	}

	@Override
	public double calculate() {
		double shorterRSI = new RSI(qh, shorterPeriod).calculate();
		double longerRSI = new RSI(qh, longerPeriod).calculate();
		value = shorterRSI - longerRSI;

		return value;
	}
}
