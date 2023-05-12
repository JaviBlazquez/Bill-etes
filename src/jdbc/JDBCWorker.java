package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
				String addres = rs.getString("address");
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
			String sql = "UPDATE worker SET name, surname, salary, addres, occupation = {?,?,?,?,?}"
					+ "WHERE worker.workerId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getSurname());
			prep.setFloat(3, w.getSalary());
			prep.setString(4, w.getAddres());
			prep.setString(5, w.getOccupationString());
			prep.setInt(6, w.getWorkerId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateWorker(Worker w, int id) {
		try {
			String sql = "UPDATE worker SET name, surname, salary, addres, occupation = {?,?,?,?,?,?}"
					+ "WHERE worker.workerId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getSurname());
			prep.setFloat(3, w.getSalary());
			prep.setString(4, w.getAddres());
			prep.setString(5, w.getOccupationString());
			prep.setInt(6, w.getWorkerId());
			prep.setInt(7, id);
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeWorker(Worker w) {
		try {
			String sql = "DELETE FROM worker"
					+ "WHERE worker.worker_id = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, w.getWorkerId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public List<Worker> getWorkerByQuery(String query){
		List<Worker> workerList= new LinkedList<Worker>();
		try {
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			ResultSet rs= prep.executeQuery();
			ResultSetMetaData rsmeta= rs.getMetaData();
			Integer workerId=null;
			Integer casinoId=null;
			String name=null;
			String surname=null;
			Float salary=null;
			String addres=null;
			Occupation occupation=null;
			int totalColumnas= rsmeta.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=totalColumnas; i++) {
					switch(rsmeta.getColumnName(i)) {
						case "worker_id":{
							workerId= rs.getInt(i);
							break;
						}
						case "casino_id":{
							casinoId= rs.getInt(i);
							break;
						}
						case "name":{
							name= rs.getString(i);
							break;
						}
						case "surname":{
							surname= rs.getString(i);
							break;
						}
						case "salary":{
							salary= rs.getFloat(i);
							break;
						}
						case "addres":{
							addres= rs.getString(i);
							break;
						}
						case "occupation":{
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
						}
					}
				}
				workerList.add(new Worker(workerId, casinoId, name, surname, salary, addres, occupation));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return workerList;
	}
}
