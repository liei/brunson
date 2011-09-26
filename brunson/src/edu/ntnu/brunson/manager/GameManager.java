package edu.ntnu.brunson.manager;

import java.util.List;
import java.util.ArrayList;

import edu.ntnu.brunson.opponentmodeler.OpponentModeler;
import edu.ntnu.brunson.player.*;

public class GameManager {
	
	private List<Player> players;
	
	public GameManager(){
		players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public void playGames(int hands) {
		OpponentModeler.init(players);
		for(int hand = 0; hand < hands; hand++) {
			Output.verbose("===== Hand %4d ==========",hand + 1);
			int button = hand % players.size();
			for (int i = 0; i < players.size(); i++)
				Output.verbose("%s %s",players.get(i), button == i ? "(*)" : "");
			
			Game game = new Game(players,button);
			game.playHand();
			
			Output.verbose("==========================");
		}
		Output.results("==RESULTS==");
		for (int i = 0; i < players.size(); i++)
			Output.results("%s",players.get(i));
		OpponentModeler.print(Output.SPARSE);
	}
	
}
