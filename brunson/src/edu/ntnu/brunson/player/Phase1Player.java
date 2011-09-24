package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.cards.Value;
import edu.ntnu.brunson.manager.*;
import edu.ntnu.brunson.util.Util;
public class Phase1Player extends AIPlayer{
	
	public Phase1Player(int buyin, int aggression, int vpip, int bluffy) {
		super(buyin, aggression, vpip, bluffy);
	}
	
	public Action act(Round round, Pile communityCards, int bet, int raises, int pot) {

		HandRating powerRating = HandRating.rate(getHand(),communityCards);
		
		switch(round) {
		
		case PREFLOP:
			return getPreflopAction(bet,raises);
		case FLOP:
			return getFlopAction(bet,raises,pot,powerRating);
		case TURN:
			return getTurnAction(bet,raises,pot,powerRating);
		case RIVER:
			return getRiverAction(bet,raises,pot,powerRating);
		default:
			throw new RuntimeException("Not possible!");
		}		
	}
	
	private Action getPreflopAction(int bet, int raises) {
		//Call or Raise VPIP % of the time
		if(Util.randomBoolean(vpip)){
			return raises > 0 ? Action.call() : Action.raise(3);
		}
		return Action.fold();
	}
		
	private Action getFlopAction(int bet, int raises, int pot, HandRating rating) {	
		
		// Someone bet, we have a pair, call.
		if(bet > 0) {
			if(rating.isPair()) {
				return Action.call();
			}
			//We flopped a value hand and should raise.
			else if(0 < rating.compareTo(HandRating.pair(Value.ACE, Value.KING, Value.FOUR, Value.FIVE))) {
				return Action.raise(bet * 3);
			}
			//We have air and should fold.
			return Action.fold();
		}
		
		//Someone has raised before it's our turn to act so we only continue with two pairs or better. If we can beat two pair we'll raise.
		if(raises > 0) {
			if(rating.isTwoPair()) {
				return Action.call();
			}
			else if (rating.isBetter(HandRating.trips(Value.TWO, Value.THREE, Value.FOUR))) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair or better.
		else if(bet == 0) {
			if(rating.isBetter(HandRating.pair(Value.TWO, Value.THREE, Value.FOUR, Value.SIX))) {
				return Action.bet(3/4 * pot);
			}
			// 25% of the time we will cbet or donk on flop with complete air and hope everyone else folds.
			else if(Util.randomBoolean(25)) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
		throw new IllegalArgumentException();
	}
	
	private Action getTurnAction(int bet, int raises, int pot, HandRating rating) {
		
		// Someone bet, we'll continue with a pair of 9s or better if the pot is larger than 12.
		if(bet > 0) {
			if(rating.isPair() && pot < 13) {
				return Action.call();
			}

			//We have three of a kind and should raise.
			else if((rating.compareTo(HandRating.trips(Value.TWO, Value.THREE, Value.FOUR)) > 0)) {
				return Action.raise(bet * 3);
			}
			
			else if(rating.compareTo(HandRating.pair(Value.NINE, Value.THREE, Value.FOUR, Value.SIX)) > 0 && pot > 12) {
				return Action.call();
			}
			//Our hand isn't strong enough to continue.
			return Action.fold();
		}
		//Someone has raised before it's our turn to act so we only continue with two pairs or better. If we can beat two pair we'll raise.
		if(raises > 0) {
			if(rating.isTwoPair()) {
				return Action.call();
			}
			else if (rating.isBetter(HandRating.trips(Value.TWO, Value.THREE, Value.FOUR))) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair of 8s or better.
		else if(bet == 0) {
			if(rating.isBetter(HandRating.pair(Value.EIGHT, Value.THREE, Value.FOUR, Value.SIX))) {
				return Action.bet(3/4 * pot);
			}
			// 15% of the time we will cbet or donk on flop with complete air and hope everyone else folds.
			else if(Util.randomBoolean(15)) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
	throw new IllegalArgumentException();
	}
	
	private Action getRiverAction(int bet, int raises, int pot, HandRating rating) {
		// Someone bet, we'll continue with a pair of kings or better if the pot is larger than 30.
		if(bet > 0) {
			if(rating.isPair() && pot < 13) {
				return Action.call();
			}
			
			//We have three of a kind or better and should raise.
			else if((rating.compareTo(HandRating.trips(Value.TWO, Value.THREE, Value.FOUR)) > 0)) {
				return Action.raise(bet * 3);
			}

			else if(rating.compareTo(HandRating.pair(Value.KING, Value.THREE, Value.FOUR, Value.SIX)) > 0 && pot > 30) {
				return Action.call();
			}
			
			else if(rating.compareTo(HandRating.pair(Value.NINE, Value.THREE, Value.FOUR, Value.SIX)) > 0 && pot > 12) {
				return Action.call();
			}
			//Our hand isn't strong enough to continue.
			return Action.fold();
		}
		//Someone has raised before it's our turn to act so we only continue with two pairs or better. Raising straights.
		if(raises > 0) {
			if(rating.isTwoPair()) {
				return Action.call();
			}
			else if (rating.isBetter(HandRating.straight(Value.FIVE))) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair of Qs or better.
		else if(bet == 0) {
			if(rating.isBetter(HandRating.pair(Value.QUEEN, Value.THREE, Value.FOUR, Value.SIX))) {
				return Action.bet(3/4 * pot);
			}
			// 10% of the time we will cbet or donk and hope everyone else folds.
			else if(Util.randomBoolean(10)) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
	throw new IllegalArgumentException();
	}
			
}

