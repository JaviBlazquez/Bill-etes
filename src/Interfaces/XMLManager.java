package Interfaces;

import java.io.File;

import POJOS.Client;

public interface XMLManager {

	public void clientToXml(Client c);
	public Client xml2Client(File xml);
	public void client2Xml(Client c);
}
