package player;
import manager.Round;
import cards.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import manager.*;
public abstract class Player {
	
	private Pile hand;
	private int chips;
	private int amountWagered;
	
	private Map<Round,List<Action>> actions;
	private HandRating handRating;
		
	public Player(int buyin){
		hand = new Pile();
		chips = buyin;
		amountWagered = 0;
		actions = new EnumMap<Round,List<Action>>(Round.class);
		//List of actions this player took pre-flop, on flop, and on river.
		actions.put(Round.PREFLOP,new ArrayList<Action>());
		actions.put(Round.FLOP,new ArrayList<Action>());
		actions.put(Round.TURN,new ArrayList<Action>());
		actions.put(Round.RIVER,new ArrayList<Action>());
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
	
	public final int bet(int bet){
		int diff = bet - amountWagered;
		chips -= diff;
		amountWagered = bet;
		return diff;
	}
	
	public abstract Action act(Round round, Pile communityCards, int bet, int raises, int pot);
	
	public List<Action> getActions(Round round) {
		return actions.get(round);
	}
	
	public void addAction(Round round,Action action) {
		List<Action> list = actions.get(round);
		if(list == null)
			throw new NullPointerException();
		list.add(action);
	}
	
	public void updateAmountWagered(int chips) {
		this.amountWagered +=chips;
	}
	
	public int getAmountWagered() {
		return this.amountWagered;
	}
	
	public void resetAmountWagered() {
		amountWagered = 0;
	}
	
	public HandRating getHandRating() {
		return handRating;
	}
	
	public void setHandRating(Pile communityCards) {
		handRating = HandRating.rate(communityCards, hand);
	}

	
}
