package manager;

import java.util.List;
import java.util.ArrayList;
import cards.*;
import player.*;

public class GameManager {
	
	private ArrayList<Player> players;
	private Pile deck;
	private CommunityCards communityCards;
	
	
	private int pot;
	private int bet;
	private int button;
	
	public GameManager(){
		players = new ArrayList<Player>();
		deck = Deck.fullDeck();
		this.button = 0;
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
		ArrayList<Player> activePlayers = players;
		bet = 0;
		dealHoleCards();
		postBlinds();
		
		//Action starts with the player sitting three places to the left of the button.
		int index = (button+3) % players.size();
		for(int i = 0; i < players.size(); i++) {
			Action action = players.get(index).getAction();
			switch(action.getType()) {
			case FOLD: 
				activePlayers.remove(index); break;
			case CALL:
				//TODO: This implementation forces the player to pay max bet and doesn't give him any discounts so he can call (max bet - his previous bet)
				activePlayers.get(index).updateStack(-bet);
				updatePot(bet);
				break;
			case RAISE:
				//TODO: implement this with a soltion to the problem in CALL.
				break;
			case BET:
				activePlayers.get(index).updateStack(-bet);
				updatePot(bet);
				bet = action.getBet();
				break;
				
			}
		}
		communityCards.clear();
	}
	
	private void dealHoleCards() {
		for(Player player : players) {
			player.addCard(deck.pop());
			player.addCard(deck.pop());
		}
		
	}
	
	private void updatePot(int delta) {
		this.pot += delta;
		
	}
	
	private void postBlinds() {
		//sb
		players.get((button-2) % players.size()).updateStack(-1);
		//bb
		players.get((button-1) % players.size()).updateStack(-2);
		this.updatePot(3);
		
		
	}
}
