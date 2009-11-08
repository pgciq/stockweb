package persistence.session;

import java.util.Set;

import persistence.dao.ScriptEngineDAO;
import persistence.vo.Script;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ScriptEngineSession {
		private ScriptEngineDAO scriptEngineDAO = new ScriptEngineDAO();
		
        public void saveSettingChart(Script script) throws Exception{
        	scriptEngineDAO.saveSettingChart(script);
        }

        public void add(Script script) throws Exception{
        	scriptEngineDAO.persist(script);
        }

        public Set<Script> list() throws Exception{
        	return scriptEngineDAO.getListObject();
        }

        public Script get(Script script) throws Exception{
        	return scriptEngineDAO.getObjectByName(script.getName());
        }

        public Script getById(Script script) throws Exception{
        	return scriptEngineDAO.getObjectByKey(String.valueOf(script.getId()));
        }

        public void delete(Script script) throws Exception{
        	scriptEngineDAO.delete(String.valueOf(script.getId()));
        }
}

