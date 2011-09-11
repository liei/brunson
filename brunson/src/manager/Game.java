package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cards.*;

import player.Action;
import player.Player;
import player.PlayerCycler;
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
		
		PlayerCycler players = PlayerCycler.cycler(playerList,button);
		
		//Small blind
		if(players.hasNext())
			pot += players.next().bet(1);
		
		// Big blind
		if(players.hasNext())
			pot += players.next().bet(2);

		
		for(Round round : Round.values()){
			communityCards.add(deck.deal(round.cardsDealt()));	
			playRound(round,players,0);
			
			if(playerList.size() == 1) {
				playerList.get(0).updateStack(pot);
				return;
			}
			players.reset();
		}
		
		//Determine winner(s) at showdown
		List<Player> winners = showdown();
		int potSlice = pot/winners.size();
		for(Player player : winners)
			player.updateStack(potSlice);
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
		//Cycle through each remaining active player, hasNext return false when there's only one player left.
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
