package manager;

import java.util.Comparator;

import preflop.RatingComparator;
import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 1000; i++){
			Pile deck = Deck.fullDeck();
			deck.shuffle();
			Pile pile = deck.deal(5);
			try{
				Utility.calcCardPower(pile);
			} catch (Exception e){
				e.printStackTrace();
				System.out.println(pile);
			}
		}
	}
}
