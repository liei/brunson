package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pile implements Iterable<Card>{
	
	ArrayList<Card> cards;
	
	public Pile(Card... newCards) {
		this.cards = new ArrayList<Card>();
		
		for(Card card : newCards)
			add(card);
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
		
	public void remove(Card card){
		cards.remove(card);
	}
	
	public void clear() {
		cards.clear();
	}
		
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(Card card : cards){
			sb.append(card);
			sb.append(",");
		}
		sb.setCharAt(sb.length() - 1 , '}');
		return sb.toString();
	}

	public void sort() {
		Collections.sort(cards);
	}
	
	public static Pile newPile(String... cards){
		Pile pile = new Pile();
		for(String card : cards)
			pile.add(new Card(card));
		return pile;
		
	}
}
