package edu.ntnu.brunson.manager;

import java.util.ArrayList;
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
		
		for(Player player : players.list()){
			player.resetAmountWagered();
		}
		
		int bet = 0;
		//Small blind
		if(players.hasNext()){
			Player sb = players.next();
			Output.verbose("%s posts small blind, $%d.",sb.getName(),1);
			bet = bet(sb,1);
		}
		
		// Big blind
		if(players.hasNext()){
			Player bb = players.next();
			Output.verbose("%s posts small blind, $%d.",bb.getName(),2);
			bet = bet(bb,2);
		}
		
		//deal cards
		for(Player player : players.list()){
			player.clearHand();
			player.addCards(deck.deal(2));
		}
		
		for(Round round : Round.values()){
			Output.verbose("===%s====================",round);
			Output.verbose(players);
			
			community.add(deck.deal(round.cardsDealt()));
			Output.verbose("community: %s, pot: %d",community,pot);
			
			if(playRound(round,players,bet)) {
				Player winner = players.list().get(0);
				winner.updateStack(pot);
				Output.sparse("%s wins pot, %d",winner.getName(),pot);
				return;
			}
			
			for(Player player : players.list()){
				player.resetAmountWagered();
			}
			bet = 0;
			players.reset();
		}
		
		//Determine winner(s) at showdown
		List<Player> winners = showdown(players.list());
		int potSlice = pot/winners.size();
		Output.verbose("==Showdown==");
		StringBuilder handSummary = new StringBuilder();
		for(Player player : winners){			
			player.updateStack(potSlice);
			handSummary.append(player.getName());
			handSummary.append(" ");
		}
		handSummary.append("wins pot, $");
		handSummary.append(potSlice);
		Output.sparse(handSummary);
	}
	
	
	private boolean playRound(Round round,PlayerCycler players,int bet){
		int raises = 0;
		//Cycle through each remaining active player, hasNext return false when there's only one player left.
		while(players.hasNext()) {
			Player player = players.next();
			Output.debugf("%d == %d",player.getAmountWagered(),bet);
			if(player.getAmountWagered() == bet)
				return false;

			Action action = player.act(round,community, bet, raises, pot, players.list());
			player.setLastAction(action);
			String hand = String.format("%s %s",player.getHand().toString(),round == Round.PREFLOP ? "" : HandRating.rate(player.getHand(),community));
			
			Output.verbose("%s %s with %s",player.getName(),action,hand);
			player.addAction(round,action);
			switch(action.getType()) {
			case FOLD: 
				players.remove();
				break;
			case CHECK:
				bet(player,0);
				break;
			case CALL:
				bet(player,bet);
				break;
			case BET:
				bet = bet(player,action.getBet());
				break;
			case RAISE:
				bet = bet(player,action.getBet());
				raises++;
				break;
			}
		}
		return true;
	}
	
	private List<Player> showdown(List<Player> playerList) {
		List<Player> winners = new ArrayList<Player>();
		HandRating best = HandRating.rate(playerList.get(0).getHand(), community);
		for(Player player : playerList){
			
			HandRating rating = HandRating.rate(player.getHand(), community);
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
	
	private int bet(Player player,int bet) {
		Output.debugf("%s bets %d.%n",player.getName(),bet);
		if(bet < 0) {
			throw new IllegalArgumentException("Cannot bet negative amounts!");
		}
		pot += player.bet(bet);
		return bet;
	}
}
