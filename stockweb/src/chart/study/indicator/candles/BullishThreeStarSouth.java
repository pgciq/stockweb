package chart.study.indicator.candles;

import chart.study.indicator.Indicator;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * Lower Bollinger Band
 */
public class BullishThreeStarSouth extends Indicator {
	private int length;

	@Override
	public double calculate() {
		// ThreeStatusSouth - Esse candle precisa de confirmaçao antes de operar
		CandlestickUtils candle = new CandlestickUtils(qh);

		double result = 0;
		double myMediaMinima = candle.getVolumeMedioMinimo(5);

		boolean TerceiroCandleBaixa = (candle.Open(2) > candle.Close(2));
		boolean SegundoCandleBaixa = (candle.Open(1) > candle.Close(1)) && (candle.High(1) < candle.High(2)) && (candle.Low(1) > candle.Low(2));
		boolean PrimeiroCandleBaixa = (candle.Open(0) > candle.Close(0)) && (candle.High(0) < candle.High(1)) && (candle.Low(0) > candle.Low(1));
		boolean ThreeStartSouth = TerceiroCandleBaixa && SegundoCandleBaixa && PrimeiroCandleBaixa; //&& (TendenciaUltimosCandles=0)
		if(ThreeStartSouth && (myMediaMinima <= candle.Volume(0))) {
			result = 4;
		}
		
		return result;
	}
}
