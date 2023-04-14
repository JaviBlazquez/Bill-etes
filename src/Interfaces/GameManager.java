package Interfaces;

import java.util.List;

import POJOS.Game;

public interface GameManager {

	public void addGame(Game g);
	public List<Game> getListOfGames();
	public void updateGame(Game g); //No se por qué querrías actualizar un match a lo mejor se borra
	public void removeGame(Game g);
    public Game getGame();

}
