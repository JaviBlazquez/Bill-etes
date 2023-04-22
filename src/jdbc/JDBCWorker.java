package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import Interfaces.WorkerManager;
import POJOS.Occupation;
import POJOS.Worker;

public class JDBCWorker implements WorkerManager{
	private JDBCManager manager;
	
	public JDBCWorker(JDBCManager m) {
		this.manager = m;
	}
	@Override
	public void addWorker(Worker w) {
		try {
			String sql = "INSERT INTO worker (workerId, casinoId, name, surname, salary, addres, occupation) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, w.getWorkerId());
			prep.setInt(2, w.getCasinoId());
			prep.setString(3, w.getName());
			prep.setString(4, w.getSurname());
			prep.setFloat(5, w.getSalary());
			prep.setString(6, w.getAddres());
			prep.setString(7, w.getOccupationString());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Worker> getListOfWorkers() {
		List<Worker> workerList= new LinkedList<Worker>();
		try {
			String sql = "SELECT * FROM worker";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int workerId = rs.getInt("worker_id");
				int casinoId = rs.getInt("casino_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				float salary = rs.getFloat("salary");
				String addres = rs.getString("addres");
				Occupation occupation;
				switch(rs.getString("occupation")) {
					case "Security":{
						occupation= Occupation.SECURITY;
					}
					case "Croupier":{
						occupation= Occupation.CROUPIER;
					}
					default:{
						occupation= Occupation.ADMINISTRATION;
					}
				}
				Worker w= new Worker(workerId, casinoId, name, surname, salary, addres, occupation);
				workerList.add(w);
			}
			return workerList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateWorker(Worker w) {
		try {
			String sql = "UPDATE worker SET name, surname, salary, addres, occupation = {?,?,?,?,?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getSurname());
			prep.setFloat(3, w.getSalary());
			prep.setString(4, w.getAddres());
			prep.setString(5, w.getOccupationString());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeWorker(Worker w) {
		try {
			String sql = "DELETE FROM worker"
					+ "WHERE casinotable.worker_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, w.getWorkerId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Worker getWorker() {
		
		return null;
	}

}
