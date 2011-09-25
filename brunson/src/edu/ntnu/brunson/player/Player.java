package edu.ntnu.brunson.player;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import edu.ntnu.brunson.cards.*;
import edu.ntnu.brunson.manager.*;

public abstract class Player {
	
	private Pile hand;
	private int chips;
	private int amountWagered;
	private String name;
	
	private Map<Round,List<Action>> actions;
	private HandRating handRating;
		
	public Player(String name, int buyin){
		hand = new Pile();
		chips = buyin;
		amountWagered = 0;
		actions = new EnumMap<Round,List<Action>>(Round.class);
		//List of actions this player took pre-flop, on flop, and on river.
		actions.put(Round.PREFLOP,new ArrayList<Action>());
		actions.put(Round.FLOP,new ArrayList<Action>());
		actions.put(Round.TURN,new ArrayList<Action>());
		actions.put(Round.RIVER,new ArrayList<Action>());
		this.name = name;
	}
	
	public void addCard(Card card){
		hand.add(card);
	}
	
	public void addCards(Pile pile) {
		hand.add(pile);
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
	
	public abstract Action act(Round round, Pile communityCards, int bet, int raises, int pot, int players);
	
	public List<Action> getActions(Round round) {
		return actions.get(round);
	}
	
	public void addAction(Round round,Action action) {
//		writeToHH(action);
		List<Action> list = actions.get(round);
		if(list == null)
			throw new NullPointerException();
		list.add(action);
	}
	
	public void updateAmountWagered(int chips) {
		this.amountWagered += chips;
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


	public void addChips(int pot) {
		// TODO Auto-generated method stub
	}
	
	public String getName() {
		return name;
	}
	
//	private void writeToHH(Action action) {
//		switch(action.getType()) {
//		case FOLD:	Output.addToHH(name + " folds."); return;
//		case CALL:	Output.addToHH(name + " calls."); return;
//		case BET:	Output.addToHH(name + " bets " + java.lang.Integer.toString(action.getBet()) + "."); return;
//		case RAISE:	Output.addToHH(name + " raises to " + java.lang.Integer.toString(action.getBet()) + "."); return;
//		default: throw new RuntimeException("This shouldn't happen!");
//		}
//	}
	
	public String toString(){
		return String.format("%s ($%d)",name,chips); 
	}	
}
