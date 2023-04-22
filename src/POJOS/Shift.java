package POJOS;

public class Shift {

	private int tableId;
	private int croupierId;
	private int timeStamp;
	
	
	
	public Shift(int tableId, int croupierId, int timeStamp) {
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
	public int getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Shift [tableId=" + tableId + ", croupierId=" + croupierId + ", timeStamp=" + timeStamp + "]";
	}
	
	
}
