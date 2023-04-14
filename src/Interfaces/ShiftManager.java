package Interfaces;

import java.util.List;

import POJOS.Shift;

public interface ShiftManager {

	public void addShift(Shift s);
	public List<Shift> getListOfShifts();
	public void updateShift(Shift s); //Podría usarse para extender cuanto dura un turno
	public void removeShift(Shift s);
	//Get the shift
    public Shift getShift();
}
