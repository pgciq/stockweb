package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Shooting Star
 */
public class BearishHangingMan extends Indicator {

	@Override
	public double calculate() {

		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;

		double TendenciaUltimosCandles = candle.getTendencia(0,1);

		boolean HangingMan = candle.isHangingManORHammer(qh);
		if ((HangingMan) && (TendenciaUltimosCandles > 0)) {
			result = 3;
		}

		return result;
	}
}
