package Interfaces;
import java.util.List;

import POJOS.Client;

public interface ClientManager {
	public void addClient(Client C);
	public Client getClient();
	public void updateClient(Client C);
	public List<Client> getListofClient();
	public void removeClient(Client C);
	public List<Client> getClientByQuery(String query);
}
