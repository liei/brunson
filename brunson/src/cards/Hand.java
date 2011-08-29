package cards;

import java.util.*;

public class Hand implements Iterable<Card>{

	//field
	ArrayList<Card> cards;
	
	//constructor
	public Hand (Card[] cards){
		this.cards = new ArrayList<Card>();
		for (int i = 0; i < cards.length; i++) {
			this.cards.add(cards[i]);
		}
	}
	
	public int getCardCount(){
		return cards.size();
	}
	
	public Card getCard(int index){
		return (0 <= index && index < cards.size()) ? cards.get(index) : null;
	}
	
		
	public Card[] getCards(){
		Card[] arr = new Card[cards.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = cards.get(i);
		}
		return arr;
	}
		
	public void remove(Card card){
		cards.remove(card);
	}
		
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	

}
