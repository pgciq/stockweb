package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Weighted Moving Average.
 */
public class WMA extends Indicator {
	private final int length;

	public WMA(QuoteHistory qh, int length) {
		super(qh);
		this.length = length;
	}

	@Override
	public double calculate() {
		int endBar = qh.size() - 1;
		int startBar = endBar - length;
		double wma = 0;

		for (int bar = startBar; bar <= endBar; bar++) {
			wma += qh.getPriceBar(bar).getClose() * (bar + 1);
		}

		value = wma / (length * (length + 1) / 2);
		return value;
	}
}
