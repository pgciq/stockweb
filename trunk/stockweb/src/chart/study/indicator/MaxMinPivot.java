package chart.study.indicator;

import java.util.List;
import java.util.StringTokenizer;

import chart.study.PriceBar;
import chart.study.QuoteHistory;

/**
 * Exponential Moving Average.
 */
public class MaxMinPivot extends Indicator {
	private final int length;

	public static double OK = 0;

	public static double NO = 0;

	public static double totalPercent = 0;

	public static double totalPerdaPercent = 0;

	public MaxMinPivot(QuoteHistory qh, int length) {
		super(qh);
		this.length = length;
	}

	@Override
	public double calculate() {
		List<PriceBar> priceBars = qh.getAll();
		int lastBar = priceBars.size() - 1;

		double lastMax = priceBars.get(lastBar).getHigh();
		double lastMin = priceBars.get(lastBar).getLow();
		double totMax = 0;
		double totMin = 0;

		double priceMax = 0;
		double priceMin = 0;

		long lastDate = 0L;

		for (int i = lastBar - length; i < lastBar; i++) {
			lastDate = priceBars.get(i).getDate();
			priceMax = priceBars.get(i).getHigh();
			priceMin = priceBars.get(i).getLow();
			System.out.println("priceBars.get(i).getHigh(): "
					+ priceBars.get(i).getHigh());
			totMax += priceBars.get(i).getHigh();
			totMin += priceBars.get(i).getLow();
		}

		System.out
				.println(">>>>>>>>>>>>>>>>>> priceBars.get(lastBar).getDate(): "
						+ priceBars.get(lastBar).getDate());
		System.out.println(">>>>>>>>>>>>>>>>>> Last Date: " + lastDate);
		// System.out.println(">>>>>>>>>>>>>>>>>>>>>>> priceMax = "+ priceMax +
		// " || lastMax = " + lastMax);
		double media = 0;
		double perc = 0;
		double totale = 0;
		double variation = 1.0065; // 1.0065;
		String rsTest = "";

		// TEST For Price MAX
		media = totMax / length;

		// Calculando porcentagem
		perc = media < priceMax ? priceMax / media : media / priceMax;

		// totale = (priceMax * perc) * variation;
		totale = priceMax * perc / variation; // TEST
		System.out.println("MAX de ALTA = " + totale);
		// System.out.println(">>>>>>>>>> Differ tra MAX ALTA = " + (((totale /
		// priceMax)*100)-100));
		rsTest = String.valueOf(totale);

		// perc = (media < priceMax) ? priceMax / media : media / priceMax;
		totale = priceMax / perc / variation;
		// totale = (priceMax / perc) * variation; //TEST
		System.out.println("MAX de BAIXA = " + totale + "\n");
		// System.out.println(">>>>>>>>>> Differ tra MAX BAIXA = " + (((totale /
		// priceMax)*100)-100));
		rsTest += "," + String.valueOf(totale);

		// TEST For Price MIN
		media = totMin / length;
		perc = media < priceMin ? priceMin / media : media / priceMin;
		// perc = priceMin / media;
		totale = priceMin * perc * variation;
		System.out.println("MIN de ALTA = " + totale);
		// System.out.println(">>>>>>>>>> Differ tra MIN ALTA = " + (((priceMin
		// / totale)*100)-100));
		rsTest += "," + String.valueOf(totale);

		totale = priceMin / perc * variation;
		// totale = (priceMin / perc) / variation; // TEST
		System.out.println("MIN de BAIXA = " + totale + "\n");
		// System.out.println(">>>>>>>>>> Differ tra MIN BAIXA = " + (((priceMin
		// / totale)*100)-100));
		rsTest += "," + String.valueOf(totale);

		System.out.println("\nlastMax: " + lastMax);
		System.out.println("lastMin: " + lastMin + "\n");

		System.out
				.println(validation(rsTest, lastMax, lastMin)
						+ " ========================================================== ");

		return value;
	}

	private String validation(String result, double max, double min) {
		double maxA, maxB, minA, minB, tmp;
		int exist = 0;
		StringTokenizer token = new StringTokenizer(result, ",");
		while (token.hasMoreTokens()) {
			tmp = Double.parseDouble((String) token.nextElement());
			if (tmp <= max && tmp >= min) {
				exist += 1;
			}
		}
		if (exist >= 2) {
			OK += 1;
			return "OK";
		}

		/*
		 * maxA = Double.parseDouble((String)token.nextElement()); maxB =
		 * Double.parseDouble((String)token.nextElement()); minA =
		 * Double.parseDouble((String)token.nextElement()); minB =
		 * Double.parseDouble((String)token.nextElement());
		 * 
		 * if((maxA <= max) && (maxA >= min)){
		 * System.out.println("MAX de ALTA - OK"); if((minA >= min) && (minA <=
		 * max)){
		 * 
		 * totalPercent += (((maxA / minA)100)-100);
		 * System.out.println("MIN de ALTA - OK \n Ganho porcentual = " +
		 * (((maxA / minA)100)-100) );
		 * 
		 * OK += 1; return "OK"; } }else{ if((maxB <= max) && (maxB >= min)){
		 * System.out.println("MAX de BAIXA - OK"); if((minB >= min) && (minB >=
		 * min)){
		 * 
		 * totalPercent += (((minB / maxB)100)-100);
		 * System.out.println("MIN de BAIXA - OK \n Ganho porcentual = " +
		 * (((minB / maxB)100)-100) );
		 * 
		 * OK += 1; return "OK"; } } } totalPerdaPercent += (max / min);
		 */
		NO += 1;
		return "NO";
	}
}
