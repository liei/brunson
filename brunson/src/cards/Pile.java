package cards;

import java.util.ArrayList;
import java.util.Iterator;

public class Pile implements Iterable<Card>{
	
	ArrayList<Card> cards;
	
	public Pile(){
		this.cards = new ArrayList<Card>();
	}
	
	public void add(Card card){
		cards.add(card);
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
	
	public void clear() {
		cards.clear();
	}
		
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
}
