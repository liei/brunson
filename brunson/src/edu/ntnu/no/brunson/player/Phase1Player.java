package edu.ntnu.no.brunson.player;

import java.util.Random;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.no.brunson.manager.*;
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
			return getFlopAction(bet, raises, pot, powerRating);
		case TURN:
			return getTurnAction(bet, raises, pot, powerRating);
		case RIVER:
			return getRiverAction(bet, raises, pot, powerRating);
		}		
		throw new IllegalArgumentException();
	}
		
		private Action getPreflopAction(int bet, int raises) {
			Random randomGenerator = new Random();
			int r = randomGenerator.nextInt(100) + 1;
			//Call VPIP % of the time if someone raises.
			if(raises == 1) {
				if(r > vpip) {
					return Action.call();
				}
				return Action.fold();
			}
			//Raise VPIP % of the time, otherwise fold.
			if(r > vpip) {
				return Action.fold();
			}

			return Action.raise(3);
		}
		
	private Action getFlopAction(int bet, int raises, int pot, HandRating powerRating) {
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(100) + 1;
		
		// Someone bet, we have a pair, call.
		if(bet > 0) {
			if(powerRating[0] == 2) {
				return Action.call();
			}
			//We flopped a value hand and should raise.
			else if(powerRating[0] > 3) {
				return Action.raise(bet * 3);
			}
			//We have air and should fold.
			return Action.fold();
		}
		//Someone has raised before it's our turn to act so we only continue with two pairs or better. If we can beat two pair we'll raise.
		if(raises > 0) {
			if(powerRating[0] == 3) {
				return Action.call();
			}
			else if (powerRating[0] > 3) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair or better.
		else if(bet == 0) {
			if(powerRating[0] > 1) {
				return Action.bet(3/4 * pot);
			}
			// 25% of the time we will cbet or donk on flop with complete air and hope everyone else folds.
			else if(r > 75) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
	throw new IllegalArgumentException();
	}
	
	private Action getTurnAction(int bet, int raises, int pot, HandRating powerRating) {
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(100) + 1;
		
		// Someone bet, we'll continue with a pair of 9s or better if the pot is larger than 12.
		if(bet > 0) {
			if(powerRating[0] == 2 && pot < 13) {
				return Action.call();
			}
			else if(powerRating[0] == 2 && powerRating[1] > 8 && pot > 12) {
				return Action.call();
			}
			//We have three of a kind and should raise.
			else if(powerRating[0] > 4) {
				return Action.raise(bet * 3);
			}
			//Our hand isn't strong enough to continue.
			return Action.fold();
		}
		//Someone has raised before it's our turn to act so we only continue if we can beat two pairs.
		if(raises > 0) {
			if(powerRating[0] == 5) {
				return Action.call();
			}
			//We have the effective nuts and should re-raise.
			else if (powerRating[0] > 7) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair or better.
		else if(bet == 0) {
			if(powerRating[0] > 3) {
				return Action.bet(3/4 * pot);
			}
			// 20% of the time we will cbet or donk on flop with complete air and hope everyone else folds.
			else if(r > 80) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
	throw new IllegalArgumentException();
	}
	
	private Action getRiverAction(int bet, int raises, int pot, int[] powerRating) {
		Random randomGenerator = new Random();
		int r = randomGenerator.nextInt(100) + 1;
		
		// Someone bet, we'll continue with a pair of kings or better if the pot is larger than 30.
		if(bet > 0) {
			if(powerRating[0] == 2 && pot < 13) {
				return Action.call();
			}
			else if(powerRating[0] == 2 && powerRating[1] > 8 && pot > 12) {
				return Action.call();
			}
			else if(powerRating[0] == 2 && powerRating[1] > 13 && pot > 30) {
				return Action.call();
			}
			//We have three of a kind and should raise.
			else if(powerRating[0] > 4) {
				return Action.raise(bet * 3);
			}
			//Our hand isn't strong enough to continue.
			return Action.fold();
		}
		//Someone has raised before it's our turn to act so we only continue if we can beat two pairs.
		if(raises > 0) {
			if(powerRating[0] == 5) {
				return Action.call();
			}
			//We have the effective nuts and should re-raise.
			else if (powerRating[0] > 7) {
				return Action.raise(bet * 3);
			}
		}
		//Nobody has bet so we're betting if we have a pair of queens or better.
		else if(bet == 0) {
			if(powerRating[0] > 12) {
				return Action.bet(3/4 * pot);
			}
			// 10% of the time we will cbet or donk on flop with complete air and hope everyone else folds.
			else if(r > 90) {
				return Action.bet(3/4 * pot);
			}
			
			return Action.check();
				
		}
	throw new IllegalArgumentException();
	}
			
}

