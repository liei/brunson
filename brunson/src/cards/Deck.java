package cards;

import java.util.*;

public class Deck implements Iterable<Card>{
	
	ArrayList<Card> deck;
	Random random = new Random();
	
	//Constructor
	public Deck() {
		this.deck = new ArrayList<Card>();
		for(Value value : Value.values()) {
			for(Suit suit : Suit.values()) {
				deck.add(new Card(value, suit));
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
	public Pile getFlop() {
		Pile flop = new Pile();
		flop.add(this.pop());
		flop.add(this.pop());
		flop.add(this.pop());
		return flop;
	}
	@Override
	public Iterator<Card> iterator() {
		return deck.iterator();
	}
	

	

}
