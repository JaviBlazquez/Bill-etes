package POJOS;
import java.io.Serializable;

public class BancaryAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025335208651977334L;
	private float money;
	private int accountId;
	
	public BancaryAccount(int _id,float _money){
		this.setAccountId(_id);
		this.setMoney(_money);
	}
	
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "BancaryAccount [money=" + money + ", accountId=" + accountId + "]";
	}

	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
}
