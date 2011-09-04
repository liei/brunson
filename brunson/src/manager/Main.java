package manager;

import java.util.Comparator;

import preflop.RatingComparator;
import cards.Card;
import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		
		for(Card card : Deck.getPile("AsAh9s")){
			System.out.println(card);
		}
	}
}
