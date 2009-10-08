package ai.strategy;

import java.util.HashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.json.JSONObject;

import chart.study.indicator.utils.CandlestickUtils;

public class ScriptStrategy {

	private ScriptEngine jsEngine = null;
	private Invocable invocableEngine = null;

	public ScriptStrategy() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		jsEngine = mgr.getEngineByName("JavaScript");
		invocableEngine = (Invocable) jsEngine;
	}

	public String applyScript(String scriptName, String script, String date,
			CandlestickUtils candles) {
		JSONObject jsonResult = new JSONObject();
		HashMap<String, String> mapResult = new HashMap<String, String>();
		try {
			mapResult.put("scriptname", scriptName);
			mapResult.put("type", "scriptengine");
			mapResult.put("date", date);
			jsEngine.eval(script);
			invocableEngine.invokeFunction("applyScript", candles, mapResult);
			System.out.println(mapResult.toString());
			for (String key : mapResult.keySet()) {
				jsonResult.put(key, mapResult.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult.toString();
	}

}
