package ai.environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.json.JSONObject;

import persistence.Stock;
import ai.Percept;

public class YahooEnvironmentView implements EnvironmentView {

	private String hosting = "http://ichart.yahoo.com/table.csv?s=${symbol}&a=${monthDI}&b=${dayDI}&c=${yearDI}&d=${monthDF}&e=${dayDF}&f=${yearDF}&g=${interval}&ignore=.csv";

	// Referencia format param: http://www.gummy-stuff.org/Yahoo-data.htm

	private String applyParameters(JSONObject json) {

		String dayDI = String.valueOf(json.get("filterDI")).substring(6, 8);
		String monthDI = String.valueOf(Integer.parseInt(String.valueOf(
				json.get("filterDI")).substring(4, 6)) - 1);
		String yearDI = String.valueOf(json.get("filterDI")).substring(0, 4);

		String dayDF = String.valueOf(json.get("filterDF")).substring(6, 8);
		String monthDF = String.valueOf(Integer.parseInt(String.valueOf(
				json.get("filterDF")).substring(4, 6)) - 1);
		String yearDF = String.valueOf(json.get("filterDF")).substring(0, 4);

		String host = hosting.replace("${dayDI}", dayDI).replace("${monthDI}",
				monthDI).replace("${yearDI}", yearDI);
		host = host.replace("${dayDF}", dayDF).replace("${monthDF}", monthDF)
				.replace("${yearDF}", yearDF);
		host = host.replace("${symbol}", (String) json.get("symbol"));
		host = host.replace("${interval}", (String) json.get("interval"));

		return host;
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

			String content = getContentHttp(applyParameters(json));
			String open, high, low, close, volume;
			StringTokenizer token = new StringTokenizer(content, "|");
			token.nextToken();
			while (token.hasMoreElements()) {
				stock = new Stock();
				StringTokenizer tkField = new StringTokenizer(
						token.nextToken(), ",");
				// stock.setCodneg(tkField.nextToken());
				String data = tkField.nextToken();
				String[] month = { "gen,feb,mar,apr,mag,giu,lug,ago,set,ott,nov,dic" };
				for (int x = 0; x < month.length; x++) {
					data = data.replaceAll(month[x], String.valueOf(x + 1))
							.replaceAll("-", "");
				}

				open = tkField.nextToken();
				high = tkField.nextToken();
				low = tkField.nextToken();
				close = tkField.nextToken();
				volume = tkField.nextToken();

				if (firstRecord) {
					stock.setCodneg((String) json.get("symbol"));
					stock.setDataPregao(getLastData(data));
					stock.setPreabe(close);
					stock.setPremax(close);
					stock.setPremin(close);
					stock.setPreult(close);
					stock.setVoltot(volume);
					lsStocks.add(stock);
					stock = new Stock();
					firstRecord = false;
				}

				stock.setCodneg((String) json.get("symbol"));
				stock.setDataPregao(data);
				stock.setPreabe(open);
				stock.setPremax(high);
				stock.setPremin(low);
				stock.setPreult(close);
				stock.setVoltot(volume);
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

	public String getContentHttp(String url) throws Exception {
		String result = "";
		try {
			URLConnection yc = new URL(url).openConnection();
			InputStreamReader inputStreamReader = new InputStreamReader(yc
					.getInputStream());
			BufferedReader in = new BufferedReader(inputStreamReader);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result += inputLine + "|";
			}
			in.close();
			inputStreamReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

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