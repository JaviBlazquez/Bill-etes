
package POJOS;

public class Casino {
	
	private int casinoId;
	private int accountId;
	
	public Casino (int casinoId, int accountId) {
		this.casinoId = casinoId;
		this.accountId = accountId;
	}

	public int getCasinoId() {
		return casinoId;
	}

	public void setCasinoId(int casinoId) {
		this.casinoId = casinoId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
}
