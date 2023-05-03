package jdbc;
import Interfaces.BancaryAccountManager;
import java.sql.*;
import POJOS.BancaryAccount;
import POJOS.Machine;

public class JDBCBancaryAccount implements BancaryAccountManager{
	private JDBCManager manager;
	
	public JDBCBancaryAccount(JDBCManager m) {
		this.manager = m;
	}
	
	public void addBancaryAccount(BancaryAccount BA) {
		
		try {
			String sql = "INSERT INTO bancaryaccount (account_id,money) VALUES(?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, BA.getAccountId());
			prep.setFloat(2, BA.getMoney());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBancaryAccount(BancaryAccount BA) {
		
		try {
			String sql = "UPDATE bancaryaccount SET money = {?}"
					+ "WHERE bancaryaccount.account_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, BA.getMoney());
			prep.setInt(2, BA.getAccountId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BancaryAccount getBancaryAccount() {
		try {
			String sql = "Select * FROM bancaryaccount";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int bancaryAccountId = rs.getInt("account_id");
				float money_won = rs.getFloat("money");
				BancaryAccount BA = new BancaryAccount(bancaryAccountId,money_won);
				return BA;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
