package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.manager.HandRating;
import edu.ntnu.brunson.manager.Round;

public class Phase2Player extends AIPlayer{

	private static int playerCount = 1;
	
	public Phase2Player(String name,int buyin, int aggression, int vpip, int bluffy){
		super(name,buyin, aggression,vpip,bluffy);
	}
	
	public Phase2Player(int buyin, int aggression, int vpip, int bluffy) {
		this(String.format("Phase2-%d",playerCount++),buyin, aggression, vpip, bluffy);
	}

	public Action act(Round round, Pile community, int bet, int raises, int pot, int players) {
	
		if(round == Round.PREFLOP) {
			return getPreflopAction(bet,raises);
		}
		HandRating powerRating = HandRating.rate(getHand(),community);
		
		HandRating.strength(this.getHand(), community, players);
		
		return null;
	}

	private Action getPreflopAction(int bet, int raises) {
		// TODO Auto-generated method stub
		if(bet == 0) {
			//raise top vpip% of hands. otherwise fold.
		}
		
		else if(bet == 1 && raises == 0) {
			//re-raise top 3% of hands to 3 * bet.
			
			//call with vpip-3% remaining hands, otherwise fold.
		}
		
		else if(bet == 1 && raises > 0) {
			//re-raise top 3% 3*pot, call med neste 2% av hender om pot-odds indikerer at du blir re-raiset.
		}
		return null;
	}



}


