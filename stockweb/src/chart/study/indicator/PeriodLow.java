package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Lowest low of the period.
 */
public class PeriodLow extends Indicator {
	private final int periodLength;

	public PeriodLow(QuoteHistory qh, int periodLength) {
		super(qh);
		this.periodLength = periodLength;
	}

	@Override
	public double calculate() {

		int periodStart = qh.size() - periodLength;
		int periodEnd = qh.size() - 1;
		double low = qh.getPriceBar(periodStart).getLow();

		for (int bar = periodStart + 1; bar <= periodEnd; bar++) {
			double barLow = qh.getPriceBar(bar).getLow();
			if (barLow < low) {
				low = barLow;
			}
		}

		value = low;
		return value;
	}
}
