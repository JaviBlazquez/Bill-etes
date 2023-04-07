package POJOS;

public class Administration extends Worker{
	private int adminId;

	public Administration(int casinoId, String name, String surname, float salary, String addres, int adminId) {
		super(casinoId, name, surname, salary, addres);
		this.setAdminId(adminId);
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	
}
