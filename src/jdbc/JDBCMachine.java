package jdbc;
import POJOS.Machine;

import java.sql.PreparedStatement;

import Interfaces.MachineManager;

public class JDBCMachine implements MachineManager{
	private JDBCManager manager;
	
	public JDBCMachine(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addMachine(Machine M) {
		try {
			String sql = "INSERT INTO machine (machine_id,name,money_won) VALUES(?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, M.getMachineId());
			prep.setString(2, M.getMachineName());
			prep.setFloat(3, M.getMoneyWon());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeMachine(Machine M) {
		try {
			String sql = "DELETE FROM machine"
					+ "WHERE machine.machine_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, M.getMachineId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMachine(Machine M) {
		try {
			String sql = "UPDATE machine SET name,money_won = {?,?}"
					+ "WHERE machine.machine_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, M.getMachineName());
			prep.setFloat(2, M.getMoneyWon());
			prep.setInt(3, M.getMachineId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getMachineFromId(Machine M) {
		try {
			String sql = "SELECT machine_id,name,money_won"
					+ "WHERE machine_id = {?}"
					+ "FROM machine";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, M.getMachineId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Machine getMachine() {
		
		return null;
	}

}
