package chart.study.indicator;

import chart.study.QuoteHistory;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Upper Bollinger Band
 */
public class VariationPriceIndex extends Indicator {
	private final int ncandles;
	private int sizeLimitHistory = 0;

	private double differMaxMin;
	private double mediaMax;
	private double mediaMin;

	public VariationPriceIndex(QuoteHistory qh, int ncandles) {
		super(qh);
		this.ncandles = ncandles;
	}

	@Override
	public double calculate() {

		int lastBar = qh.size() - 1;

		if (sizeLimitHistory != 0) {
			lastBar = sizeLimitHistory - 1;
		}

		int firstBar = lastBar - ncandles + 1;

		CandlestickUtils candle = new CandlestickUtils(qh);

		differMaxMin = Math.abs(candle.High(firstBar) - candle.Low(firstBar));
		double mediaMaxMin = 0;

		for (int x = firstBar; x <= lastBar; x++) {
			// double barVolume = qh.getPriceBar(x).getVolume();
			mediaMaxMin = mediaMaxMin
					+ Math.abs(candle.High(x + 1) - candle.Low(x + 1));
		}

		mediaMax = mediaMaxMin / ncandles;
		mediaMin = mediaMaxMin / ncandles / 4 * 2;

		return 1;
	}

	public double getDifferMaxMin() {
		return differMaxMin;
	}

	public double getMediaMax() {
		return mediaMax;
	}

	public double getMediaMin() {
		return mediaMin;
	}

	public void setLimitHistory(int size) {
		this.sizeLimitHistory = size;
	}

}
