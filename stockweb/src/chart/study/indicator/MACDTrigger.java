package chart.study.indicator;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * MACD Trigger
 */
public class MACDTrigger extends Indicator {
	private final int triggerLength;
	private final double multiplier;

	public MACDTrigger(MACD macd, int triggerLength) {
		super(macd);
		this.triggerLength = triggerLength;
		multiplier = 2. / (triggerLength + 1.);
	}

	@Override
	public double calculate() {
		int lastBar = parent.getHistory().size() - 1;
		int firstBar = lastBar - 2 * triggerLength;

		double trigger = parent.getHistory().get(firstBar).getValue();
		for (int bar = firstBar + 1; bar <= lastBar; bar++) {
			double macd = parent.getHistory().get(bar).getValue();
			trigger += (macd - trigger) * multiplier;
		}

		value = trigger;
		return value;
	}
}
