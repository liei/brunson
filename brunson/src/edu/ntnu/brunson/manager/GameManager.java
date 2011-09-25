package edu.ntnu.brunson.manager;

import java.util.List;
import java.util.ArrayList;

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
		for(int hand = 0; hand < hands; hand++) {
			Output.printf("===== Hand %4d ==========%n",hand + 1);
			int button = hand % players.size();
			for (int i = 0; i < players.size(); i++)
				Output.printf("%s %s%n",players.get(i), button == i ? "(*)" : "");
			
			Game game = new Game(players,button);
			game.playHand();
			
			Output.println("==========================");
			Output.println();
//			Output.printHH();
		}
		Output.println("==RESULTS==");
		for (int i = 0; i < players.size(); i++)
			Output.printf("%s%n",players.get(i));
	}
	

}
