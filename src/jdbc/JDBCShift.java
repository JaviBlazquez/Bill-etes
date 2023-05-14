package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import POJOS.Shift;

public class JDBCShift {
private JDBCManager manager;
	
public JDBCShift(JDBCManager m) {
		this.manager = m;
	}


public void addShift(Shift s) {
	try {
		String sql = "INSERT INTO shift (table_id, croupier_id, fecha) VALUES(?,?,?)";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, s.getTableId());
		prep.setInt(2, s.getCroupierId());
		prep.setTimestamp(3, s.getTimeStamp());
		prep.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
}


public List<Shift> getListOfShifts() {
	List<Shift> shiftList= new LinkedList<Shift>();
	try {
		String sql = "SELECT * FROM shift";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		ResultSet rs= prep.executeQuery();
		while(rs.next()) {
			int tableId = rs.getInt("table_id");
			int croupierId = rs.getInt("croupier_id");
			Timestamp timeStamp = rs.getTimestamp("time_Stamp");
			Shift s= new Shift(tableId, croupierId, timeStamp);
			shiftList.add(s);
			}
		return shiftList;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}
	


public void updateShift(Shift s) {
	try {
		String sql = "UPDATE Shift SET tableId = ?, croupierId = ?, timeStamp = ?";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, s.getTableId());
		prep.setInt(2, s.getCroupierId());
		prep.setTimestamp(3, s.getTimeStamp());
		prep.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}


public void removeShift(Shift s) {
	try {
		String sql = "DELETE FROM shift"
				+ "WHERE shift.croupier_id = {?}";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setInt(1, s.getCroupierId());
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}

public Shift getShift() {
	
	return null;
}


}
