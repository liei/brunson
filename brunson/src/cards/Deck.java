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

	public static Card getCard(Value value,Suit suit){
		return getCard(value.toString() + suit.toString());			
	}

	public static Card getCard(String key){
		return cardMap.get(key);
	}
}
