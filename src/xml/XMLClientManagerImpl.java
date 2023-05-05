package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.Marshaller;

import Interfaces.XMLClientManager;
import POJOS.Client;

public class XMLClientManagerImpl implements XMLClientManager {

	
	@Override
	public void clientToXml(Client c) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Marshaller marshaller =  jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("./xmls/Client.xml");
			marshaller.marshal(c, file);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Client xmlToClient(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clientToHtml(Client c) {
		// TODO Auto-generated method stub
		
	}

}
