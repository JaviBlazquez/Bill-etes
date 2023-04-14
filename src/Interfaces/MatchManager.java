package Interfaces;

import java.util.List;

import POJOS.Match;

public interface MatchManager {
	
	public void addMatch(Match m);
	public List<Match> getListOfMatches();
	public void updateMatch(Match m); //No se por qué querrías actualizar un match a lo mejor se borra
	public void removeMatch(Match m);
    public Match getMatch();

}
