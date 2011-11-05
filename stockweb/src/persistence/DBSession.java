package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class DBSession {

	private String JDBC_driver = "com.mysql.jdbc.Driver";
	private String ODBC_driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	private Connection conn = null;
	public static DBSession istance = null;

	public static DBSession getIstance() {
		if (istance == null) {
			istance = new DBSession();
		}

		return istance;

	}

	public DBSession() {
//	    connect("root", "root");
		connect("spymenow", "concha");
	}

	/*
	 * CONNECT mysql
	 */
	synchronized public Connection connect(String login, String pws) {
//		String dbURL = "jdbc:mysql://localhost:3306/stockweb?useServerPrepStmts=true";
		String dbURL = "jdbc:mysql://localhost:3307/spymenow?useServerPrepStmts=true";
		try {
			Class.forName(JDBC_driver);
			conn = DriverManager.getConnection(dbURL, login, pws);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * DISCONNECT mysql
	 */
	synchronized public void disconnect(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		return conn;
	}

}
