package manager;

import cards.Card;
import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		Pile pile = Pile.newPile("9s","9d","9c","2s","3s","2h","Qd");
		int[] powerRating = Utility.calcCardPower(pile);
		
		System.out.println(pile);
		for(int i : powerRating)
			System.out.printf(" %2d",i);
		System.out.println();
		
		
	}
}
