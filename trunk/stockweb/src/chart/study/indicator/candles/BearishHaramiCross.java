package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Bullish Morning Star
 */
public class BearishHaramiCross extends Indicator {

	@Override
	public double calculate() {

		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;

		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);
		// double SombraSup1 = candle.getCandleSize(0 ,2 ,1);
		// double SombraInf1 = candle.getCandleSize(0 ,2 ,2);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);
		double SombraSup2 = candle.getCandleSize(1, 2, 1);
		double SombraInf2 = candle.getCandleSize(1, 2, 2);

		double TendenciaUltimosCandles = candle.getTendencia(1, 3);

		boolean CandleDoji = candle.Doji(0);

		boolean SegundoCandleBaixa = candle.Open(1) < candle.Close(1)
				&& CorpoCandle2 > CorpoCandle1
				&& CorpoCandle2 > SombraSup2 + SombraInf2;

		boolean PrimeiroCandleGAP = CandleDoji
				&& candle.Close(0) > candle.Open(1)
				&& candle.Close(0) < candle.Close(1)
				&& candle.Open(0) > candle.Open(1)
				&& candle.Open(0) < candle.Close(1);

		boolean HaramiCrossBaixa = TendenciaUltimosCandles == 1
				&& SegundoCandleBaixa && PrimeiroCandleGAP;

		if (HaramiCrossBaixa) {
			result = -10;
		}

		return result;
	}
}
