package manager;

import java.util.List;
import java.util.ArrayList;
import cards.*;
import player.*;

public class GameManager {
	
	List<Player> players;
	Deck deck;
	
	
	private int pot;
	private int bet;
	
	public GameManager(){
		players = new ArrayList<Player>();
		deck = new Deck();
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	private void playGame(int hands) {
		for(int i=0; i < hands; i++) {
			playHand();
		}
		
	}
	
	private void playHand(){
		
		for(Player player : players) {
		}
		CommunityCards communityCards = new CommunityCards();
		
	}
	
}
