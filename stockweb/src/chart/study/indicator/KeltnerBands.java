package chart.study.indicator;

import chart.study.QuoteHistory;

/*
 * http://www.erlangerquote.com/new_B.htm
 *    Mid Band: MOV( (H+L+C)/3, 10, Simple )
 *    Upper Keltner Band:  MOV((H+L+C)/3,10,S) + MOV((H-L),10,S)
 *    Lower Keltner Band:  MOV((H+L+C)/3,10,S) - MOV((H-L),10,S)
 *    
 *    NAO E'COMPLETO, DEVO APLICAR ESSAS FORMULAS
 */
public class KeltnerBands extends Indicator {
	private final int length;

	// private final double multiplier;

	public KeltnerBands(QuoteHistory qh, int length) {
		super(qh);
		this.length = length;
		// multiplier = 2. / (length + 1.);
	}

	@Override
	public double calculate() {
		/*
		 * List<PriceBar> priceBars = qh.getAll(); int lastBar =
		 * priceBars.size() - 1; int firstBar = lastBar - 2 length + 1; double
		 * ema = priceBars.get(firstBar).getClose();
		 * 
		 * for (int bar = firstBar; bar <= lastBar; bar++) { double barClose =
		 * priceBars.get(bar).getClose(); ema += (barClose - ema) multiplier; }
		 * 
		 * value = ema;
		 */
		return value;
	}
}
