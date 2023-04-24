package POJOS;

public class Match {
	
	private int clientId;
	private int tableId;
	private int timeStamp;
	
	public Match(int clientId, int tableId, int timeStamp) {
		super();
		this.clientId = clientId;
		this.tableId = tableId;
		this.timeStamp = timeStamp;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public int getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Match [clientId=" + clientId + ", tableId=" + tableId + ", timeStamp=" + timeStamp + "]";
	}
	
	
	

}
