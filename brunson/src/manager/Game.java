package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.*;

import player.Action;
import player.Player;

public class Game {
	
	private List<Player> players;
	//private int button;
	private int pot;
	private int bet;
	private int raises;
	private Pile deck;
	private Pile communityCards;
	
	public Game(int button, List<Player> players) {
		this.button = button;
		this.players = players;
		this.communityCards = new Pile();
		this.deck = Deck.fullDeck();
		deck.shuffle();
	}
	
	private void playHand(int button){
		
		List<Player> activePlayers = new ArrayList<Player>();
		Collections.copy(activePlayers,players);
		
		// Preflop	
		int index = button;
		
		// Small blind
		index = (index + 1) % activePlayers.size(); 
		activePlayers.get(index).updateStack(-1);
		activePlayers.get(index).updateAmountWagered(1);
		updatePot(1);
		
		// Big blind
		index = (index + 1) % activePlayers.size(); 
		activePlayers.get(index).updateStack(-2);
		activePlayers.get(index).updateAmountWagered(2);
		updatePot(2);
		
		dealHoleCards();
		
		activePlayers = preFlop(index, players);
		
		if(activePlayers.size() == 1) {
			return;
		}
		communityCards.clear();
		communityCards.add(deck.deal(3));	
	}
	
	//Takes in an index which is the first player to act pre-flop as well as a list of players and returns a list of active players.
	private List<Player> preFlop(int index, List<Player> activePlayers) {
		bet = 2;
		int raises = 0;
		//Cycle through each remaining active player.
		while(true) {
			Player player = players.get(index);
			Action action = player.act(Round.PREFLOP,communityCards, bet, raises, pot);
			switch(action.getType()) {
			case FOLD: 
				player.setPreFlopAction(Action.fold());
				activePlayers.remove(player);
				break;
			case CALL:
				player.setPreFlopAction(Action.call());
				player.updateStack(player.getAmountWagered()-bet);
				player.updateAmountWagered(bet- player.getAmountWagered());
				updatePot(bet-player.getAmountWagered());
				break;
			case RAISE:
				player.setPreFlopAction(Action.raise(3*bet));
				player.updateAmountWagered(3*bet);
				player.updateStack(-3*bet);
				updatePot(3*bet);
				raises++;
				break;
			}
			for(Player p : activePlayers) {
				int callCount = 0;
				//Check if the last player action was to call.
				if(p.getPreFlopActions().get(p.getPreFlopActions().size() - 1).getType() == Action.Type.CALL) {
					callCount++;
				}
				//If all but one player has called it's time to see a flop.
				if(callCount == activePlayers.size() - 1) {
					return activePlayers;
				}
			}
			index = (index + 1) % activePlayers.size();
			
			//All but one have folded and the hand ends.
			if(activePlayers.size() == 1) {
				activePlayers.get(index).updateStack(pot);
				return activePlayers;
			}
		}
	}
	
	private List<Player> Flop(int index, int pot, List<Player> activePlayers) {
		//TODO Implement flop play!
		return activePlayers;
	}
	
	private List<Player> Turn(int index, int pot, List<Player> activePlayers) {
		//TODO Implement turn play!
		return activePlayers;
	}
	
	private List<Player> River(int index, int pot, List<Player> activePlayers) {
		//TODO Implement river play!
		return activePlayers;
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

}
