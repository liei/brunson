package edu.ntnu.brunson.player;

import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.cards.Value;
import edu.ntnu.brunson.manager.*;

import java.util.List;
import edu.ntnu.brunson.opponentmodeler.*;


public class Phase3Player extends Phase2Player {
	
	private static final HandRating LOW_TRIPS 		= HandRating.trips(Value.TWO, Value.THREE, Value.FOUR);
	
	static int playerCount = 1;
	
	public Phase3Player(String name,int buyin, int aggression, int vpip, int bluffy) {
		super(name,buyin,aggression, vpip, bluffy);
	}
	
	public Phase3Player(int buyin, int aggression, int vpip, int bluffy) {
		this(String.format("Phase3-%d",playerCount++),buyin, aggression, vpip, bluffy);
	}
	
	public Action act(Round round, Pile community, int bet, int raises, int pot, List<Player> players) {

		
		if(round == Round.PREFLOP) {
			return getPreflopAction(bet,raises, players.size(), pot);
		}
		//My handstrength
		double handStrength = HandRating.strength(getHand(), community, players.size());
		double weakerVillains = 0;
		double strongestVillain = 0;
		HandRating powerRating = HandRating.rate(getHand(),community);
		//Get strengthEstimate for remaining players from OpponentModeler.
		for(Player player : players) {
			Context c = new Context(round, players.size(), raises,player.lastAction());
			StrengthEstimate se = OpponentModeler.get(player, c);
			
			//If we have less than 3 datapoints we shouldn't base our decisions on it.
			if(se.getTimesSeen() < 3) {
				return super.act(round, community, bet, raises, pot, players);
			}	
			
			//Compare handstrengths
			if(handStrength >  se.getStrength()) {
				weakerVillains++;
				strongestVillain = se.getStrength() > strongestVillain ? se.getStrength(): strongestVillain;
			}
		}
		
		double strengthRatio = weakerVillains / (double)players.size();
		if(bet == 0 && raises == 0) {
			//We actually beat them and should bet.
			if(strengthRatio * 100 > 100-aggression) {
				return Action.bet((int)0.75 * pot);
			}
			
			//Our model indicates that the strongest villain is weak and we might get him to fold with a cheap bluff.
			else if(strongestVillain < 0.7) {
				return Action.bet((int)0.5 * pot);
			}
		}
		//Someone has bet.
		if(bet > 0) {
			
			//We only want to raise if villain has _something_. If he has complete garbage and we raise for value he will fold.
			if(strongestVillain < handStrength) {
				if(strongestVillain > 0.8) {
					return Action.raise(3*bet);
				}
				
				//We actually have air ourselves and will attempt a cheap bluff when we perceive weakness.
				else if(strongestVillain < 0.7 && handStrength < 0.7) {
					return Action.raise(2*bet);
				}
				return Action.call();
			}
			//We don't want to fold trips or better on the flop or turn, as we might improve.
			else if(strongestVillain > handStrength) {
				if(powerRating.isBetter(LOW_TRIPS) && (round == Round.FLOP || round == Round.TURN)) {
					return Action.call();
			}
			else
				return Action.fold();
				
			}
		}
			
		throw new RuntimeException("Phase3Player has no idea what to do!");
	}

}
