package cards;

import java.util.ArrayList;

public class HoleCards {
	
	private Pile holeCards;
	
	public HoleCards(Pile pile) {
		this.holeCards = pile;
		
	}
	
	public HoleCards(Card card, Card card2) {
		this.holeCards = new Pile();
		this.holeCards.add(card);
		this.holeCards.add(card2);
	}
	
	public HoleCards() {
		
	}
	
	public Pile getHoleCards() {
		return this.holeCards;
	}
	
	public void setHoleCards(Pile cards) {
		for(Card card : cards) {
			this.holeCards.add(card);
		}
	}
	public void addHoleCard(Card card) {
		this.holeCards.add(card);
	}

}
