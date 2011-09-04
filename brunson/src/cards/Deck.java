package cards;

import java.util.*;

public class Deck {

	private static Pile deck = new Pile();
	private static Map<String,Card> cardMap = new HashMap<String,Card>();	
	
	static {
		for(Value value : Value.values()){
			for(Suit suit : Suit.values()){
				Card card = new Card(value,suit);
				deck.add(card);
				cardMap.put(card.toString(),card);
			}
		}	
	}	

	private Deck(){};

	public static Pile fullDeck(){
		return deck.copy();
	}

	public static Pile incompleteDeck(Pile... piles){
		Pile pile = deck.copy();
		pile.remove(piles);
		return pile;
	}
	
	public static Card getCard(Value value,Suit suit){
		return getCard(value.toString() + suit.toString());			
	}

	public static Card getCard(String key){
		Card card = cardMap.get(key);
		if(card == null)
			throw new IllegalArgumentException(String.format("%s is not a legal card",key));
		return card;
	}
		
	public static Pile getPile(String... cards){
		Pile pile = new Pile();
		for(String card : cards)
			pile.add(getCard(card));
		return pile;
	}
	
	public static Pile getPile(String cards){
		return getPile(cards.split("(?<=\\G..)"));
	}
}
