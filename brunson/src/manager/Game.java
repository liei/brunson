package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cards.*;

import player.Action;
import player.Player;
import util.Util;

public class Game {
	
	private List<Player> playerList;
	private int button;
	private int pot;
	private int bet;
	private int raises;
	private Pile deck;
	private Pile communityCards;
	
	public Game(int button, List<Player> players) {
		this.button = button;
		playerList = new ArrayList<Player>(players);
		this.communityCards = new Pile();
		this.deck = Deck.fullDeck();
		deck.shuffle();
	}
	
	private void playHand(int button){
		
		dealHoleCards();
		
		// Preflop	
		
		Iterator<Player> players = Util.playerCycler(playerList,button);
		
		//Skip player on button
		if(players.hasNext())
			players.next();
		
		//Small blind
		if(players.hasNext())
			pot += players.next().bet(1);
		
		// Big blind
		if(players.hasNext())
			pot += players.next().bet(2);
		
		
		playRound(Round.PREFLOP,players,2);
		//Check if we have a winner already.
		if(playerList.size() == 1){
			playerList.get(0).updateStack(pot);
			return;
		}

		//Flop
		communityCards.add(deck.deal(3));	
		playRound(Round.FLOP,players,0);

		if(playerList.size() == 1) {
			playerList.get(0).updateStack(pot);
			return;
		}
		
		//Turn
		communityCards.add(deck.deal(1));
		playRound(Round.TURN,players,0);
		if(playerList.size() == 1) {
			playerList.get(0).updateStack(pot);
			return;
		}
		//River
		communityCards.add(deck.deal(1));
		playRound(Round.TURN,players,0);
		if(playerList.size() == 1) {
			playerList.get(0).updateStack(pot);
			return;
		}

		//Determine winner(s) at showdown
		showdown();
		for(Player player : playerList) {
			player.updateStack(pot / playerList.size());
		}
		return;
	}
	
	private List<Player> showdown() {
		List<Player> winners = new ArrayList<Player>();
		
		HandRating best = HandRating.rate(playerList.get(0).getHand());
		for(Player player : playerList){
			HandRating rating = HandRating.rate(player.getHand());
			int comp = best.compareTo(rating);
			if(comp == 0)
				winners.add(player);
			else if (comp < 0){
				winners.clear();
				winners.add(player);
			}
		}
		return winners;
	}
	
	public void playRound(Round round,Iterator<Player> players,int bet){
		int raises = 0;
		//Cycle through each remaining active player, hasNext return false when there are only one player left.
		while(players.hasNext()) {
			Player player = players.next();
			if(player.getAmountWagered() == bet)
				break;
			Action action = player.act(round,communityCards, bet, raises, pot);
			player.addAction(round,action);
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
	
	private void dealHoleCards() {
		for(Player player : playerList) {
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
