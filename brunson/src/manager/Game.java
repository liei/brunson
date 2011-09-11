package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

		dealHoleCards();
		
		// Preflop	
		int index = button;
		
		// Small blind
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
			activePlayers.get(0).updateStack(pot);
			return;
		}
		communityCards.add(deck.deal(1));
		index = (button + 1) % activePlayers.size();
		turn(index, pot, activePlayers);
		if(isWinner(activePlayers)) {
			activePlayers.get(0).updateStack(pot);
			return;
		}
		communityCards.add(deck.deal(1));
		index = (button + 1) % activePlayers.size();
		river(index, pot, activePlayers);
		if(isWinner(activePlayers)) {
			activePlayers.get(0).updateStack(pot);
			return;
		}
		//Determine winner(s) at showdown
		showdown(activePlayers);
		for(Player player : activePlayers) {
			player.updateStack(pot / activePlayers.size());
		}
		return;
	}
	
	
	private void preFlop(Iterator<Player> players) {

		//Skip player on button
		if(players.hasNext())
			players.next();
		
		//Small blind
		if(players.hasNext())
			pot += players.next().bet(1);
		
		// Big blind
		if(players.hasNext())
			pot += players.next().bet(2);
		
		
		bet = 2;
		int raises = 0;
		//Cycle through each remaining active player, hasNext return false when there are only one player left.
		while(players.hasNext()) {
			Player player = players.next();
			if(player.getAmountWagered() == bet)
				break;
			Action action = player.act(Round.PREFLOP,communityCards, bet, raises, pot);
			player.addAction(Round.PREFLOP,action);
			switch(action.getType()) {
			case FOLD: 
				players.remove();
				break;
			case CHECK:
			case CALL:
				pot += player.bet(bet);
				break;
			case BET:
			case RAISE:
				bet = action.getBet();
				pot += player.bet(bet);
				raises++;
				break;
			}
		}
	}
	
	private void flop(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		int loop = 1000;
		int count = 0;
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
			if(count > loop) {
				throw new RuntimeException("infinite loop");
			}
			count++;
		}
	}
	
	private void turn(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		int loop = 1000;
		int count = 0;
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
			if(count > loop) {
				throw new RuntimeException("infinite loop");
			}
			count++;
		}
	}
	
	private void river(int index, int pot, List<Player> activePlayers) {
		bet = 0;
		int raises = 0;
		int loop = 1000;
		int count = 0;
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
			if(count > loop) {
				throw new RuntimeException("infinite loop");
			}
			count++;
		}
	}
	
	private void showdown(List<Player> activePlayers) {
		int loop = 1000;
		int count = 0;
		int index = 0;
		for(Player player : players) {
			player.setHandRating(communityCards);
		}
		HandRating bestHand = activePlayers.get(0).getHandRating();
		index = 1;
		boolean draw = true;
		while(activePlayers.size() > 1) {
			//Remove player with losing hand from consideration.
			if(activePlayers.get(index).getHandRating().compareTo(bestHand) < 0) {
				activePlayers.remove(index);
			}
			else if(activePlayers.get(index).getHandRating().compareTo(bestHand) > 0) {
				bestHand =activePlayers.get(index).getHandRating();
			}
			//Check for winner
			if(activePlayers.size() == 1) {
				return;
			}
			for(Player player : activePlayers) {
				if(player.getHandRating().compareTo(bestHand) != 0){
					draw = false;
					return;
				}	
			}
			if(draw){
				return;
			}
			if(count > loop) {
				throw new RuntimeException("infinite loop");
			}
			count++;
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
