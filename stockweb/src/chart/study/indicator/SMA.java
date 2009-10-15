package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Simple Moving Average.
 */
public class SMA extends Indicator {
	private final int length;

	public SMA(QuoteHistory qh, int length) {
		super(qh);
		this.length = length;
	}

	@Override
	public double calculate() {
		int endBar = qh.size() - 1;
		int startBar = endBar - length;
		double sma = 0;

		for (int bar = startBar; bar <= endBar; bar++) {
			sma += qh.getPriceBar(bar).getClose();
		}

		value = sma / (endBar - startBar + 1);
		return value;
	}
}
