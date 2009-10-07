package persistence.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import persistence.DBSession;
import persistence.vo.Script;

public class ScriptEngineDAO {			//implements PersistenceDAO{

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stat = null;

	public Set<Script> getListObject() throws Exception {
		Set<Script> list = new HashSet();
		Script accountUser = null;
		conn = DBSession.getIstance().getConnection();
		String sql = "SELECT id, name, script, descr, param FROM scripts ORDER BY name";
		
		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while (rs.next()) 
			list.add(getObjectValue(rs));

		rs.close();
		stat.close();
		
		return list;
	}

	public void delete(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = "DELETE FROM scripts WHERE id = " + key;
		
		stat = conn.createStatement();
		stat.execute(sql);

		stat.close();
	}

	
	public Script getObjectByKey(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = "select id, name, script, descr, param from scripts where id = " + key;

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		rs.next();
		
		return getObjectValue(rs);
	}	
	
	public Script getObjectByName(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = "select id, name, script, descr, param from scripts where name like '" + key + "%'";

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		rs.next();
		
		return getObjectValue(rs);
	}	
	
	private Script getObjectValue(ResultSet rs) throws Exception{
		
		Script script = new Script();
		script.setId(rs.getLong("id"));
		script.setName(rs.getString("name"));
		script.setScript(rs.getString("script"));
		script.setDescr(rs.getString("descr"));
		script.setParam(rs.getString("param"));
		return script;
	}
	
	public void persist(Script script) throws Exception {

		conn = DBSession.getIstance().getConnection();
//		String sql = "insert into t02_accountuser (firstname, lastname, date, email, icon, login, password, reajust, notes, sim, telcontact) values (?,?,?,?,?,?,?,?,?,?,?)";
		String sql = "insert into scripts (name, script, descr, param) values (?,?,?,?)";

		if (exist(script)) {
			System.out.println(">>>>>>> LOG: script.getId() = " + script.getId());

			sql = "update scripts set " + 
				  "		name = ?,    " +
				  " 	script = ?,  " +
				  " 	descr = ?,   " +
				  " 	param = ?    " +
				  " where " +
				  "		id = " + String.valueOf(script.getId());		
		}
		
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, script.getName());
		stat.setString(2, script.getScript());
		stat.setString(3, script.getDescr());
		stat.setString(4, script.getParam());
		stat.execute();

	}

	public boolean exist(Script script) {
		boolean exist = false;
		conn = DBSession.getIstance().getConnection();
		try {
			String sql = "select id from scripts where name = '" + script.getName() + "'";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				script.setId(rs.getLong("id"));
				exist = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exist;
	}


}
