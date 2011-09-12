package edu.ntnu.brunson.manager;

public enum Round {
	PREFLOP(0),FLOP(3),TURN(1),RIVER(1);

	private int cards; 
	
	private Round(int cards){
		this.cards = cards;
	}
	
	public int cardsDealt() {
		return cards;
	}
}
