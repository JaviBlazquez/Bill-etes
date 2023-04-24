package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import Interfaces.CasinoManager;
import POJOS.Casino;
import POJOS.Client;
/*private int casinoId;
	private int accountId;*/
public class JDBCCasino implements CasinoManager{
	private JDBCManager manager;
	public JDBCasino(JDBCManager m) {
		this.manager = m;
	}
	public void addCasino(Casino C) {
		
		try {
			String sql = "INSERT INTO casino (casinoID,accountID) VALUES(?,?)";
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
			String sql = "DELETE FROM casino"
					+ "WHERE casino.casinoID = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,C.getCasinoId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCasino(Casino C) {
		try {
			String sql = "UPDATE casino SET accountID = {?}"
					+ "WHERE client.clientId = {?}";
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
				int casinoId = rs.getInt("casinoID");
				int accountId=rs.getInt("accountID");
				Casino C= new Casino(casinoId,accountId);
				casinoList.add(C);
			}
			return casinoList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return casinoList;
	}
	public Client getClient() {
		
		return null;
}
