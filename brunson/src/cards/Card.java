package cards;

public class Card implements Comparable<Card>{
	
	private Value value;
	private Suit suit;	
	
	public Card(Suit suit,Value value){
		this.suit = suit;
		this.value = value;
	}
	
//	public Card(int v, char s) {
//		switch(s) {
//		case 's': this.suit = Suit.SPADES;
//		case 'd': this.suit = Suit.DIAMONDS;
//		case 'h': this.suit = Suit.HEARTS;
//		case 'c': this.suit = Suit.CLUBS;
//		}
//		this.value=Value.getValueFromInt(v);
//	}
	
	public Card(String s){
		if(s.length() != 2) {
			throw new IllegalArgumentException();
		}
		value = Value.getValueFromPip(s.charAt(0));
		suit = Suit.getSuitFromPip(s.charAt(1));
	}
	
	
	public Value getValue(){
		return value;
	}
	
	public Suit getSuit(){
		return suit;
	}
	
	public String toString(){
		return String.format("%s%s",value,suit);
	}
	
	public int getIntValue() {
		for(int i = 0; i< Value.values().length; i++) {
			if(this.value == Value.values()[i]) {
				return i + 2;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public int compareTo(Card that) {
		return  that.getValue().ordinal() - this.getValue().ordinal();
	}

}



