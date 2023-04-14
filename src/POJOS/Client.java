package POJOS;

public class Client {
	private int clientId;
	private int phone;
	private float money;
	private String name;
	private String surname;
	private condition condition;
	public Client(int clientId, int phone, float money, String name, String surname, POJOS.condition condition) {
		
		this.clientId = clientId;
		this.phone = phone;
		this.money = money;
		this.name = name;
		this.surname = surname;
		this.condition = condition;
	}
	
	
	
}
