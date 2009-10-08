package chart.study.indicator;

import chart.study.QuoteHistory;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Upper Bollinger Band
 */
public class VariationVolume extends Indicator {
	private final int ncandles;
	private int sizeLimitHistory = 0;
	private MaxMinIndex maxMinIndex = null;

	private double percMaxIndex;
	private double differMaxMin;
	private double differOpenClose;
	private double mediaMax;
	private double mediaMin;

	public VariationVolume(QuoteHistory qh, int ncandles) {
		super(qh);
		this.ncandles = ncandles;
		this.maxMinIndex = new MaxMinIndex(qh);
	}

	@Override
	public double calculate() {

		int lastBar = qh.size() - 1;

		if (sizeLimitHistory != 0) {
			lastBar = sizeLimitHistory - 1;
		}

		int firstBar = lastBar - ncandles + 1;

		CandlestickUtils candle = new CandlestickUtils(qh);

		differMaxMin = Math.abs(candle.High(firstBar) / candle.Low(firstBar)
				* 100 - 100);
		differOpenClose = Math.abs(candle.Close(firstBar)
				/ candle.Close(firstBar + 1) * 100 - 100);
		double mediaMaxMin = 0;

		MaxMinIndex maxMinIndex = new MaxMinIndex(qh);
		maxMinIndex.calculate();

		double maxMinIndex01 = maxMinIndex.getMaxMinIndex01();
		percMaxIndex = Math.abs(maxMinIndex01 / candle.Close(firstBar + 1)) * 100 - 100;

		for (int x = firstBar; x <= lastBar; x++) {
			// double barVolume = qh.getPriceBar(x).getVolume();
			mediaMaxMin = mediaMaxMin
					+ Math.abs(candle.High(x + 1) / candle.Low(x + 1) * 100
							- 100);
		}

		mediaMax = mediaMaxMin / ncandles;
		mediaMin = mediaMaxMin / ncandles / 4 * 2;

		/*
		 * for(int x=1; x<ncandles; x++){ mediaMaxMin = mediaMaxMin +
		 * Math.abs(((candle.High(x) / candle.Low(x))100)-100); }
		 */

		return 1;
	}

	public double getDifferMaxMin() {
		return differMaxMin;
	}

	public double getDifferOpenClose() {
		return differOpenClose;
	}

	public double getMediaMax() {
		return mediaMax;
	}

	public double getMediaMin() {
		return mediaMin;
	}

	public double getPercMaxIndex() {
		return percMaxIndex;
	}

	public void setLimitHistory(int size) {
		this.sizeLimitHistory = size;
	}

}
