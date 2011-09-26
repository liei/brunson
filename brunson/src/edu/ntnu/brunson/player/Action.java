package edu.ntnu.brunson.player;

public class Action {
	
	private Action.Type type;
	private int bet;
	
	private Action(Action.Type type, int bet){
		this.type = type;
		this.bet = bet;
	}
	
	public int getBet(){
		return bet;
	}
	
	public Action.Type getType(){
		return type;
	}
	
	public static Action fold(){
		return new Action(Type.FOLD,0);
	}
	
	public static Action raise(int bet){
		return new Action(Type.RAISE,bet);
	}
	
	public static Action bet(int bet){
		return new Action(Type.BET,bet);
	}
	
	public static Action call(){
		return new Action(Type.CALL,0);
	}
	
	public static Action check(){
		return new Action(Type.CHECK,0);
	}
	
	public static enum Type {
		BET,
		RAISE,
		CHECK,
		CALL,
		FOLD;		
	}
	
	public String toString(){
		switch(type){
		case BET: return String.format("bets %d",bet);
		case RAISE: return String.format("raises to %d",bet);
		case CHECK: return "checks";
		case CALL: return "calls";
		case FOLD: return "folds";
		default: throw new IllegalArgumentException("type can't be null");
		}
	}
}