package Interfaces;

import java.io.File;

import POJOS.Casino;
import POJOS.Worker;


public interface XMLCasinoManager {
	
	public void casinoToXml(Casino c);
	public void workerToXml(Worker w);
	public Worker xmlToWorker(File xml);
	public Casino xmlToCasino(File xml);
}
