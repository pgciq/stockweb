package persistence.session;

import java.util.Set;

import persistence.dao.ChartSettingEngineDAO;
import persistence.dao.ScriptEngineDAO;
import persistence.vo.Script;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ChartSettingEngineSession {

		private ChartSettingEngineDAO chartSettingEngineDAO = new ChartSettingEngineDAO();
		
        public void add(String idscript, String name) throws Exception{
        	chartSettingEngineDAO.persist(idscript, name);
        }

        public Set<Script> list() throws Exception{
        	return chartSettingEngineDAO.getListObject();
        }

        public Script get(String key) throws Exception{
        	return chartSettingEngineDAO.getObjectByName(key);
        }

        public Script getById(Script script) throws Exception{
        	return chartSettingEngineDAO.getObjectByKey(String.valueOf(script.getId()));
        }

        public void delete(Script script) throws Exception{
        	chartSettingEngineDAO.delete(String.valueOf(script.getId()));
        }
}

