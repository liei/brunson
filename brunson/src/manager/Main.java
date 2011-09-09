package manager;

import cards.Card;
import cards.Deck;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		
		for(Card card : Deck.getPile("AsAh9s")){
			System.out.println(card);
		}
	}
}
