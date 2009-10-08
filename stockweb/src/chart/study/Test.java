package chart.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import persistence.Stock;
import chart.study.indicator.BollingerLower;
import chart.study.indicator.BollingerMiddle;
import chart.study.indicator.BollingerUpper;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		test.loadFile("COTAHIST_M062008.TXT");
		test.loadFile("COTAHIST_M072008.TXT");
		test.loadFile("COTAHIST_M082008.TXT");
		test.loadQuote();
	}

	private long date, volume;
	private double open, high, low, close;
	private PriceBar priceBar = null;
	private QuoteHistory history = null;

	private ArrayList lsStocks = null;

	public Test() {
		history = new QuoteHistory("PETR4");
	}

	public void loadFile(String filename) {

		try {
			FileReader file = new FileReader(new File(filename));
			BufferedReader buffer = new BufferedReader(file);
			buffer.readLine();
			buffer.readLine();
			while (buffer.ready()) {
				saveObject(buffer.readLine());
			}
			file.close();

			// DBSession.getIstance().disconnect(DBSession.getIstance().getConnection());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadQuote() {

		/*
		 * priceBar = new PriceBar(date, open, high, low, close, volume);
		 * history.addHistoricalPriceBar(priceBar);
		 */
		BollingerUpper upper = new BollingerUpper(history, 22, 2.0);
		BollingerMiddle middle = new BollingerMiddle(history, 22);
		BollingerLower lower = new BollingerLower(history, 22, 2.0);

		double _upper = upper.calculate();
		double _middle = middle.calculate();
		double _lower = lower.calculate();

		System.out.println("Bollinger Upper: " + upper);
		System.out.println("Bollinger Middle: " + middle);
		System.out.println("Bollinger Lower: " + lower);
		System.out.println("Bollinger Upper -> Date: " + upper.getDate());
		System.out.println("\n Percentual : " + _upper / _lower);

	}

	private double parseDouble(double price) {
		String value = String.valueOf(price);
		String tmp = value.substring(0, value.length() - 4) + "."
				+ value.substring(value.length() - 4, value.indexOf("."));
		System.out.println(tmp);
		return Double.parseDouble(tmp);

	}

	private void saveObject(String line) {
		Stock ibov = new Stock();
		ibov.setTipreg(line.substring(0, 2));
		ibov.setDataPregao(line.substring(2, 10));
		ibov.setCodbdi(line.substring(10, 12));
		ibov.setCodneg(line.substring(12, 24));
		ibov.setTpmerc(line.substring(24, 27));
		ibov.setNomres(line.substring(27, 39));
		ibov.setEspeci(line.substring(39, 49));
		ibov.setPrazot(line.substring(49, 52));
		ibov.setModref(line.substring(52, 56));
		ibov.setPreabe(line.substring(56, 69));
		ibov.setPremax(line.substring(69, 82));
		ibov.setPremin(line.substring(82, 95));
		ibov.setPremed(line.substring(95, 108));
		ibov.setPreult(line.substring(108, 121));
		ibov.setPreofc(line.substring(121, 134));
		ibov.setPreofv(line.substring(134, 147));
		ibov.setTotneg(line.substring(147, 152));
		ibov.setQuatot(line.substring(152, 170));
		ibov.setVoltot(line.substring(170, 188));
		ibov.setPreexe(line.substring(188, 201));
		ibov.setIndopc(line.substring(201, 202));
		ibov.setDatven(line.substring(202, 210));
		ibov.setFatcot(line.substring(210, 217));
		ibov.setPtoexe(line.substring(217, 230));
		ibov.setCodisi(line.substring(230, 242));
		ibov.setDismes(line.substring(242, 245));

		if (!ibov.getDataPregao().equals("COTAHIST")) {
			date = Long.parseLong(ibov.getDataPregao());
			open = ibov.getPreabe();
			high = ibov.getPremax();
			low = ibov.getPremin();
			close = ibov.getPreult();
			volume = Long.parseLong(ibov.getVoltot());
		}
		if (ibov.getCodneg().trim().equals("VALE5")) {
			// System.out.println(ibov.getCodneg() + " - ult = " +
			// ibov.getPreult() + " - abert = " + ibov.getPreabe() +
			// " - data = " + ibov.getDataPregao());
			priceBar = new PriceBar(date, parseDouble(open), parseDouble(high),
					parseDouble(low), parseDouble(close), volume);
			history.addHistoricalPriceBar(priceBar);
		}
	}

}
