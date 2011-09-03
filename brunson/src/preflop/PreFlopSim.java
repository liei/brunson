package preflop;

import java.io.PrintWriter;
import java.util.Comparator;

import manager.Utility;

import cards.Card;
import cards.Deck;
import cards.Pile;
import cards.Suit;
import cards.Value;

public class PreFlopSim {

	private static Comparator<int[]> compareRating = new RatingComparator();
	PrintWriter writer;
	
	public static void main(String[] args) {
		new PreFlopSim(null).runSimulations(0);
	}
	
	public PreFlopSim(PrintWriter writer){
		this.writer = writer;
	}
	
	public void runSimulations(int numSims){
		Pile pile = new Pile();
		double strength;
		for(Value v1 : Value.values()){
			Pile pocket = new Pile(new Card(v1,Suit.CLUBS),new Card(v1,Suit.HEARTS));
//			strength = simulate(pocket);
			System.out.println(pocket);
			for(int i = v1.ordinal() + 1; i < Value.values().length; i++){
				Value v2 = Value.values()[i];
				Pile suited = new Pile(new Card(v1,Suit.CLUBS),new Card(v2,Suit.HEARTS)); 
				System.out.println(suited);
//				strength = simulate(suited);
				Pile offSuit = new Pile(new Card(v1,Suit.CLUBS),new Card(v2,Suit.CLUBS)); 
//				strength = simulate(offSuit);
				System.out.println(offSuit);
			}	
		}
		
	}

	private boolean runHand(Pile myHand,int players) {
		Pile deck = Deck.fullDeck();
		deck.remove(myHand);
		deck.shuffle();
		Pile[] other = new Pile[players];
		for(int i = 0; i < players; i++){
			Pile pile = new Pile();
			pile.add(deck.deal(2));
			other[i] = pile;
		}
		Pile community = deck.deal(5);

		int[] myRating = Utility.calcCardPower(myHand,community);
		for(Pile theirHand : other){
			int[] theirRating = Utility.calcCardPower(theirHand,community);
			if(compareRating.compare(myRating,theirRating) < 0)
				return false;
				
		}
		return true;
	}
	
}
