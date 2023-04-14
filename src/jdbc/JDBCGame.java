package jdbc;
import Interfaces.BancaryAccountManager;
import Interfaces.GameManager;
import java.sql.*;
import java.util.List;

import POJOS.Game;

public class JDBCGame implements GameManager{
	private JDBCManager manager;

	@Override
	public void addGame(Game g) {

		try {
			String sql = "INSERT INTO game (clientId, machineId, timeStamp) VALUES(?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, g.getClientId());
			prep.setInt(2,g.getMachineId());
			prep.setInt(3, g.getTimeStamp());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Game> getListOfGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGame(Game g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeGame(Game g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}

}
