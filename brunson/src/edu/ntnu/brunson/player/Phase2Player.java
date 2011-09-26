package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.manager.HandRating;
import edu.ntnu.brunson.manager.Round;
import edu.ntnu.brunson.util.*;
import java.util.List;

public class Phase2Player extends AIPlayer{

	private static int playerCount = 1;
	
	public Phase2Player(String name,int buyin, int aggression, int vpip, int bluffy){
		super(name,buyin, aggression,vpip,bluffy);
	}
	
	public Phase2Player(int buyin, int aggression, int vpip, int bluffy) {
		this(String.format("Phase2-%d",playerCount++),buyin, aggression, vpip, bluffy);
	}

	public Action act(Round round, Pile community, int bet, int raises, int pot, List<Player> activePlayers) {
	
		if(round == Round.PREFLOP) {
			return getPreflopAction(bet,raises, activePlayers.size(), pot);
		}
		double handStrength = HandRating.strength(this.getHand(), community, activePlayers.size());
		if(bet > 0) {
			
			if(Util.potOdds(pot, bet) < handStrength) {
				if((handStrength - 0.9) * 1000 > 100- aggression) {
					if(raises > 2) {
						return Action.call();
					}
					return Action.raise(3* bet);
				}
				return Action.call();
			}
			return Action.fold();
		}
		
		if(bet == 0 | bet == -1) {
			if(handStrength > 0.5) {
				return Action.bet((int)(0.75*pot));
			}
			else if(Util.randomBoolean(bluffy)){
				return Action.bet((int)(0.75*pot));
			}
			return Action.fold();
		}
		
		throw new RuntimeException("Phase2Player not sure what to do!");
	}

	private Action getPreflopAction(int bet, int raises, int players, int pot) {
		
		//We're first to act and raise the top vpip% of hands.
		if(bet == 2 && pft.inPercentile(getHand(), 100-vpip)) {
			return Action.raise(3*bet);
			
		}
		//Someone has raised before us, re-raise top 3% of hands, if it's a single raise call with top vpip% of hands.
		else if(raises > 0) {
			if(pft.inPercentile(getHand(), 97)) {
				if(raises > 2) {
					return Action.call();
				}
				return Action.raise(3*bet);
			}
			
			else if(raises == 1 && pft.inPercentile(getHand(), 100-vpip)) {
				return Action.call();
			}
		}
		//Call if pot odds are great and you have an OK hand.
		else if(raises > 0 && pft.inPercentile(getHand(), 100-6) && Util.potOdds(pot, bet-getAmountWagered()) < 0.50) {
			return Action.call();
		}
		return Action.fold();
	}



}


