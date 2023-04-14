package jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import Interfaces.CroupierManager;
import Interfaces.WorkerManager;
import POJOS.Worker;
import POJOS.Croupier;

public class JDBCCroupier implements CroupierManager{

	private JDBCManager manager;
	
	public JDBCCroupier(JDBCManager m)
	{
		this.manager = m;
	}
	@Override
	public void addWorker(Worker w) {
		Croupier c=(Croupier) w;
		try{
			String sql = "INSERT INTO croupier (casinoId, name, surname, salary, addres, croupierId) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, c.getCasinoId());
			prep.setString(2, c.getName());
			prep.setString(3, c.getSurname());
			prep.setFloat(4, c.getSalary());
			prep.setString(5, c.getAddres());
			prep.setInt(6, c.getCroupierId());
			
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Worker> getListOfWorkers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorker(Worker w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWorker(Worker w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignTable(int croupierId, int tableId) {
		try{
			String sql = "INSERT INTO shift (croupierId,tableId) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, croupierId);
			prep.setInt(2, tableId);		
			
			prep.executeUpdate();			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
