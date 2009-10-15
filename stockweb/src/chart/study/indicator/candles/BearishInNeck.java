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
public class BearishInNeck extends Indicator {
	// Padrao Piercing

	@Override
	public double calculate() {

		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;

		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);
		double SombraSup1 = candle.getCandleSize(0, 2, 1);
		double SombraInf1 = candle.getCandleSize(0, 2, 2);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);
		double SombraSup2 = candle.getCandleSize(1, 2, 1);
		double SombraInf2 = candle.getCandleSize(1, 2, 2);

		/*
		 * double CorpoCandle3 = candle.getCandleSize(2, 1, 0); double
		 * SombraSup3 = candle.getCandleSize(2 ,2 ,1); double SombraInf3 =
		 * candle.getCandleSize(2 ,2 ,2);
		 */
		double TendenciaUltimosCandles = candle.getTendencia(1, 3);

		boolean SegundoCandleBaixa = candle.Open(1) > candle.Close(1)
				&& CorpoCandle2 > SombraSup2 + SombraInf2;

		double rangeMaximo = (candle.Open(1) - candle.Close(1)) / 100 * 20; // Retorna
																			// o
																			// valor
																			// do
																			// pre�o
																			// que
																			// representa
																			// 10%

		boolean PrimeiroCandleAlta = candle.Close(0) > candle.Open(0)
				&& candle.Close(0) < candle.Close(1) + rangeMaximo
				&& candle.Close(0) > candle.Close(1) - rangeMaximo
				&& CorpoCandle1 > SombraSup1 + SombraInf1;

		boolean BearishInNeck = TendenciaUltimosCandles == 0
				&& SegundoCandleBaixa && PrimeiroCandleAlta;

		if (BearishInNeck) {
			result = -14;
		}

		return result;
	}
}
