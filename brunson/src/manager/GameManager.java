package manager;

import java.util.List;
import java.util.ArrayList;
import player.*;

public class GameManager {
	
	private List<Player> players;
	private int button;
	
	public GameManager(){
		players = new ArrayList<Player>();
		button = 0;
		
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public void playGames(int hands) {
		for(int i = 0; i < hands; i++) {
			new Game(button, players);
			button = (button + 1) % players.size();
		}
	}
	

}
