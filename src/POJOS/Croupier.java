package POJOS;

public class Croupier extends Worker{
	private int croupierId;

	

	public Croupier(int casinoId, String name, String surname, float salary, String addres, int croupierId) {
		super(casinoId, name, surname, salary, addres);
		this.croupierId = croupierId;
	}

	public int getCroupierId() {
		return croupierId;
	}

	public void setCroupierId(int croupierId) {
		this.croupierId = croupierId;
	}
	
}
