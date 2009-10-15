package chart.study;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

/**
 * Encapsulates the price bar information.
 */
public class PriceBar {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	private long date;
	private double open, high, low, close;
	private long volume;

	/**
	 * This constructor used to create a new real time bar whose OHLC values are
	 * the same as the last completed bar.
	 */
	public PriceBar(double price) {
		this(0, price, price, price, price, 0);
	}

	/**
	 * This constructor used to create a new real time bar
	 */
	public PriceBar(double open, double high, double low, double close,
			long volume) {
		this(0, open, high, low, close, volume);
	}

	/**
	 * This constructor is used to create a new historical bar
	 */
	public PriceBar(long date, double open, double high, double low,
			double close, long volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}

	public double getClose() {
		return close;
	}

	public long getDate() {
		return date;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getMidpoint() {
		return (low + high) / 2;
	}

	public double getOpen() {
		return open;
	}

	public String getShortDate() {
		synchronized (dateFormat) {
			return dateFormat.format(new Date(date));
		}
	}

	public long getVolume() {
		return volume;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" date: ").append(getShortDate());
		sb.append(" open: ").append(open);
		sb.append(" high: ").append(high);
		sb.append(" low: ").append(low);
		sb.append(" close: ").append(close);
		sb.append(" volume: ").append(volume);

		return sb.toString();
	}
}
