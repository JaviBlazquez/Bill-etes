package POJOS;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shift implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1385339185760201333L;
	private int tableId;
	private int croupierId;
	private Timestamp timeStamp;
	
	
	
	public Shift(int tableId, int croupierId, Timestamp timeStamp) {
		super();
		this.tableId = tableId;
		this.croupierId = croupierId;
		this.timeStamp = timeStamp;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getCroupierId() {
		return croupierId;
	}
	public void setCroupierId(int croupierId) {
		this.croupierId = croupierId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Shift [tableId=" + tableId + ", croupierId=" + croupierId + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
