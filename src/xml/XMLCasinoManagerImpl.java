package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Interfaces.XMLCasinoManager;
import POJOS.Casino;
import POJOS.Worker;
import jdbc.JDBCCasino;
import jdbc.JDBCManager;
import jdbc.JDBCWorker;


public class XMLCasinoManagerImpl implements XMLCasinoManager{
	JDBCManager manager;

	@Override
	public void casinoToXml(Casino c) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Casino.class);
			Marshaller marshaller =  jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Casino.xml");
			marshaller.marshal(c, file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void workerToXml(Worker w) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Casino.class);
			Marshaller marshaller =  jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Worker.xml");
			marshaller.marshal(w, file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Worker xmlToWorker(File xml) {
		JDBCManager jdbcManager = new JDBCManager();
		JDBCWorker worker = new JDBCWorker(jdbcManager);
		Worker w = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Worker.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			w = (Worker) unmarshaller.unmarshal(xml);
			worker.addWorker(w);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return w;
	}
	public Casino xmlToCasino(File xml) {
		JDBCManager jdbcManager = new JDBCManager();
		JDBCCasino casino = new JDBCCasino(jdbcManager);
		Casino c = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Worker.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			c = (Casino) unmarshaller.unmarshal(xml);
			casino.addCasino(c);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c;
	}
	

}
