package jdbc;
import Interfaces.ClientManager;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import POJOS.BancaryAccount;
import POJOS.Client;
import POJOS.Machine;
import POJOS.Occupation;
import POJOS.Table;
import POJOS.Worker;
public class JDBCClient implements ClientManager {
	private JDBCManager manager;
	public JDBCClient(JDBCManager m) {
		this.manager = m;
	}
	public void addClient(Client C) {
		
		try {
			String sql = "INSERT INTO client (client_id,phone,money,name,surname,condition) VALUES(?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, C.getClientId());
			prep.setInt(2,C.getPhone());
			prep.setFloat(3,C.getMoney());
			prep.setString(4,C.getName());
			prep.setString(5,C.getSurname());
			prep.setBoolean(6,C.isCondition());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void removeClient(Client C) {
		try {
			String sql = "DELETE FROM client"
					+ "WHERE client.clientId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1,C.getClientId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateClient(Client C) {
		try {
			String sql = "UPDATE client SET phone,money,name,surname,condition = {?,?,?,?,?}"
					+ "WHERE client.clientId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, C.getPhone());
			prep.setFloat(2, C.getMoney());
			prep.setString(3, C.getName());
			prep.setString(4, C.getSurname());
			prep.setBoolean(5, C.isCondition());
			prep.setInt(6, C.getClientId());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateClient(Client C, int id) {
		try {
			String sql = "UPDATE client SET phone,money,name,surname,condition,client_id = {?,?,?,?,?,?}"
					+ "WHERE client.clientId = {?}";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, C.getPhone());
			prep.setFloat(2, C.getMoney());
			prep.setString(3, C.getName());
			prep.setString(4, C.getSurname());
			prep.setBoolean(5, C.isCondition());
			prep.setInt(6, C.getClientId());
			prep.setInt(7, id);
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<Client> getListofClient() {
		List<Client> clientList = new LinkedList<Client>();
		try {
			String sql = "SELECT * FROM client";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs= prep.executeQuery();
			while(rs.next()) {
				int clientId = rs.getInt("clientId");
				int phone=rs.getInt("phone");
				float money=rs.getFloat("money");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				boolean condition = rs.getBoolean("condition");
				Client C= new Client(clientId,phone,money,name,surname,condition);
				clientList.add(C);
			}
			return clientList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clientList;
	}
	public Client getClient() {
		
		return null;
	}
	public List<Client> getClientByQuery(String query){
		List<Client> clientList= new LinkedList<Client>();
		try {
			PreparedStatement prep = manager.getConnection().prepareStatement(query);
			ResultSet rs= prep.executeQuery();
			ResultSetMetaData rsmeta= rs.getMetaData();
			Integer clientId=null;
			Integer phone=null;	
			Float money=null;
			String name=null;
			String surname=null;
			Boolean condition=null;
			int totalColumnas= rsmeta.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=totalColumnas; i++) {
					switch(rsmeta.getColumnName(i)) {
						case "client_id":{
							clientId= rs.getInt(i);
							break;
						}
						case "phone":{
							phone= rs.getInt(i);
							break;
						}
						case "money":{
							money= rs.getFloat(i);
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
						case "condition":{
							condition= rs.getBoolean(i);
							break;
						}
						
						
					}
				}
				clientList.add(new Client(clientId, phone,money, name, surname,condition));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clientList;
	}
}
