package xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Interfaces.XMLClientManager;
import POJOS.Client;
import jdbc.JDBCClient;
import jdbc.JDBCManager;

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
	public void xmlToClient(File xml) {
		Client c = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			c = (Client) unmarshaller.unmarshal(xml);
			JDBCManager jdbcManager = new JDBCManager();
			JDBCClient jdbcClient = new JDBCClient(jdbcManager);
			List<Client> listClient = jdbcClient.getListofClient();
			Iterator<Client> itClient = listClient.iterator();
			while(itClient.hasNext()) {
				if(c.getClientId() == itClient.next().getClientId()) {
					c.setClientId(listClient.size()+1);
				}
			}
			jdbcClient.addClient(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
