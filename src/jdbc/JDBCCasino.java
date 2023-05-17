package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import Interfaces.CasinoManager;
import POJOS.Casino;

public class JDBCCasino implements CasinoManager{
	private JDBCManager manager;
	public JDBCCasino(JDBCManager m) {
		this.manager = m;
	}
	public void addCasino(Casino C) {
		
		try {
			String sql = "INSERT INTO casino (casino_id,account_id) VALUES(?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, C.getCasinoId());
			prep.setInt(2,C.getCasinoId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void removeClient(Casino C) {
		try {
			String sql = "DELETE FROM casino WHERE casino.casino_id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,C.getCasinoId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCasino(Casino C) {
		try {
			String sql = "UPDATE casino SET account_id = ? WHERE client.client_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, C.getAccountId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<Casino> getListofCasino() {
		List<Casino> casinoList = new LinkedList<Casino>();
		try {
			String sql = "SELECT * FROM casino";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int casinoId = rs.getInt("casino_id");
				int accountId=rs.getInt("account_id");
				Casino C= new Casino(casinoId,accountId);
				casinoList.add(C);
			}
			return casinoList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return casinoList;
	}
	
	public Casino getCasino() {
		try {
			String sql = "SELECT * FROM casino";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int casinoId = rs.getInt("casino_id");
				int accountId=rs.getInt("account_id");
				Casino C= new Casino(casinoId,accountId);
				return C;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
}
	}
