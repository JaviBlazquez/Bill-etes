package jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import Interfaces.TableManager;
import POJOS.Table;

public class JDBCTable implements TableManager{
	private JDBCManager manager;
	
	public JDBCTable(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public Table getTable() {
		
		return null;
	}

	@Override
	public void addTable(Table T) {
		
		try {
			String sql = "INSERT INTO casinotable (table_id,game_name,money_won) VALUES(?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, T.getTableId());
			prep.setString(2, T.getGameName());
			prep.setFloat(3, T.getMoneyWon());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTable(Table T) {
		try {
			String sql = "UPDATE casinotable SET game_name = ?, money_won = ? WHERE casinotable.table_id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, T.getGameName());
			prep.setFloat(2, T.getMoneyWon());
			prep.setInt(3, T.getTableId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeTable(Table T) {
		try {
			String sql = "DELETE FROM casinotable"
					+ "WHERE casinotable.table_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, T.getTableId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Table> getListofTable() {
		List<Table> tableList = new LinkedList<Table>();
		try {
			String sql = "SELECT * FROM casinotable";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int tableId = rs.getInt("table_id");
				String gameName = rs.getString("game_name");
				float money_won = rs.getFloat("money_won");
				Table T= new Table(tableId,gameName,money_won);
				tableList.add(T);
			}
			return tableList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tableList;
	}
}
