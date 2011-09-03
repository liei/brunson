package cards;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Pile implements Iterable<Card>{
	
	List<Card> cards;
	
	public Pile(){
		cards = new ArrayList<Card>();
	}
	
	public Pile(Card... newCards) {
		this();
		for(Card card : newCards)
			add(card);
	}

	public Pile(Pile... piles) {
		this();
		for(Pile pile : piles){
			for(Card card : pile)
				add(card);
		}
	}

	public void add(Card card){
		cards.add(card);
	}

	public void add(Pile... piles) {
			for(Pile pile : piles){
				for(Card card : pile)
					add(card);
			}
	}
	
	public int getCardCount(){
		return cards.size();
	}
	
	public Card getCard(int index){
		return (0 <= index && index < cards.size()) ? cards.get(index) : null;
	}
	
	public void remove(Card... card){
		cards.remove(card);
	}
		
	public void remove(Pile... piles) {
		for(Pile pile : piles){
			for(Card card : pile){
				remove(card);
			}
		}
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

	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public static Pile newPile(String... cards){
		Pile pile = new Pile();
		for(String card : cards)
			pile.add(Deck.getCard(card));
		return pile;
	}

	public int size() {
		return cards.size();
	}

	public Card pop() {
		return cards.remove(0);
	}

	public Pile deal(int numCards) {
		Pile pile = new Pile();
		for(int i = 0; i < numCards; i++)
			pile.add(pop());
		return pile;
	}
	
	public Pile copy() {
		Pile pile = new Pile();
		for(Card card : cards)
			pile.add(card);
		return pile;
	}

}
