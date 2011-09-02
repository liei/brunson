package manager;

import java.util.List;
import java.util.ArrayList;
import cards.*;
import player.*;

public class GameManager {
	
	List<Player> players;
	Pile deck;
	
	
	private int pot;
	private int bet;
	
	public GameManager(){
		players = new ArrayList<Player>();
		deck = Deck.fullDeck();
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
		int dealer = 0;
		//deal holecards
		for(Player player : players) {
			player.addHoleCard(deck.pop());
			player.addHoleCard(deck.pop());
		}
		CommunityCards communityCards = new CommunityCards();
		
	}
	
}
