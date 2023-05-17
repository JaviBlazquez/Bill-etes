package POJOS;

import java.io.Serializable;
import java.sql.Timestamp;

public class Match implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 176087543364843395L;
	private int client_id;
	private int table_id;
	private Timestamp fecha;
	
	public Match(int clientId, int tableId, Timestamp timeStamp) {
		super();
		this.client_id = clientId;
		this.table_id = tableId;
		this.fecha = timeStamp;
	}

	public int getClientId() {
		return client_id;
	}

	public void setClientId(int clientId) {
		this.client_id = clientId;
	}

	public int getTableId() {
		return table_id;
	}

	public void setTableId(int tableId) {
		this.table_id = tableId;
	}

	public Timestamp getTimeStamp() {
		return fecha;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.fecha = timeStamp;
	}

	@Override
	public String toString() {
		return "Match [clientId=" + client_id + ", tableId=" + table_id + ", timeStamp=" + fecha + "]";
	}
	
	
	

}
