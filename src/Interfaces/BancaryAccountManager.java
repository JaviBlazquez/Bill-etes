package Interfaces;
import POJOS.BancaryAccount;

public interface BancaryAccountManager {
	
	//Add a new bancary account
	public void addBancaryAccount(BancaryAccount BA);
	
	//Get the bancary account
	public BancaryAccountManager getBancaryAccount();
	
	//Updates the bancary account to new values
	public void updateBancaryAccount(BancaryAccount BA);
	
}
