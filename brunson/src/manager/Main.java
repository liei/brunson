package manager;

import java.util.Comparator;

import preflop.RatingComparator;
import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		Pile deck = Deck.fullDeck();
		deck.shuffle();
		Pile community = deck.deal(3);
		Pile pile1 = deck.deal(2);
		Pile pile2 = deck.deal(2);
		
		int[] r1 = Utility.calcCardPower(community,pile1);
		int[] r2 = Utility.calcCardPower(community,pile2);
		
		Comparator<int[]> rating = new RatingComparator(); 

		System.out.println(new Pile(pile1,community));
		System.out.println(new Pile(pile2,community));
		System.out.println(rating.compare(r1,r2));
	}
}
