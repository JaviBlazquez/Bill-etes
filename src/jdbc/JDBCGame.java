 package jdbc;
import Interfaces.GameManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import POJOS.Game;

public class JDBCGame implements GameManager{
	private JDBCManager manager;
	public JDBCGame(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addGame(Game g) {

		try {
			String sql = "INSERT INTO game (clientId, machineId, timeStamp) VALUES(?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, g.getClientId());
			prep.setInt(2,g.getMachineId());
			prep.setTimestamp(3, g.getTimeStamp());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Game> getListOfGames() {
		List<Game> gameList = new LinkedList<Game>();
		try {
			String sql = "SELECT * FROM Game";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int gameId = rs.getInt("game_id");
				int clientId = rs.getInt("client_id");
				Timestamp timeStamp = rs.getTimestamp("fecha");
				gameList.add(new Game(gameId,clientId,timeStamp));
			}
			return gameList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gameList;
	}
	
	
	@Override
	public void removeGame(Game g) {
		try {
			String sql = "DELETE FROM game"
					+ "WHERE game.gameId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, g.getMachineId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void updateGame(Game g) {
		try {
			String sql = "UPDATE game SET clientId, machineId, timeStamp = {?,?,?}"
					+ "WHERE game.gameId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, g.getClientId());
			prep.setInt(2, g.getMachineId());
			prep.setTimestamp(3, g.getTimeStamp());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
