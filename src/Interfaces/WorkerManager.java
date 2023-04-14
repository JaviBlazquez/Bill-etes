package Interfaces;

import java.util.List;
import POJOS.Worker;

public interface WorkerManager {
	
	public void addWorker(Worker w);
	public List<Worker> getListOfWorkers();
	public void updateWorker(Worker w);
	public void removeWorker(Worker w);
}
