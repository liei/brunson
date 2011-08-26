package cards;

public class Card implements Comparable<Card>{
	
	//field
	private int value;
	private Suit suit;	
	
	//constructor
	public Card(Suit suit,int value){
		this.suit = suit;
		this.value = (1 <= value && value <= 13) ? value : -1;
	}
	
	public int getValue(){
		return value;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public String toString(){
		return String.format("%c%d",suit.pip,value);
	}

	@Override
	public int compareTo(Card o) {
		return 0;
	}

}



