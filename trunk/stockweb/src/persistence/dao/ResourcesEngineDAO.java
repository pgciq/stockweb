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

public class ResourcesEngineDAO { // implements PersistenceDAO{

	private Connection conn = null;

	public String getObjectByName(String key) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> key =" + key);
		conn = DBSession.getIstance().getConnection();
		String sql = "select content from resources where name = '" + key + "'";

		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		rs.next();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + rs.getString("content"));
		return rs.getString("content");
	}


}
