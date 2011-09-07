package player;
import manager.Round;
import cards.*;
import java.util.ArrayList;

public abstract class Player {
	
	private Pile hand;
	private int chips;
	private ArrayList<Action>[] actions;
		
	public Player(int buyin){
		this.hand = new Pile();
		chips = buyin;
		this.actions = new ArrayList[4];
		//List of actions this player took pre-flop, on flop, and on river.
		this.actions[0] = new ArrayList<Action>();
		this.actions[1] = new ArrayList<Action>();
		this.actions[2] = new ArrayList<Action>();
		this.actions[3] = new ArrayList<Action>();
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
	
	public void clearHand() {
		hand.clear();
	}
	
	public abstract Action act(Round round, Pile communityCards, int bet, int raises, int pot);
	
	public ArrayList<Action> getPreFlopActions() {
		return this.actions[0];
	}

	public ArrayList<Action> getFlopActions() {
		return this.actions[1];
	}
	public ArrayList<Action> getTurnActions() {
		return this.actions[2];
	}
	public ArrayList<Action> getRiverActions() {
		return this.actions[3];
	}
}
