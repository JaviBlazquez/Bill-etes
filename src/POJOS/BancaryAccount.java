package POJOS;
import java.io.Serializable;

public class BancaryAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9025335208651977334L;
	private float money;
	private int account_id;
	
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
		return "BancaryAccount [money=" + money + ", account_id=" + account_id + "]";
	}

	public int getAccountId() {
		return account_id;
	}
	public void setAccountId(int accountId) {
		this.account_id = accountId;
	}
	
	
}
