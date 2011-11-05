package ai.environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONObject;

import persistence.Stock;
import persistence.dao.QuoteEngineDAO;
import persistence.vo.QuoteVO;
import ai.Percept;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify:
 */

public class DBEnvironmentView implements EnvironmentView {

	// Referencia format param: http://www.gummy-stuff.org/Yahoo-data.htm

	private List<QuoteVO> applyParameters(JSONObject json) {

		String dayDI = String.valueOf(json.get("filterDI")).substring(6, 8);
		String monthDI = String.valueOf(json.get("filterDI")).substring(4, 6);
		String yearDI = String.valueOf(json.get("filterDI")).substring(0, 4);

		String dayDF = String.valueOf(json.get("filterDF")).substring(6, 8);
		String monthDF = String.valueOf(json.get("filterDF")).substring(4, 6);
		String yearDF = String.valueOf(json.get("filterDF")).substring(0, 4);

		QuoteEngineDAO dao = new QuoteEngineDAO();
		return dao.getQuote((String) json.get("symbol"), yearDI + monthDI + dayDI, yearDF + monthDF + dayDF);
	}

	public Percept envChanged(String command) {
		Percept percept = null;
		boolean firstRecord = true;
		// Precisa setar dentro do command outro parametro para definir o
		// intervalo de data
		try {
			percept = new Percept();
			ArrayList<Stock> lsStocks = new ArrayList();
			Stock stock = null;
			JSONObject json = new JSONObject(command);

			List<QuoteVO> quoteVO = applyParameters(json);

			if (firstRecord) {
				stock = new Stock();
				stock.setCodneg((String) json.get("symbol"));
				stock.setDataPregao(getLastData(quoteVO.get(0).getDate()));
				stock.setPreabe(quoteVO.get(0).getClose());
				stock.setPremax(quoteVO.get(0).getClose());
				stock.setPremin(quoteVO.get(0).getClose());
				stock.setPreult(quoteVO.get(0).getClose());
				stock.setVoltot(quoteVO.get(0).getVolume());
				lsStocks.add(stock);
				stock = new Stock();
				firstRecord = false;
			}

			for (int x = 0; x < quoteVO.size(); x++) {
				stock = new Stock();
				stock.setCodneg((String) json.get("symbol"));
				stock.setDataPregao(quoteVO.get(x).getDate());
				stock.setPreabe(quoteVO.get(x).getOpen());
				stock.setPremax(quoteVO.get(x).getHigh());
				stock.setPremin(quoteVO.get(x).getLow());
				stock.setPreult(quoteVO.get(x).getClose());
				stock.setVoltot(quoteVO.get(x).getVolume());
				lsStocks.add(stock);
			}

			percept.setAttribute("change", "true");
			percept.setAttribute("typeObject", "StockList");
			percept.setAttribute("ArrayStockObject", lsStocks);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(command);

		return percept;
	}

	private String getLastData(String data) {
		String dateFormat = "yyyyMMdd";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				dateFormat);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(data.substring(0, 4)), Integer
				.parseInt(data.substring(4, 6)) - 1, Integer.parseInt(data
				.substring(6, 8)));
		calendar.add(Calendar.DAY_OF_MONTH, +1);
		return sdf.format(calendar.getTime());
	}
}