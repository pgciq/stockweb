package persistence.impl;

import java.util.Set;

import persistence.session.ChartSettingEngineSession;
import persistence.vo.Script;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ChartSettingEngineImpl {

	public Script get(String key) {
		try {
			return new ChartSettingEngineSession().get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Script getById(Script script) {
		try {
			return new ChartSettingEngineSession().getById(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Set list() {
		try {
			return new ChartSettingEngineSession().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void remove(Script script) {
		try {
			new ChartSettingEngineSession().delete(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(String idscript, String name) {
		try {
			new ChartSettingEngineSession().add(idscript, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public void saveSettingChart(Script script) {
		try {
			new ChartSettingEngineSession().saveSettingChart(script);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/	
}
