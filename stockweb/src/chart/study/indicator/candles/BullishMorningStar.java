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
public class BullishMorningStar extends Indicator {
	private int length;

	@Override
	public double calculate() {
		// Estrela da manh� (Bullish Morning Star)
		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;

		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);
		double SombraSup2 = candle.getCandleSize(1, 2, 1);
		double SombraInf2 = candle.getCandleSize(1, 2, 2);

		double CorpoCandle3 = candle.getCandleSize(2, 1, 0);
		double SombraSup3 = candle.getCandleSize(2, 2, 1);
		double SombraInf3 = candle.getCandleSize(2, 2, 2);

		double TendenciaUltimosCandles = candle.getTendencia(2, 3);

		boolean TerceiroCandleBaixa = candle.Close(2) < candle.Open(2)
				&& CorpoCandle3 > SombraSup3 + SombraInf3;

		boolean SegundoCandleGAP = CorpoCandle2 * 2 < CorpoCandle3
				&& candle.Open(1) < candle.Close(2)
				&& candle.Close(1) < candle.Close(2)
				&& candle.Open(1) < candle.Open(0)
				&& candle.Close(1) < candle.Open(0) && SombraSup2 > SombraInf2;

		boolean PrimeiroCandleAlta = candle.Close(0) >= candle.Open(2)
				&& candle.Open(0) < candle.Close(2)
				&& CorpoCandle1 > CorpoCandle2; // AND ( CorpoCandle1 >
												// (SombraSup1+SombraInf1) )
												// //AND (Close >= Open(2))

		boolean EstrelaDaManha = TerceiroCandleBaixa && SegundoCandleGAP
				&& PrimeiroCandleAlta && TendenciaUltimosCandles == 0;

		if (EstrelaDaManha) {
			result = 5;
		}

		return result;
	}
}
