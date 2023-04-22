package POJOS;

public class Table {
 
	private int tableId;
	private String gameName;
	private float moneyWon;
	
	public Table(int _id,String _gameName,float _moneyWon) {
		this.setGameName(_gameName);
		this.setMoneyWon(_moneyWon);
		this.setTableId(_id);
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public float getMoneyWon() {
		return moneyWon;
	}
	public void setMoneyWon(float moneyWon) {
		this.moneyWon = moneyWon;
	}
	@Override
	public String toString() {
		return "Table [tableId=" + tableId + ", gameName=" + gameName + ", moneyWon=" + moneyWon + "]";
	}
	
	
}
