package chart.study.indicator;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class IndicatorValue {
	private final long date;
	private final double value;

	public IndicatorValue(long date, double value) {
		this.date = date;
		this.value = value;
	}

	public long getDate() {
		return date;
	}

	public double getValue() {
		return value;
	}

}
