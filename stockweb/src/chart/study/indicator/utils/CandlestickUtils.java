package chart.study.indicator.utils;

import chart.study.QuoteHistory;

public class CandlestickUtils {
	private QuoteHistory history;

	public CandlestickUtils(QuoteHistory history) {
		this.history = history;
	}

	public double Close(int candle) {
		return history.getPriceBar(position(candle)).getClose();
	}

	// Retorna a dimensao do corpo do candle
	public double CorpoCandle(int candle) {
		double CorpoCandle = 0;

		double Open = history.getPriceBar(candle).getOpen();
		double Close = history.getPriceBar(candle).getClose();
		double High = history.getPriceBar(candle).getHigh();

		if (Close > Open) {
			CorpoCandle = Math.abs(Open / Close * 100 - 100);
		} else {
			// CorpoCandle = ABS(((Close[candle] / Open[candle])*100)-100)
			CorpoCandle = Math.abs(High / Close * 100 - 100);
		}
		return CorpoCandle;
	}

	public boolean Doji(int candle) {
		boolean result = false;
		double range = Math.abs(High(candle) - Low(candle));
		result = range > Math.abs(Open(candle) - Close(candle) * 5)
				|| Open(candle) == Close(candle);
		return result;
	}

	// Retorna a dimensao do candle
	public double getCandleSize(int candle, int type, int typeSombra) {
		double result = 0;

		double Open = history.getPriceBar(candle).getOpen();
		double Close = history.getPriceBar(candle).getClose();
		double High = history.getPriceBar(candle).getHigh();
		double Low = history.getPriceBar(candle).getLow();

		boolean SinalCandlePositivo = Close > Open;
		if (type == 1) {

			if (SinalCandlePositivo) {
				result = Math.abs(Close / Open * 100 - 100);
			} else {
				result = Math.abs(Open / Close * 100 - 100);
			}
		} else if (type == 2) {

			if (SinalCandlePositivo) {
				if (typeSombra == 1) {
					result = Math.abs(High / Close * 100 - 100);
				} else if (typeSombra == 2) {
					result = Math.abs(Open / Low * 100 - 100);
				}
			} else {
				if (typeSombra == 1) {
					result = Math.abs(High / Open * 100 - 100);
				} else if (typeSombra == 2) {
					result = Math.abs(Close / Low * 100 - 100);
				}
			}
		}

		if (typeSombra == 3) {
			result = Math.abs(High / Low * 100 - 100);
		}

		return result;

	}

	// Retorna a tendencia de alta, baixa e sem tendencia
	public int getTendencia(int begin, int end) {
		int result = 0;

		int QtdeTendenciaBaixa = 0;
		int QtdeTendenciaAlta = 0;

		for (int x = begin; x < end - 1; x++) {
			int resultTendenciaCandle = getTendenciaAltaBaixa(x);

			if (resultTendenciaCandle == 0) {
				QtdeTendenciaBaixa = QtdeTendenciaBaixa + 1;
			}

			if (resultTendenciaCandle == 1) {
				QtdeTendenciaAlta = QtdeTendenciaAlta + 1;
			}
		}

		boolean ExistTendencia = QtdeTendenciaBaixa != 0
				&& QtdeTendenciaAlta != 0;

		if (ExistTendencia) {
			result = -1;
		} else if (QtdeTendenciaBaixa == 0) {
			result = 1;
		} else if (QtdeTendenciaAlta == 0) {
			result = 0;
		}
		return result;
	}

	// Retorna a tendencia de alta ou de baixa
	public int getTendenciaAltaBaixa(int candleposition) {
		int result = 0;

		boolean SinalCandle1 = false;
		boolean SinalCandle2 = false;
		boolean TendenciaCandleAlta = false;
		boolean TendenciaCandleBaixa = false;
		int QtdeTendenciaBaixa = 0;
		int QtdeTendenciaAlta = 0;

		int x = candleposition;

		SinalCandle1 = Close(x) > Open(x);
		SinalCandle2 = Close(x + 1) > Open(x + 1);

		// Verificando Tendencia de Baixa dos Candles
		if (SinalCandle1 && SinalCandle2) {
			TendenciaCandleAlta = Close(x) > Close(x + 1);
			TendenciaCandleBaixa = Close(x) < Close(x + 1);
		} else if (SinalCandle1 && SinalCandle2 == false) {
			TendenciaCandleAlta = Close(x) > Open(x + 1)
					|| Close(x) > Close(x + 1);
			TendenciaCandleBaixa = Close(x) < Open(x + 1)
					&& Close(x) < Close(x + 1);
		} else if (SinalCandle1 == false && SinalCandle2) {
			TendenciaCandleAlta = Open(x) > Close(x + 1)
					&& Close(x) > Open(x + 1);
			TendenciaCandleBaixa = Open(x) < Close(x + 1)
					|| Close(x) < Open(x + 1);
		} else if (SinalCandle1 == false && SinalCandle2 == false) {
			TendenciaCandleAlta = Open(x) > Open(x + 1)
					&& Close(x) > Open(x + 1);
			TendenciaCandleBaixa = Open(x) < Open(x + 1)
					|| Close(x) < Close(x + 1);
		}

		if (TendenciaCandleBaixa) {
			QtdeTendenciaBaixa = QtdeTendenciaBaixa + 1;
		}

		if (TendenciaCandleAlta) {
			QtdeTendenciaAlta = QtdeTendenciaAlta + 1;
		}

		if (QtdeTendenciaBaixa == 0) { // QtdeTendenciaBaixa = 0 significa que a
										// tendencia � de ALTA
			result = 1;
		} else if (QtdeTendenciaAlta == 0) { // QtdeTendenciaAlta = 0 significa
												// que a tendencia � de BAIXA
			result = 0;
		}

		return result;
	}

	// Retorna o valor medio do volume
	public double getVolumeMedio(int ncandles) {

		double MediaVolume = 0;
		for (int x = 1; x < ncandles; x++) {
			MediaVolume = MediaVolume + Volume(x);
		}
		double Media = MediaVolume / ncandles;
		// MediaMin = ((MediaVolume / ncandles) / 4)*2
		return Media;// , MediaMin AS "Media Minima"
	}

	// Retorna o valor medio do volume
	public double getVolumeMedioMinimo(int ncandles) {

		double MediaVolume = 0;
		for (int x = 1; x < ncandles; x++) {
			MediaVolume = MediaVolume + Volume(x);
		}
		double MediaMin = MediaVolume / ncandles / 2.55 * 2;
		return MediaMin;
	}

	public double High(int candle) {
		return history.getPriceBar(position(candle)).getHigh();
	}

	public int isCandleLongTrend(int candles) {
		int result = 0;

		double CorpoCandle1 = getCandleSize(0, 1, 0);
		double SombraSup1 = getCandleSize(0, 2, 1);
		double SombraInf1 = getCandleSize(0, 2, 2);

		int TendenciaUltimosCandles = getTendencia(0, candles);

		boolean PrimeiroCandleBaixa = Open(1) > Close(1)
				&& CorpoCandle1 > (SombraSup1 + SombraInf1) * 1.2;

		boolean PrimeiroCandleAlta = Open(1) < Close(1)
				&& CorpoCandle1 > (SombraSup1 + SombraInf1) * 1.2;

		boolean CandleLongDownTrend = TendenciaUltimosCandles == 0
				&& PrimeiroCandleBaixa;

		boolean CandleLongUpTrend = TendenciaUltimosCandles == 1
				&& PrimeiroCandleAlta;

		if (CandleLongDownTrend) {
			result = -1;
		}

		if (CandleLongUpTrend) {
			result = 1;
		}

		return result;

	}

	// Verifica se o candle � qualquer tipo de martelo ou enforcado
	public boolean isHangingManORHammer(QuoteHistory history) {
		boolean result = false;

		double Open1 = history.getPriceBar(1).getOpen();
		double Close1 = history.getPriceBar(1).getClose();
		// Hanging Man OR Hammer

		double CorpoCandle1 = getCandleSize(0, 1, 0);
		double SombraSup1 = getCandleSize(0, 2, 1);
		double SombraInf1 = getCandleSize(0, 2, 2);

		double LarguraCandle1 = getCandleSize(0, 3, 0);

		double percLargCandle1 = 100 - Math.abs(CorpoCandle1 / LarguraCandle1
				* 100 - 100);

		boolean HangingMan = Open1 != Close1 && percLargCandle1 > 10
				&& percLargCandle1 <= 30 && SombraInf1 > CorpoCandle1 * 2
				&& SombraSup1 / (SombraInf1 + CorpoCandle1) < 0.10;

		if (HangingMan) {
			result = true;
		}

		return result;

	}

	public double Low(int candle) {
		return history.getPriceBar(position(candle)).getLow();
	}

	public double Open(int candle) {
		return history.getPriceBar(position(candle)).getOpen();
	}

	private int position(int candle) {
		return candle;
	}

	public double Volume(int candle) {
		return history.getPriceBar(position(candle)).getVolume();
	}

}
