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

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class ChartSettingEngineDAO { // implements PersistenceDAO{

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stat = null;

	public void delete(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = "DELETE FROM chartsetting WHERE id = " + key;

		stat = conn.createStatement();
		stat.execute(sql);

		stat.close();
	}

	public boolean exist(String nameChart, String idScript, String rsIdChart) {
		boolean exist = false;
		conn = DBSession.getIstance().getConnection();
		try {
			String sql = " SELECT id " +
						 " FROM chartsetting " +
						 " WHERE " +
						 "		idscript = " + idScript +
						 "  and name = " + nameChart;

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				rsIdChart = rs.getString("id");
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

	public Set<Script> getListObject() throws Exception {
		Set<Script> list = new HashSet();
		conn = DBSession.getIstance().getConnection();
		String sql = " SELECT s.id, s.name, s.script, s.descr, s.param, s.settingchart " +
					 " FROM chartsetting ch, script s" +
					 " WHERE " +
					 "		s.id = ch.idscript " +
					 " ORDER BY s.name, ch.id";

		stat = conn.createStatement();
		rs = stat.executeQuery(sql);
		while (rs.next()) {
			list.add(getObjectValue(rs));
		}

		rs.close();
		stat.close();

		return list;
	}

	public Script getObjectByKey(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = " SELECT s.id, s.name, s.script, s.descr, s.param, s.settingchart " +
					 " FROM chartsetting ch, script s " +
					 " WHERE " +
					 "		s.id = ch.idscript " +
					 "		ch.id = " + key;

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		rs.next();

		return getObjectValue(rs);
	}

	public Script getObjectByName(String key) throws Exception {
		conn = DBSession.getIstance().getConnection();
		String sql = " SELECT s.id, s.name, s.script, s.descr, s.param, s.settingchart " +
					 " FROM chartsetting ch, script s " +
					 " WHERE " +
					 "		s.id = ch.idscript " +
					 "		ch.name = '" + key + "'";

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		rs.next();

		return getObjectValue(rs);
	}

	private Script getObjectValue(ResultSet rs) throws Exception {

		Script script = new Script();
		script.setId(rs.getLong("id"));
		script.setName(rs.getString("name"));
		script.setScript(rs.getString("script"));
		script.setDescr(rs.getString("descr"));
		script.setParam(rs.getString("param"));
		script.setSettingchart(rs.getString("settingchart"));
		return script;
	}

	public void persist(String idscript, String name) throws Exception {
		String idChartSetting = "";
		conn = DBSession.getIstance().getConnection();
		String sql = "insert into chartsetting (name, idscript) values (?,?)";

		if (exist(name, idscript, idChartSetting)) {
			System.out.println(">>>>>>> LOG: idScript = " + idscript);

			sql = " update chartsetting set 	 "  + 
				  "		name = ?,    	 "  + 
				  " 	idscript = ?  	 "  + 
				  "  where " + 
				  "		id = " + idChartSetting;
		}

		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, name);
		stat.setString(2, idscript);
		stat.execute();

	}

}
