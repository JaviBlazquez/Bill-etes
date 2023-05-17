
package POJOS;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Casino")
@XmlType(propOrder = {"workers"})
public class Casino implements Serializable{
	
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9197997725060790303L;
	@XmlTransient
	private int casino_id;
	@XmlTransient
	private int account_id;
	@XmlElement(name = "worker")
	@XmlElementWrapper(name = "Workers")
	private List<Worker> workers;
	
	public Casino() {
		super();
		workers = new LinkedList<>();
	}
	
	public Casino (int casinoId, int accountId) {
		this.casino_id = casinoId;
		this.account_id = accountId;
		workers = new LinkedList<>();
	}

	public int getCasinoId() {
		return casino_id;
	}

	public void setCasinoId(int casinoId) {
		this.casino_id = casinoId;
	}

	public int getAccountId() {
		return account_id;
	}

	public void setAccountId(int accountId) {
		this.account_id = accountId;
	}
	
    public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}


	@Override
	public String toString() {
		return "Casino [casino_id=" + casino_id + ", account_id=" + account_id + ", workers=" + workers + "]";
	}
	
	
}
