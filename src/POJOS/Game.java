package POJOS;

import java.io.Serializable;
import java.sql.Timestamp;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3370777575023982414L;
	private int client_id;
	private int machine_id;
	private Timestamp timeStamp;
	
	
	
	public Game(int clientId, int machineId, Timestamp timeStamp) {
		super();
		this.client_id = clientId;
		this.machine_id = machineId;
		this.timeStamp = timeStamp;
	}
	
	public int getClientId() {
		return client_id;
	}
	public void setClientId(int clientId) {
		this.client_id = clientId;
	}
	public int getMachineId() {
		return machine_id;
	}
	public void setMachineId(int machineId) {
		this.machine_id = machineId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Game [client_id=" + client_id + ", machine_id=" + machine_id + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
