package Interfaces;
import java.util.List;

import POJOS.Table;

public interface TableManager {

	//returns a table
	public Table getTable();
	
	//Adds a new Table
	public void addTable(Table T);
	
	//Updates the values of a table
	public void updateTable(Table T);
	
	//Removes a table
	public void removeTable(Table T);
	
	//Get a table from Id
	public List<Table> getListofTable();
	
}
