package edu.ntnu.brunson.cards;

public class Card implements Comparable<Card>{
	
	private Value value;
	private Suit suit;	
	
	Card(Value value, Suit suit){
		this.value = value;
		this.suit = suit;
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

	@Deprecated
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



