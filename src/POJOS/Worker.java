package POJOS;

public class Worker {
	private int casinoId;
	private String name;
	private String surname;
	private float salary;
	private String addres;
	public Worker(int casinoId, String name, String surname, float salary, String addres) {
		super();
		this.casinoId = casinoId;
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.addres = addres;
	}
	public int getCasinoId() {
		return casinoId;
	}
	public void setCasinoId(int casinoId) {
		this.casinoId = casinoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
}
