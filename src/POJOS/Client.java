package POJOS;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Client")
@XmlType(propOrder = {"phone", "money", "condition"})
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9034033102536229349L;
	
	@XmlTransient
	private int clientId;
	@XmlElement
	private int phone;
	@XmlElement
	private float money;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String surname;
	@XmlElement
	private boolean condition;
	public Client(int clientId, int phone, float money, String name, String surname, boolean condition) {
		
		this.clientId = clientId;
		this.phone = phone;
		this.money = money;
		this.name = name;
		this.surname = surname;
		this.condition = condition;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
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
	public boolean isCondition() {
		return condition;
	}
	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", phone=" + phone + ", money=" + money + ", name=" + name
				+ ", surname=" + surname + ", condition=" + condition + "]";
	}
		
	
	
}
