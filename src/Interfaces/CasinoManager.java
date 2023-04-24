package Interfaces;

import java.util.List;

import POJOS.Casino;

public interface CasinoManager {
	public void addCasino(Casino C);
	public Casino getCasino();
	public void updateCasino(Casino C);
	public List<Casino> getListofCasino();
}
