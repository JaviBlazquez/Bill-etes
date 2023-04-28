
package POJOS;

import java.io.Serializable;

public class Casino implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -9197997725060790303L;
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
	
	@Override
	public String toString() {
		return "Casino [casinoId=" + casinoId + ", accountId=" + accountId + "]";
	}
}
