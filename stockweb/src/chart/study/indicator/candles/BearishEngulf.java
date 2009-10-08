package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Lower Bollinger Band
 */
public class BearishEngulf extends Indicator {
	private int length;

	@Override
	public double calculate() {
		// Engolfo de baixa
		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;
		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);

		int TendenciaCandlesMinima = candle.getTendencia(1, 3);
		int TendenciaCandlesMedia = candle.getTendencia(1, 5);
		int TendenciaCandlesForte = candle.getTendencia(1, 7);

		boolean AlturaCandle = CorpoCandle1 > CorpoCandle2;

		// EngulfingFull = 0
		boolean SegundoCandle = candle.Close(1) > candle.Open(1)
				|| candle.Doji(1);

		boolean PrimeiroCandle = AlturaCandle
				&& candle.Close(1) < candle.Open(0)
				&& candle.Close(1) > candle.Close(0)
				&& candle.Open(1) < candle.Open(0)
				&& candle.Open(1) > candle.Close(0);

		if (SegundoCandle && PrimeiroCandle) {
			result = -2;
			if (TendenciaCandlesMinima == 0) {
				result = -2.10;
			}
			if (TendenciaCandlesMedia == 0) {
				result = -2.30;
			}
			if (TendenciaCandlesForte == 0) {
				result = -2.50;
			}
		}

		return result;
	}
}
