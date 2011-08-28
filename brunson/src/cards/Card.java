package cards;

public class Card implements Comparable<Card>{
	
	//field
	private Value value;
	private Suit suit;	
	
	//constructor
	public Card(Suit suit,Value value){
		this.suit = suit;
		this.value = value;
	}
	
	public Value getValue(){
		return value;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public String toString(){
		return value.pip + suit.pip;
	}

	@Override
	public int compareTo(Card o) {
		return 0;
	}

}



