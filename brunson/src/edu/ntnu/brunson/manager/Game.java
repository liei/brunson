package edu.ntnu.brunson.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ntnu.brunson.cards.*;
import edu.ntnu.brunson.player.Action;
import edu.ntnu.brunson.player.Player;
import edu.ntnu.brunson.player.PlayerCycler;


public class Game {
	
	private PlayerCycler players;
	private int pot;
	private Pile community;
	private Pile deck;

	public Game(List<Player> playerList,int button){
		players = PlayerCycler.cycler(playerList,button);
		pot = 0;
		deck = Deck.fullDeck().shuffle();
		community = new Pile();
	}
		
	
	public void playHand(){
		
		//Small blind
		if(players.hasNext())
			pot += players.next().bet(1);
		
		// Big blind
		if(players.hasNext())
			pot += players.next().bet(2);

		
		for(Round round : Round.values()){
			community.add(deck.deal(round.cardsDealt()));	
			boolean winner = playRound(round,players,0);
			if(winner) {
				players.list().get(0).updateStack(pot);
				return;
			}
			players.reset();
		}
		
		//Determine winner(s) at showdown
		List<Player> winners = showdown(players.list());
		int potSlice = pot/winners.size();
		for(Player player : winners)
			player.updateStack(potSlice);
	}
	
	
	private boolean playRound(Round round,Iterator<Player> players,int bet){
		int raises = 0;
		//Cycle through each remaining active player, hasNext return false when there's only one player left.
		while(players.hasNext()) {
			Player player = players.next();
			if(player.getAmountWagered() == bet)
				return false;
			Action action = player.act(round,community, bet, raises, pot);
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
		return true;
	}
	
	private List<Player> showdown(List<Player> playerList) {
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
	
}
