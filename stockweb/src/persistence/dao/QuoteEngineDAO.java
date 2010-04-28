package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import persistence.DBSession;
import persistence.Stock;
import persistence.vo.QuoteVO;
import persistence.vo.Script;


/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class QuoteEngineDAO { // implements PersistenceDAO{

	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stat = null;

	public List<QuoteVO> getQuote(String stockName, String dateI, String dateF) {
		List<QuoteVO> result = new ArrayList<QuoteVO>();
		conn = DBSession.getIstance().getConnection();
		PreparedStatement pstat = null;
		try {
			String sql = " select date, high, low, open, close, volume " +
						 " from quote " +
						 " where date > ? and date < ? and stock = ? " +
						 " order by date desc";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dateI);
			pstat.setString(2, dateF);
			pstat.setString(3, stockName);
			
			rs = pstat.executeQuery();
			while(rs.next()) {
				QuoteVO quote = new QuoteVO();
				quote.setStock(stockName);
				quote.setDate(rs.getString("date"));
				quote.setHigh(rs.getString("high"));
				quote.setLow(rs.getString("low"));
				quote.setOpen(rs.getString("open"));
				quote.setClose(rs.getString("close"));
				quote.setVolume(rs.getString("volume"));
				result.add(quote);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
