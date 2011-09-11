package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cards.*;

import player.Action;
import player.Player;

public class Game {
	
	private List<Player> players;
	private int button;
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
		
		preFlop(index, players);
		
		//Check if we have a winner already.
		if(isWinner(activePlayers)) {
			return;
		}
		//Deal flop
		communityCards.clear();
		communityCards.add(deck.deal(3));	
		
		index = (button + 1) % activePlayers.size();
		flop(index, pot, activePlayers);
		if(isWinner(activePlayers)) {
			return;
		}
		communityCards.add(deck.deal(1));
		index = (button + 1) % activePlayers.size();
		turn(index, pot, activePlayers);
		if(isWinner(activePlayers)) {
			return;
		}
		communityCards.add(deck.deal(1));
		index = (button + 1) % activePlayers.size();
		river(index, pot, activePlayers);
		if(isWinner(activePlayers)) {
			return;
		}
		
	}
	
	
	private void preFlop(int index, List<Player> activePlayers) {
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
					return;
				}
			}
			index = (index + 1) % activePlayers.size();
			
			//All but one have folded and the hand ends.
			if(activePlayers.size() == 1) {
				activePlayers.get(index).updateStack(pot);
				return;
			}
		}
	}
	
	private void flop(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		for(Player player : players) {
			player.resetAmountWagered();
		}
		//Cycle through each remaining active player.
		while(true) {
			Player player = players.get(index);
			Action action = player.act(Round.FLOP,communityCards, bet, raises, pot);
			switch(action.getType()) {
			case FOLD: 
				player.setFlopAction(Action.fold());
				activePlayers.remove(player);
				break;
			case CALL:
				player.setFlopAction(Action.call());
				player.updateStack(player.getAmountWagered()-bet);
				player.updateAmountWagered(bet- player.getAmountWagered());
				updatePot(bet-player.getAmountWagered());
				break;
			case RAISE:
				player.setFlopAction(Action.raise(3*bet));
				player.updateAmountWagered(3*bet);
				player.updateStack(-3*bet);
				updatePot(3*bet);
				raises++;
				break;
			}
			for(Player p : activePlayers) {
				int callCount = 0;
				//Check if the last player action was to call.
				if(p.getPreFlopActions().get(p.getFlopActions().size() - 1).getType() == Action.Type.CALL) {
					callCount++;
				}
				//If all but one player has called it's time to see a flop.
				if(callCount == activePlayers.size() - 1) {
					return;
				}
			}
			index = (index + 1) % activePlayers.size();
			
			//All but one have folded and the hand ends.
			if(activePlayers.size() == 1) {
				activePlayers.get(index).updateStack(pot);
				return;
			}
		}
	}
	
	private void turn(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		for(Player player : players) {
			player.resetAmountWagered();
		}
		//Cycle through each remaining active player.
		while(true) {
			Player player = players.get(index);
			Action action = player.act(Round.TURN,communityCards, bet, raises, pot);
			switch(action.getType()) {
			case FOLD: 
				player.setTurnAction(Action.fold());
				activePlayers.remove(player);
				break;
			case CALL:
				player.setTurnAction(Action.call());
				player.updateStack(player.getAmountWagered()-bet);
				player.updateAmountWagered(bet- player.getAmountWagered());
				updatePot(bet-player.getAmountWagered());
				break;
			case RAISE:
				player.setTurnAction(Action.raise(3*bet));
				player.updateAmountWagered(3*bet);
				player.updateStack(-3*bet);
				updatePot(3*bet);
				raises++;
				break;
			}
			for(Player p : activePlayers) {
				int callCount = 0;
				//Check if the last player action was to call.
				if(p.getPreFlopActions().get(p.getTurnActions().size() - 1).getType() == Action.Type.CALL) {
					callCount++;
				}
				//If all but one player has called it's time to see a flop.
				if(callCount == activePlayers.size() - 1) {
					return;
				}
			}
			index = (index + 1) % activePlayers.size();
			
			//All but one have folded and the hand ends.
			if(activePlayers.size() == 1) {
				activePlayers.get(index).updateStack(pot);
				return;
			}
		}
	}
	
	private void river(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		for(Player player : players) {
			player.resetAmountWagered();
		}
		//Cycle through each remaining active player.
		while(true) {
			Player player = players.get(index);
			Action action = player.act(Round.RIVER,communityCards, bet, raises, pot);
			switch(action.getType()) {
			case FOLD: 
				player.setRiverAction(Action.fold());
				activePlayers.remove(player);
				break;
			case CALL:
				player.setRiverAction(Action.call());
				player.updateStack(player.getAmountWagered()-bet);
				player.updateAmountWagered(bet- player.getAmountWagered());
				updatePot(bet-player.getAmountWagered());
				break;
			case RAISE:
				player.setRiverAction(Action.raise(3*bet));
				player.updateAmountWagered(3*bet);
				player.updateStack(-3*bet);
				updatePot(3*bet);
				raises++;
				break;
			}
			for(Player p : activePlayers) {
				int callCount = 0;
				//Check if the last player action was to call.
				if(p.getPreFlopActions().get(p.getRiverActions().size() - 1).getType() == Action.Type.CALL) {
					callCount++;
				}
				//If all but one player has called it's time to see a flop.
				if(callCount == activePlayers.size() - 1) {
					return;
				}
			}
			index = (index + 1) % activePlayers.size();
			
			//All but one have folded and the hand ends.
			if(activePlayers.size() == 1) {
				activePlayers.get(index).updateStack(pot);
				return;
			}
		}
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
	
	private boolean isWinner(List<Player> activePlayers) {
		if(activePlayers.size() == 1) {
			return true;
		}
		return false;
	}

}
