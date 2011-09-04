package player;
import manager.Round;
import cards.*;

public abstract class Player {
	
	private Pile hand;
	private int chips;
		
	public Player(int buyin){
		this.hand = new Pile();
		chips = buyin;
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Pile getHand() {
		return hand;
	}
	
	public int getStackSize() {
		return chips;
	}
	
	public void updateStack(int delta) {
		chips += delta;
	}
	
	public abstract Action act(Round round, Pile communityCards, int bet, int raises, int pot);

}
