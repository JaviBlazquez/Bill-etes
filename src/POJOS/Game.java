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
	private int timeStamp;
	
	
	
	public Game(int clientId, int machineId, int timeStamp) {
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
	public int getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Game [clientId=" + clientId + ", machineId=" + machineId + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
