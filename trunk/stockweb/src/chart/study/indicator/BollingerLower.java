package chart.study.indicator;

import chart.study.QuoteHistory;

/**
 * Lower Bollinger Band
 */
public class BollingerLower extends Indicator {
    private final int length;
    private final double deviations;
    private int sizeLimitHistory = 0;

    public BollingerLower(QuoteHistory qh, int length, double deviations) {
        super(qh);
        this.length = length;
        this.deviations = deviations;
    }

    public void setLimitHistory(int size){
    	this.sizeLimitHistory = size;
    }

    @Override
    public double calculate() {
        int lastBar = qh.size() - 1;

        double squareSum = 0;
        double sum = 0;

        if(sizeLimitHistory != 0){
        	lastBar = sizeLimitHistory - 1;
        }

        int firstBar = lastBar - length + 1;

        for (int bar = firstBar; bar <= lastBar; bar++) {
            double barClose = qh.getPriceBar(bar).getClose();
            sum += barClose;
            squareSum += barClose * barClose;
        }

        double stDev = length * squareSum - sum * sum;
        stDev /= (length * (length - 1));
        stDev = Math.sqrt(stDev);

        value = (sum / length) - deviations * stDev;
        return value;
    }
}
