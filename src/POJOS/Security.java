package POJOS;

public class Security extends Worker{
	private int securityId;

	
	public Security(int casinoId, String name, String surname, float salary, String addres, int securityId) {
		super(casinoId, name, surname, salary, addres);
		this.securityId = securityId;
	}

	public int getSecurityId() {
		return securityId;
	}

	public void setSecurityId(int securityId) {
		this.securityId = securityId;
	}

}
