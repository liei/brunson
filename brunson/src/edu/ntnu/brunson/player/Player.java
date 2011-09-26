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
	private Action lastAction;
	private Map<Round,List<Action>> actions;
	private HandRating handRating;
		
	public Player(String name, int buyin){
		hand = new Pile();
		chips = buyin;
		amountWagered = -1;
		actions = new EnumMap<Round,List<Action>>(Round.class);
		//List of actions this player took pre-flop, on flop, and on river.
		actions.put(Round.PREFLOP,new ArrayList<Action>());
		actions.put(Round.FLOP,new ArrayList<Action>());
		actions.put(Round.TURN,new ArrayList<Action>());
		actions.put(Round.RIVER,new ArrayList<Action>());
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addCard(Card card){
		hand.add(card);
		if(hand.size() > 2) {
			throw new IllegalStateException("A player cannot have more than two holecards!");
		}
	}
	
	public void addCards(Pile pile) {
		for(Card card : pile)
			addCard(card);
	}
	
	public Pile getHand() {
		return hand;
	}
	
	public int getStackSize() {
		return chips;
	}
	
	public void setLastAction(Action lastAction){
		this.lastAction = lastAction; 
	}
	
	public Action lastAction() {
		return lastAction;
	}	
	
	public void updateStack(int delta) {
		chips += delta;
	}
	
	public void clearHand() {
		hand.clear();
	}
	
	public final int bet(int bet){
		int diff = amountWagered == -1 ? bet : bet - amountWagered;
		chips -= diff;
		amountWagered = bet;
		return diff;
	}
	
	public abstract Action act(Round round, Pile communityCards, int bet, int raises, int pot, List<Player> activePlayers);
	
	public List<Action> getActions(Round round) {
		return actions.get(round);
	}
	
	public void addAction(Round round,Action action) {
		List<Action> list = actions.get(round);
		list.add(action);
	}
	
	public void updateAmountWagered(int chips) {
		this.amountWagered += chips;
	}
	
	public int getAmountWagered() {
		return amountWagered;
	}
	
	public void resetAmountWagered() {
		amountWagered = -1;
	}
	
	public HandRating getHandRating() {
		return handRating;
	}
	
	public void setHandRating(Pile communityCards) {
		handRating = HandRating.rate(communityCards, hand);
	}
		
	public String toString(){
		return String.format("%s ($%d)",name,chips); 
	}
}
