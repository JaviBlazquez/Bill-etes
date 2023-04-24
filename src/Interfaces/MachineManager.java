package Interfaces;
import java.util.List;

import POJOS.Machine;

public interface MachineManager {
	
	//add a new machine
	public void addMachine(Machine M);
	
	//deletes an existing machine
	public void removeMachine(Machine M);
	
	//Updates the values of an existing machine
	public void updateMachine(Machine M);
	
	//gets a machine from its id
	public List<Machine> getListofMachine();
	
	//returns an existing machine
	public Machine getMachine();
}
