package ai.agent.program;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONObject;

import persistence.Stock;
import persistence.dao.ChartSettingEngineDAO;
import persistence.dao.ScriptEngineDAO;
import persistence.vo.Script;
import ai.AgentProgram;
import ai.Percept;
import ai.strategy.ScriptStrategy;
import chart.study.History;
import chart.study.PriceBar;
import chart.study.QuoteHistory;
import chart.study.indicator.utils.CandlestickUtils;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ExecuteScriptAP extends AgentProgram {

	private PriceBar priceBar = null;

	public QuoteHistory history = null;

	private DecimalFormat df = new DecimalFormat("#.####");

	public ExecuteScriptAP() {

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

		String type = (String) percept.getAttribute("typeObject");
		if (type == null || type.indexOf("Stock") == -1) {
			return "";
		}

		if (type.equals("StockList")) {
			ArrayList lsSotck = (ArrayList) percept.getAttribute("ArrayStockObject");
			for (int i = 0; i < lsSotck.size(); i++) {
				apply((Stock) lsSotck.get(i));
			}
			return "ok";
		}

		apply((Stock) percept.getAttribute("StockObject"));

		return "ok";
	}

	public String getListResultScript() {
		String result = "";
		ArrayList lsResult = new ArrayList();

		try {

			ScriptStrategy strategy = new ScriptStrategy();
//			ScriptEngineDAO engineDAO = new ScriptEngineDAO();
//			List<Script> lsObject = new ArrayList<Script>(engineDAO.getListObject());
			List<Script> lsObject = new ArrayList<Script>((new ChartSettingEngineDAO()).getListObject());

			for (int position = 0; position < history.size() - 10; position++) {

				String date = String.valueOf(history.getPriceBar(position).getDate());

				for (int x = 0; x < lsObject.size(); x++) {
					lsResult.add(strategy.applyScript(lsObject.get(x).getName(), lsObject.get(x).getScript(), date, new CandlestickUtils(history), position, this, new HashMap<String,String>()));
				}
			}
			String token = "";
			for (int x = 0; x < lsResult.size(); x++) {
				result = result + token + lsResult.get(x);
				token = "|";
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public String getResultScript(String scriptName, int lastbar) {
		String result = "";
		Script script = null;

		try {

			ScriptStrategy strategy = new ScriptStrategy();
			ScriptEngineDAO engineDAO = new ScriptEngineDAO();
			script = (Script) engineDAO.getObjectByName(scriptName);

			String date = String.valueOf(history.getPriceBar(0).getDate());

			result = strategy.applyScript(script.getName(), script.getScript(), date, new CandlestickUtils(history),lastbar, this, null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	
	public String call(String key, int lastbar, HashMap<String,String> input) throws Exception {
		ScriptStrategy strategy = new ScriptStrategy();
		Script script = (Script) (new ScriptEngineDAO()).getObjectByName(key);
		return strategy.applyScript(script.getName(), script.getScript(), "", new CandlestickUtils(history),lastbar, this, input);
	}
	
	public String get(String result, String key){
		JSONObject json = new JSONObject(result);
		return json.getString(key);
	}
}
