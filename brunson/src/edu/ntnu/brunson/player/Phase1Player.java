package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.cards.Value;
import edu.ntnu.brunson.manager.*;
import edu.ntnu.brunson.util.Util;
import java.util.List;

public class Phase1Player extends AIPlayer{
	
	private static final HandRating PAIR_OF_DEUCES 	= HandRating.pair(Value.TWO, Value.THREE, Value.FOUR, Value.SIX);
	private static final HandRating PAIR_OF_EIGHTS	= HandRating.pair(Value.EIGHT, Value.THREE, Value.FOUR, Value.SIX);
	private static final HandRating PAIR_OF_NINES 	= HandRating.pair(Value.NINE, Value.THREE, Value.FOUR, Value.SIX);
	private static final HandRating PAIR_OF_QUEENS 	= HandRating.pair(Value.QUEEN, Value.THREE, Value.FOUR, Value.SIX);
	private static final HandRating PAIR_OF_KINGS 	= HandRating.pair(Value.KING, Value.THREE, Value.FOUR, Value.SIX);
	private static final HandRating PAIR_OF_ACES 	= HandRating.pair(Value.ACE, Value.KING, Value.FOUR, Value.FIVE);
	private static final HandRating LOW_TRIPS 		= HandRating.trips(Value.TWO, Value.THREE, Value.FOUR);
	private static final HandRating STRAIGT 		= HandRating.straight(Value.FIVE);
	
	private static int playerCount = 1;
	
	public Phase1Player(String name,int buyin, int aggression, int vpip, int bluffy){
		super(name,buyin, aggression,vpip,bluffy);
	}
	
	public Phase1Player(int buyin, int aggression, int vpip, int bluffy) {
		this(String.format("Phase1-%d",playerCount++),buyin, aggression, vpip, bluffy);
	}
	
	
	public Action act(Round round, Pile communityCards, int bet, int raises, int pot, List<Player> activePlayers) {
		
		if(pot < 0 || raises < 0 || activePlayers.size() < 0) {
			throw new IllegalArgumentException(String.format("Invalid input pot: %d, raises: %d, players: %d",pot,raises,activePlayers.size()));
		}

		if(round == Round.PREFLOP) {
			return getPreflopAction(bet,raises);
		}
		HandRating powerRating = HandRating.rate(getHand(),communityCards);
		switch(round) {
		case FLOP:
			return getFlopAction(bet,raises,pot,powerRating);
		case TURN:
			return getTurnAction(bet,raises,pot,powerRating);
		case RIVER:
			return getRiverAction(bet,raises,pot,powerRating);
		default:
			throw new IllegalArgumentException("round can't be null");
		}		
	}
	
	private Action getPreflopAction(int bet, int raises) {
		//Call or raise VPIP % of the time
		if(Util.randomBoolean(vpip))
			return raises > 2 ? Action.call() : Action.raise(3*bet); 
		return Action.fold();
	}
		
	private Action getFlopAction(int bet, int raises, int pot, HandRating rating) {	
		
		// nobody has bet so we're betting if we have a pair of 8s or better, bluff 25%
		if(bet == 0)
			return zeroBetAction(pot, rating,PAIR_OF_DEUCES,25);
				
		if(raises == 0) {							// someone bet
			if(rating.isBetter(PAIR_OF_ACES))		// we flopped a value hand and should raise
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
			if(rating.isPair())						// we have a pair, call
				return Action.call();
		} else {									// someone has raised
			if(rating.isTwoPair())  				// we continue with two pairs
				return Action.call();
			if (rating.isBetter(LOW_TRIPS)) 		// if we can beat two pair we'll raise
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
		}
		return Action.fold();
	}
	
	private Action getTurnAction(int bet, int raises, int pot, HandRating rating) {
		
		// nobody has bet so we're betting if we have a pair of 8s or better, bluff 25%
		if(bet == 0)
			return zeroBetAction(pot, rating,PAIR_OF_EIGHTS,25);
		
		if(raises == 0) {						// someone bet
			if(rating.isPair() && pot < 13)
				return Action.call();
			//we have three of a kind or better and should raise.
			if(rating.isBetter(LOW_TRIPS))
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
			//we'll continue with a pair of 9s or better if the pot is larger than 12.
			if(rating.isBetter(PAIR_OF_NINES) && pot > 12)
				return Action.call();
		} else { 								// someone has raised  
			if(rating.isTwoPair()) 				// we only continue with two pairs or better
				return Action.call();
			if (rating.isBetter(LOW_TRIPS)) 	// if we can beat two pair we'll raise
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
		}
		return Action.fold();
	}
	
	private Action getRiverAction(int bet, int raises, int pot, HandRating rating) {
		
		// nobody has bet so we're betting if we have a pair of Qs or better, bluff 10%
		if(bet == 0)
			return zeroBetAction(pot, rating,PAIR_OF_QUEENS,10);
		
		if(raises == 0) {							//someone has bet
			//we'll continue with a pair of kings or better if the pot is larger than 30.
			if(rating.isPair() && pot < 13) {
				return Action.call();
			}
			
			//We have three of a kind or better and should raise.
			if(rating.isBetter(LOW_TRIPS)) {
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
			}

			if(rating.isBetter(PAIR_OF_KINGS) && pot > 30) {
				return Action.call();
			}
			
			if(rating.isBetter(PAIR_OF_NINES) && pot <= 30) {
				return Action.call();
			}
			
		} else { 									//someone has raised 
			if (rating.isBetter(STRAIGT)) 			// re-raise if we have straight or better
				return raises > 2 ? Action.call() : Action.raise(bet * 3);
			if(rating.isBetter(PAIR_OF_ACES)) 		//call with pair of aces or better
				return Action.call();
		}
		return Action.fold(); 						// our hand isn't strong enough to continue
	}

	private Action zeroBetAction(int pot, HandRating rating,HandRating betRating, int bluff) {
		if(rating.isBetter(betRating))				// bet if rating is better that betRating
			return Action.bet((int)(0.75 * pot));
		if(Util.randomBoolean(bluff))				// bluff bluff% amount of the time
			return Action.bet((int)(0.75 * pot));
		//hope we get something good after a free card
		return Action.check();
	}
}

