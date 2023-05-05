package Interfaces;

import java.io.File;

import POJOS.Casino;


public interface XMLCasinoManager {
	public void casinoToXml(Casino c);
	public Casino xmlToCasino(File xml);
	public void casinoToHtml(Casino c);
}
