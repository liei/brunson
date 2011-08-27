package player;
import cards.*;

public abstract class Player {
	
	private Hand hand;
	private int stack;
	
	//Value from 0 - 100 indicating how aggressive the player is.
	protected int aggression;
	
	//VP$IP, short for voluntarily put money in put. Standard metric indicating how picky the player is pre-flop.
	//A vpip above 30 is generally regarded as too loose in 6-max games, the player plays 30% of the deck.
	//A vpip below 15 is extremely tight, or 'nitty'.
	protected int vpip;
	
	//Value from 0-100 indicating how willing the player is to run big bluffs.
	protected int bluffy;
	
	protected Player(int buyin) {
		this.stack = buyin;
		
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	//Returns the player's chosen action. 0 for fold, 1 for call and 2 for raise.
	public int Action() {
		//TODO Add some functionality here.
		return 0;
	}

}
