package POJOS;

public class Game {

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
