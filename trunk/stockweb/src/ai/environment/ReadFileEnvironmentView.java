package ai.environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;

import persistence.Stock;
import ai.Percept;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ReadFileEnvironmentView implements EnvironmentView {

	private String pathname = "";

	private String filename = "";

	public ReadFileEnvironmentView(String pathname) {
		this.pathname = pathname;
	}

	public Percept envChanged(String command) {
//		System.out.println("Class: " + this.getClass() + " - param: " + command);
		int index = 0;
		boolean register = false;
		ArrayList<Stock> lsStocks = new ArrayList();
		Percept percept = null;
		BufferedReader buffer = null;
		percept = new Percept();
		JSONObject json = new JSONObject(command);
		try {
			Stock stock = null;
			FileReader file = new FileReader(new File(this.pathname
					+ json.getString("filename")));
			buffer = new BufferedReader(file);
			buffer.readLine();
			while (buffer.ready()) {
				String line = buffer.readLine();

				stock = getObject(line);

				if (stock.getCodneg().trim().equalsIgnoreCase(
						(String) json.get("symbol"))) {
					if (!json.getString("filterDI").equals("")
							&& json.getString("filterDI").equals(stock.getDataPregao())) {
						register = true;
					}
					if (register) {
						lsStocks.add(stock);
					}
					// System.out.println(stock.getDataPregao());
					if (json.getString("filterDF")
							.equals(stock.getDataPregao())) {
						break;
					}

				}

			}

			buffer.close();

			percept.setAttribute("change", "true");
			percept.setAttribute("typeObject", "StockList");
			percept.setAttribute("ArrayStockObject", lsStocks);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return percept;
	}

	private Stock getObject(String line) {
		Stock stock = new Stock();
		stock.setTipreg(line.substring(0, 2));
		stock.setDataPregao(line.substring(2, 10));
		stock.setCodbdi(line.substring(10, 12));
		stock.setCodneg(line.substring(12, 24));
		stock.setTpmerc(line.substring(24, 27));
		stock.setNomres(line.substring(27, 39));
		stock.setEspeci(line.substring(39, 49));
		stock.setPrazot(line.substring(49, 52));
		stock.setModref(line.substring(52, 56));

		stock.setPreabe(line.substring(56, 69));
		stock.setPremax(line.substring(69, 82));
		stock.setPremin(line.substring(82, 95));
		stock.setPremed(line.substring(95, 108));
		stock.setPreult(line.substring(108, 121));

		stock.setPreofc(line.substring(121, 134));
		stock.setPreofv(line.substring(134, 147));
		stock.setTotneg(line.substring(147, 152));
		stock.setQuatot(line.substring(152, 170));
		stock.setVoltot(line.substring(170, 188));
		stock.setPreexe(line.substring(188, 201));
		stock.setIndopc(line.substring(201, 202));
		stock.setDatven(line.substring(202, 210));
		stock.setFatcot(line.substring(210, 217));
		stock.setPtoexe(line.substring(217, 230));
		stock.setCodisi(line.substring(230, 242));
		stock.setDismes(line.substring(242, 245));
		return stock;

	}
}