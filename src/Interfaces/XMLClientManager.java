package Interfaces;

import java.io.File;

import POJOS.Client;


public interface XMLClientManager {

	public void clientToXml(Client c);
	public void xmlToClient(File xml);
}
