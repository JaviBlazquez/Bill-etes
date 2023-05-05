
package POJOS;

import java.io.Serializable;
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
@XmlType(propOrder = {"worker"})
public class Casino implements Serializable{
	
	

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9197997725060790303L;
	@XmlTransient
	private int casinoId;
	@XmlTransient
	private int accountId;
	@XmlElement(name = "worker")
	@XmlElementWrapper(name = "Workers")
	private List<Worker> workers;
	
	public Casino (int casinoId, int accountId, List<Worker> l1) {
		this.casinoId = casinoId;
		this.workers = l1;
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
		return "Casino [casinoId=" + casinoId + ", accountId=" + accountId + ", workers=" + workers + "]";
	}
	
	
}
