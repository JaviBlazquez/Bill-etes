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
			String sql = "INSERT INTO match (clientId, tableId, timeStamp) VALUES(?,?,?)";
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
			String sql = "SELECT * FROM Match";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int tableId = rs.getInt("tableID");
				int clientId = rs.getInt("clientID");
				int timeStamp = rs.getInt("timeStamp");
				
			}
			return matchList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return matchList;
	}
	
	

	public void removeMatch(Match m) {
		try {
			String sql = "DELETE FROM match"
					+ "WHERE table.tableId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, m.getTableId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	

	public void updateMatch(Match m) {
		try {
			String sql = "UPDATE game SET clientId, tableId, timeStamp = {?,?,?}"
					+ "WHERE game.gameId = {?}";
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


