package cards;

import java.util.*;

public class Deck {
	
	ArrayList<Card> deck;
	Random random = new Random();
	
	//Constructor
	public Deck() {
		this.deck = new ArrayList<Card>();
		for(Value value : Value.values()) {
			for(Suit suit : Suit.values()) {
				deck.add(new Card(suit, value));
			}
		}
		
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	//Remove a random card from the deck. Somewhat easier than actually shuffling the entire deck.
	public Card pop() {
		int index = random.nextInt(deck.size());
		return deck.remove(index);
	}
	

	

}
