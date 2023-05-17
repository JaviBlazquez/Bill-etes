package jdbc;
import Interfaces.GameManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import POJOS.Match;

public class JDBCMatch {

	private JDBCManager manager;
	public JDBCMatch(JDBCManager m) {
		this.manager = m;
	}


	public void addMatch(Match m) {

		try {
			String sql = "INSERT INTO matchs (client_id, table_id, fecha) VALUES(?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getClientId());
			prep.setInt(2,m.getTableId());
			prep.setTimestamp(3, m.getTimeStamp());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Match> getListOfMatches() {
		List<Match> matchList = new LinkedList<Match>();
		try {
			String sql = "SELECT * FROM matchs";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int tableId = rs.getInt("table_id");
				int clientId = rs.getInt("client_id");
				Timestamp timeStamp = rs.getTimestamp("fecha");
				matchList.add(new Match(clientId,tableId,timeStamp));
			}
			return matchList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return matchList;
	}
	
	

	public void removeMatch(Match m) {
		try {
			String sql = "DELETE FROM matchs"
					+ "WHERE table.table_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getTableId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	

	public void updateMatch(Match m) {
		try {
			String sql = "UPDATE matchs SET client_id = ?, table_id = ?, fecha = ? WHERE matchs.match_id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getClientId());
			prep.setInt(2, m.getTableId());
			prep.setTimestamp(3, m.getTimeStamp());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public Match getMatch() {
		// TODO Auto-generated method stub
		return null;
	}

}


