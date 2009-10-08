package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Bullish Morning Star
 */
public class BearishEveningStar extends Indicator {

	@Override
	public double calculate() {
		// Estrela da noite (Bearish Evening Star)
		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;

		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);
		double SombraSup1 = candle.getCandleSize(0, 2, 1);
		double SombraInf1 = candle.getCandleSize(0, 2, 2);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);

		double CorpoCandle3 = candle.getCandleSize(2, 1, 0);
		double SombraSup3 = candle.getCandleSize(2, 2, 1);
		double SombraInf3 = candle.getCandleSize(2, 2, 2);

		double TendenciaUltimosCandles = candle.getTendencia(2, 3);
		boolean TerceiroCandleAlta = candle.Close(2) > candle.Open(2)
				&& CorpoCandle3 > SombraSup3 + SombraInf3;
		boolean SegundoCandleGAP = candle.Open(1) > candle.Close(2)
				&& candle.Close(1) > candle.Close(2)
				&& candle.Open(1) > candle.Open(0)
				&& candle.Close(1) > candle.Open(0)
				&& candle.Low(1) > candle.High(2)
				&& CorpoCandle2 < CorpoCandle3 && CorpoCandle2 < CorpoCandle1; // ((CorpoCandle2*2)
																				// <
																				// CorpoCandle3)
		boolean PrimeiroCandleBaixa = candle.Open(0) > candle.Close(0)
				&& candle.Open(0) >= candle.Open(2)
				&& candle.Open(0) > candle.Close(2)
				&& CorpoCandle1 > SombraSup1 + SombraInf1; // AND ( CorpoCandle1
															// >
															// (SombraSup1+SombraInf1)
															// ) //AND (Close >=
															// Open(2))
		boolean EstrelaDaNoite = TerceiroCandleAlta && SegundoCandleGAP
				&& PrimeiroCandleBaixa;

		if (EstrelaDaNoite) {
			result = -5;
			if (TendenciaUltimosCandles == 1) {
				result = -5.30;
			}

			if (candle.Close(2) < candle.Open(0)
					&& candle.Close(2) > candle.Close(0)) {
				result = -5.5;
			}
		}

		return result;
	}
}
