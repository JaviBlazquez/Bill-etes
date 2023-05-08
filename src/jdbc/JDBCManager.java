package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
	
	private Connection c = null;

	public JDBCManager() {
		
		try 
		{			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/casino.db");
			System.out.println("Database connection opened.");
			this.createTables();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			System.out.print("Libraries not loaded");
		}
	}
	
	private void createTables() {
		// Create Tables
		try {
		Statement stmt = c.createStatement();
		String sql = "CREATE TABLE shift ("
				+ "shift_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "croupier_id INTEGER,"
				+ "table_id INTEGER,"
				+ "fecha DATETIME,"
				+ "FOREIGN KEY(croupier_id) REFERENCES worker (worker_id),"
				+ "FOREIGN KEY(table_id) REFERENCES casinotable(table_id)"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE matchs ("
				+ "  match_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "  client_id INTEGER, "
				+ "  table_id INTEGER,"
				+ "  fecha DATETIME,"
				+ "  money_won INTEGER,"
				+ "  FOREIGN KEY(client_id) REFERENCES client(client_id),"
				+ "  FOREIGN KEY(table_id) REFERENCES casinotable(table_id)"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE game ("
				+ "  game_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "  client_id INTEGER, "
				+ "  machine_id INTEGER,"
				+ "  fecha DATETIME,"
				+ "  money_won INTEGER,"
				+ "  FOREIGN KEY(client_id) REFERENCES client(client_id),"
				+ "  FOREIGN KEY(machine_id) REFERENCES machine(machine_id)"
				+ ");";
				stmt.executeUpdate(sql);
		sql = "CREATE TABLE bancaryaccount("
				+ "  account_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "  money INTEGER"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE casinotable("
				+ "  table_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "  game_name TEXT,"
				+ "  money_won INTEGER"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE machine("
				+ "  machine_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "  name TEXT,"
				+ "  money_won INTEGER"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE client ("
				+ "  phone INTEGER NOT NULL UNIQUE,"
				+ "  name TEXT NOT NULL,"
				+ "  surname TEXT NOT NULL,"
				+ "  condition INTEGER NOT NULL,"
				+ "  client_id INTEGER NOT NULL UNIQUE"
				+ "  PRIMARY KEY(client_id)"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE casino ("
				+ "  casino_id INTEGER,"
				+ "  account_id INTEGER,"
				+ "  FOREIGN KEY(account_id) REFERENCES bancaryaccount(account_id),"
				+ "  PRIMARY KEY(casino_id)"
				+ ");";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE worker("
				+ "  worker_id INTEGER AUTOINCREMENT,"
				+ "  name TEXT,"
				+ "  surname TEXT,"
				+ "  salary REAL,"
				+ "  address TEXT,"
				+ "  occupation TEXT,"
				+ "  casino_id INTEGER,"
				+ "  PRIMARY KEY (worker_id),"
				+ "  FOREIGN KEY (casino_id) REFERENCES casino (casino_id)"
				+ ");";
		stmt.executeUpdate(sql);


		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		
		return c;
	}
	
	public void disconnect()
	{		
		try {
			c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();			
		}
	}
	
			
}
