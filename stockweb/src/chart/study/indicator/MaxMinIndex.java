package chart.study.indicator;

import java.util.HashMap;
import java.util.Map;

import chart.study.QuoteHistory;
import chart.study.indicator.utils.CandlestickUtils;
import chart.study.indicator.utils.Utils;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Lower Bollinger Band
 */
public class MaxMinIndex extends Indicator {
	private int ncandles = 1;
	private double ajust01;
	private double ajust02;
	private double ajust03;
	private double ajust04;
	private int sizeLimitHistory = 0;
	private double maxMinIndex01 = 0;
	private double maxMinIndex02 = 0;
	private double maxMinIndex03 = 0;
	private double maxMinIndex04 = 0;
	private double maxMinIndex05 = 0;

	private Map<String, String> mapAjustIndex = new HashMap<String, String>();

	public MaxMinIndex(QuoteHistory qh) {
		super(qh);
		init();
		checkAjustValue();
	}

	public MaxMinIndex(QuoteHistory qh, double ajust01, double ajust02,
			double ajust03, double ajust04) {
		super(qh);
		init();
		checkAjustValue();
		/*
		 * this.ajust01 = ajust01; this.ajust02 = ajust02; this.ajust03 =
		 * ajust03; this.ajust04 = ajust04;
		 */}

	@Override
	public double calculate() {

		CandlestickUtils candle = new CandlestickUtils(qh);

		int lastBar = 1;

		if (sizeLimitHistory != 0) {
			lastBar = sizeLimitHistory - 1;
		}

		double sum2Low = candle.Low(lastBar) + candle.Low(lastBar + 1);
		double percLow = sum2Low / candle.Low(lastBar);

		maxMinIndex01 = candle.High(lastBar + 1) / ajust01;
		maxMinIndex02 = candle.High(lastBar + 1) * ajust02;

		maxMinIndex03 = candle.Low(lastBar) * percLow * ajust03;
		maxMinIndex04 = candle.Low(lastBar) * percLow / ajust04;

		// Essa rotina serve para verificar qual a minima mais provavel com base
		// no valor da minima do dia anterior modificada -0.01
		maxMinIndex05 = maxMinIndex04;
		double lowSimulated = candle.Low(lastBar);
		while (lowSimulated > maxMinIndex05) {
			lowSimulated -= 0.01;
			sum2Low = lowSimulated + candle.Low(lastBar + 1);
			percLow = sum2Low / lowSimulated;
			maxMinIndex05 = lowSimulated * percLow / ajust04;
		}

		return 1;
	}

	private void checkAjustValue() {
		String param = "";
		int days = Math.abs(Utils.differDays(String.valueOf(qh.getPriceBar(0)
				.getDate()), String.valueOf(qh.getPriceBar(1).getDate())));

		if (days < 5) {
			param = "day";
		} else if (days < 16) {
			param = "week";
		} else if (days < 32) {
			param = "month";
		} else if (days < 357) {
			param = "year";
		}

		this.ajust01 = Double
				.parseDouble(mapAjustIndex.get(param + "_index01"));
		this.ajust02 = Double
				.parseDouble(mapAjustIndex.get(param + "_index02"));
		this.ajust03 = Double
				.parseDouble(mapAjustIndex.get(param + "_index03"));
		this.ajust04 = Double
				.parseDouble(mapAjustIndex.get(param + "_index04"));
	}

	public double getMaxMinIndex01() {
		return maxMinIndex01;
	}

	public double getMaxMinIndex02() {
		return maxMinIndex02;
	}

	public double getMaxMinIndex03() {
		return maxMinIndex03;
	}

	public double getMaxMinIndex04() {
		return maxMinIndex04;
	}

	public double getMaxMinIndex05() {
		return maxMinIndex05;
	}

	public void init() {
		/*
		 * Existe uma diferen�a de ajuste quando a tendencia � de alta ou de
		 * baixa Estou setando a media entre a tendencia, seria bom se eu
		 * conseguisse identificar a tendencia e ajustar de acordo com elas.
		 */

		mapAjustIndex.put("day_index01", "0.955");
		mapAjustIndex.put("day_index02", "1.001");
		mapAjustIndex.put("day_index03", "0.5");
		mapAjustIndex.put("day_index04", "2.043");

		mapAjustIndex.put("week_index01", "0.925");
		mapAjustIndex.put("week_index02", "1.007");
		mapAjustIndex.put("week_index03", "0.5");
		mapAjustIndex.put("week_index04", "2.12");

		mapAjustIndex.put("month_index01", "0.885");
		mapAjustIndex.put("month_index02", "1.005");
		mapAjustIndex.put("month_index03", "0.5");
		mapAjustIndex.put("month_index04", "2.42");

		mapAjustIndex.put("year_index01", "0.715");
		mapAjustIndex.put("year_index02", "0.925");
		mapAjustIndex.put("year_index03", "0.44");
		mapAjustIndex.put("year_index04", "3.42");

	}

	public void setLimitHistory(int size) {
		this.sizeLimitHistory = size;
	}

}
