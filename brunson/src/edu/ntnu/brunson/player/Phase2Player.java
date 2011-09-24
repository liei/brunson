package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.manager.Round;

public class Phase2Player extends AIPlayer{

	private static int playerCount = 0;
	
	public Phase2Player(String name,int buyin, int aggression, int vpip, int bluffy){
		super(name,buyin, aggression,vpip,bluffy);
	}
	
	public Phase2Player(int buyin, int aggression, int vpip, int bluffy) {
		this(String.format("Phase2-%d",playerCount++),buyin, aggression, vpip, bluffy);
	}

	@Override
	public Action act(Round round, Pile communityCards, int bet, int raises, int pot) {
		switch(round){
		
		case PREFLOP:
			return Action.fold();
		case FLOP:
			return Action.fold();
		case TURN:
			return Action.fold();
		case RIVER:
			return Action.fold();
		default:
			throw new RuntimeException();
		}
	}

}
