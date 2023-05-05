package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import Interfaces.XMLCasinoManager;
import POJOS.Casino;

public class XMLCasinoManagerImpl implements XMLCasinoManager{

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
	public Casino xmlToCasino(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void casinoToHtml(Casino c) {
		// TODO Auto-generated method stub
		
	}

}
