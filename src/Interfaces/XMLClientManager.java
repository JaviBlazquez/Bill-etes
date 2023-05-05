package Interfaces;

import java.io.File;

import POJOS.Client;

public interface XMLClientManager {

	public void clientToXml(Client c);
	public Client xmlToClient(File xml);
	public void clientToHtml(Client c);
}
