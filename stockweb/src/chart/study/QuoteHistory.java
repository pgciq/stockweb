package chart.study;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds and validates the priceBar history for a strategy.
 */
public class QuoteHistory {

	private PriceBar nextBar;
	private final List<PriceBar> priceBars = new ArrayList<PriceBar>();

	private final String strategyName;

	public QuoteHistory(String strategyName) {
		this.strategyName = strategyName;
	}

	public void addHistoricalPriceBar(PriceBar priceBar) {
		if (!existPiceBar(priceBar.getDate())) {
			priceBars.add(priceBar);
		}
	}

	private boolean existPiceBar(long date) {
		boolean result = false;
		for (PriceBar price : priceBars) {
			if (price.getDate() == date) {
				result = true;
			}
		}
		return result;
	}

	public List<PriceBar> getAll() {
		return priceBars;
	}

	public PriceBar getFirstPriceBar() {
		return priceBars.get(0);
	}

	public PriceBar getLastPriceBar() {
		return priceBars.get(priceBars.size() - 1);
	}

	public PriceBar getPriceBar(int index) {
		return priceBars.get(index);
	}

	public int getSize() {
		return priceBars.size();
	}

	public String getStrategyName() {
		return strategyName;
	}

	public boolean isValid() {
		// todo: da fare
		return false;
	}

	public int size() {
		return priceBars.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (PriceBar priceBar : priceBars) {
			sb.append(priceBar).append(",");
		}

		return sb.toString();
	}

	public synchronized void update(double open, double high, double low,
			double close, long volume) {
		if (nextBar == null) {
			nextBar = new PriceBar(open, high, low, close, volume);
		} else {
			nextBar.setClose(close);
			nextBar.setLow(Math.min(low, nextBar.getLow()));
			nextBar.setHigh(Math.max(high, nextBar.getHigh()));
			nextBar.setVolume(nextBar.getVolume() + volume);
		}

	}

}
