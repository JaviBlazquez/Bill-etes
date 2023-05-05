package POJOS;

import java.io.Serializable;
import java.sql.Timestamp;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3370777575023982414L;
	private int clientId;
	private int machineId;
	private Timestamp timeStamp;
	
	
	
	public Game(int clientId, int machineId, Timestamp timeStamp) {
		super();
		this.clientId = clientId;
		this.machineId = machineId;
		this.timeStamp = timeStamp;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Game [clientId=" + clientId + ", machineId=" + machineId + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
