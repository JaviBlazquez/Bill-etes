package jdbc;
import Interfaces.BancaryAccountManager;
import java.sql.*;
import POJOS.BancaryAccount;

public class JDBCBancaryAccount implements BancaryAccountManager{
	private JDBCManager manager;
	
	public JDBCBancaryAccount(JDBCManager m) {
		this.manager = m;
	}
	
	public void addBancaryAccount(BancaryAccount BA) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE bancaryaccount SET money = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, BA.getMoney());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BancaryAccount getBancaryAccount() {
		// TODO Auto-generated method stub
		return null;
	}
}
