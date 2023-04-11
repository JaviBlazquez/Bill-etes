package POJOS;

public class Machine {

	private int machineId;
	private String machineName;
	private float moneyWon;
	
	
	public Machine(int _machineId, String _machineName, float _moneyWon) {
		this.setMachineId(_machineId);
		this.setMachineName(_machineName);
		this.setMoneyWon(_moneyWon);
	}
	
	public int getMachineId() {
		return machineId;
	}
	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public float getMoneyWon() {
		return moneyWon;
	}
	public void setMoneyWon(float moneyWon) {
		this.moneyWon = moneyWon;
	}
	
	
}
