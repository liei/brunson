package manager;

import cards.Card;
import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		Pile deck = Deck.fullDeck();
		Card card1 = Deck.getCard("9s");
		Card card2 = Deck.getCard("9s");
		
		if(card1 == card2)
			System.out.println("Success");
		else 
			System.out.println("Fail");
		
		card1 = new Card("9s");
		card1 = new Card("9s");
		
		if(card1 == card2)
			System.out.println("Success");
		else
			System.out.println("Fail");
		
		
		System.out.println(card1);
		System.out.println(deck);
		
		Pile pile = Pile.newPile("9s","9d","9c","2s","3s","2h","Qd");
		int[] powerRating = Utility.calcCardPower(pile);
		
		System.out.println(pile);
		for(int i : powerRating)
			System.out.printf(" %2d",i);
		System.out.println();
		
		
	}
}
