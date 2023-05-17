package POJOS;

import java.io.Serializable;

public class Machine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981898793485992283L;
	private int machine_id;
	private String name;
	private float money_won;
	
	
	public Machine(int _machineId, String _machineName, float _moneyWon) {
		this.setMachineId(_machineId);
		this.setMachineName(_machineName);
		this.setMoneyWon(_moneyWon);
	}
	
	public int getMachineId() {
		return machine_id;
	}
	public void setMachineId(int machineId) {
		this.machine_id = machineId;
	}
	public String getMachineName() {
		return name;
	}
	public void setMachineName(String machineName) {
		this.name = machineName;
	}
	public float getMoneyWon() {
		return money_won;
	}
	public void setMoneyWon(float moneyWon) {
		this.money_won = moneyWon;
	}

	@Override
	public String toString() {
		return "Machine [machine_id=" + machine_id + ", name=" + name + ", money_won=" + money_won + "]";
	}
	
	
}
