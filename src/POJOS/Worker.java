package POJOS;

import java.io.Serializable;

public class Worker implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 91606388313702497L;
	private int workerId;
	private int casinoId;
	private String name;
	private String surname;
	private float salary;
	private String addres;
	private Occupation occupation;
	public Worker(int workerId, int casinoId, String name, String surname, float salary, String addres, Occupation occupation) {
		super();
		this.workerId = workerId;
		this.casinoId = casinoId;
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.addres = addres;
		this.occupation=occupation;
	}
	public int getWorkerId() {
		return workerId;
	}
	public void setWorkerId(int workerId) {
		this.workerId=workerId;
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
	public Occupation getOccupation() {
		return this.occupation;
	}
	public String getOccupationString() {
		switch(this.occupation) {
			case SECURITY:{
				return "Security";
			}
			case CROUPIER:{
				return "Croupier";
			}
			default:{
				return "Administration";
			}
		}
	}
	public void setOccupation(Occupation occupation) {
		this.occupation=occupation;
	}
	@Override
	public String toString() {
		return "Worker [workerId=" + workerId + ", casinoId=" + casinoId + ", name=" + name + ", surname=" + surname
				+ ", salary=" + salary + ", addres=" + addres + ", occupation=" + occupation + "]";
	}
	
}
