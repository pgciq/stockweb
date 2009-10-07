package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Lower Bollinger Band
 */
public class BearishThreeOutsideDown extends Indicator {
	// 3 por fora topo

	@Override
	public double calculate() {
		
		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;
		double CorpoCandle1 = candle.getCandleSize(0, 1, 0);
		double SombraSup1 = candle.getCandleSize(0, 2, 1);
		double SombraInf1 = candle.getCandleSize(0, 2, 2);

		double CorpoCandle2 = candle.getCandleSize(1, 1, 0);
		double CorpoCandle3 = candle.getCandleSize(2, 1, 0);

		int TendenciaCandles = candle.getTendencia(1, 3);

		boolean AlturaCandle = (CorpoCandle2 > CorpoCandle3);

		boolean TerceiroCandle = ((candle.Close(2) > candle.Open(2)) || (candle.Doji(2)));

		boolean SegundoCandleBaixa = AlturaCandle && ((candle.Close(2) < candle.Open(1)) && (candle.Close(2) > candle.Close(1)))
		&& ((candle.Open(2) < candle.Open(1)) && (candle.Open(2) > candle.Close(1)));

		boolean PrimeiroCandleBaixa = (candle.Open(0) > candle.Close(0)) && (candle.Close(0) < candle.Close(1)) && (CorpoCandle1 > (SombraSup1 + SombraInf1));
		
		boolean BearishThreeOutsideDown = TerceiroCandle && SegundoCandleBaixa && PrimeiroCandleBaixa && (TendenciaCandles == 1);
		
		if(BearishThreeOutsideDown){
			result = -15;
		}

		return result;
	}
}
