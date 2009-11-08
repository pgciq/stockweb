package persistence.impl;

import java.util.Set;

import persistence.session.ScriptEngineSession;
import persistence.vo.Script;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ScriptEngineImpl {

	public Script get(Script script) {
		try {
			return new ScriptEngineSession().get(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Script getById(Script script) {
		try {
			return new ScriptEngineSession().getById(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Set list() {
		try {
			return new ScriptEngineSession().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void remove(Script script) {
		try {
			new ScriptEngineSession().delete(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Script script) {
		try {
			new ScriptEngineSession().add(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveSettingChart(Script script) {
		try {
			new ScriptEngineSession().saveSettingChart(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
