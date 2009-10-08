package ai.agent.program;

import java.util.ArrayList;
import java.util.List;

import persistence.Stock;
import ai.AgentProgram;
import ai.Percept;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.Candlestick;

public class CandlestickAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	private List<String> lsCandle = new ArrayList<String>();

	public CandlestickAP() {
		init();
	}

	private void apply(Stock stock) {
		double open;
		double high;
		double low;
		double close;
		long date;
		long volume;

		date = Long.parseLong(stock.getDataPregao());
		open = stock.getPreabe();
		high = stock.getPremax();
		low = stock.getPremin();
		close = stock.getPreult();
		volume = Long.parseLong(stock.getVoltot());

		history = History.getQuoteHistory(stock.getCodneg());
		priceBar = new PriceBar(date, open, high, low, close, volume);
		history.addHistoricalPriceBar(priceBar);
	}

	@Override
	public String execute(Percept percept, String modal) {
		double open, high, low, close;
		long date, volume;

		String type = (String) percept.getAttribute("typeObject");
		if (type == null || type.indexOf("Stock") == -1) {
			return "";
		}

		if (type.equals("StockList")) {
			ArrayList lsSotck = (ArrayList) percept
					.getAttribute("ArrayStockObject");
			for (int i = 0; i < lsSotck.size(); i++) {
				apply((Stock) lsSotck.get(i));
			}
			return "ok";
		}

		apply((Stock) percept.getAttribute("StockObject"));

		return "ok";
	}

	public String getIdCandlestick() {

		Candlestick candle = new Candlestick(history, lsCandle);
		// candle.calculate();

		return "{type:candlestick, result: " + candle.calculate() + " ,date:"
				+ history.getFirstPriceBar().getDate() + "}";
		// return _lower + "|" + _middle + "|" + _upper;
	}

	private void init() {
		// Single candles
		// lsCandle.add("singles.BearishCandleBlack"); // 0.1

		lsCandle.add("ShootingStar"); // 1
		lsCandle.add("BullishMorningDojiStar"); // 8
		lsCandle.add("BullishMorningStar"); // 5
		lsCandle.add("BullishEngulf"); // 2
		lsCandle.add("BullishDojiStar"); // 7
		lsCandle.add("BullishThreeStarSouth"); // 4
		lsCandle.add("BullishHaramiCross"); // 10
		lsCandle.add("BullishHarami"); // 11
		lsCandle.add("BullishInvertedHammer"); // 12
		lsCandle.add("BullishPiercingLine"); // 13

		lsCandle.add("BearishEngulf"); // -2
		lsCandle.add("BearishHangingMan"); // 3
		lsCandle.add("BearishDojiStar"); // -7
		lsCandle.add("BearishEveningStar"); // -5
		lsCandle.add("BearishEveningDojiStar"); // -8
		lsCandle.add("BullishHaramiCross"); // -10
		lsCandle.add("BearishHarami"); // -11
		lsCandle.add("BearishDarkCloudCover"); // -12
		lsCandle.add("BearishInNeck"); // -13
		lsCandle.add("BearishOnNeck"); // -14
		lsCandle.add("BearishThreeOutsideDown");// -15

	}

}
