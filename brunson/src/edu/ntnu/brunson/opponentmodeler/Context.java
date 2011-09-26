package edu.ntnu.brunson.opponentmodeler;

import edu.ntnu.brunson.manager.Round;
import edu.ntnu.brunson.player.Action;

public class Context {
	
	private Round round;
	private String action;
	private int numPlayers;
	private Amount players;
	private Amount raises;
	
	public static void main(String[] args) {
		Context c = new Context(Round.FLOP,2,1,Action.fold());
		System.out.println(c);
	}
	
	public Context(Round round,int numPlayers, int numRaises,Action action) {
		this.round = round;
		this.action = action.getType().name();
		this.numPlayers = numPlayers;
		switch(numPlayers){
		case 2: 
			players = Amount.ONE;
			break;
		case 3:
		case 4:
			players = Amount.FEW; 
			break;
		default: 
			if(numPlayers < 2)
				throw new IllegalArgumentException("numPlayers must be >= 2");
			players = Amount.MANY;
		}
		switch(numRaises){
		case 0: 
			raises = Amount.NONE; 
			break;
		case 1:
			raises = Amount.ONE; 
			break;
		default: 
			if(numRaises < 0)
				throw new IllegalArgumentException("numRaises must be >= 0");
			raises = Amount.MANY;
		}
	}
	
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Context))
			return false;
		Context that = (Context)o;
		return this.round == that.round &&
				this.players == that.players &&
				this.raises == that.raises;
	}

	public static String getHeader(){
		return String.format("ROUND    PLAYERS  RAISES  ACTION  ");
	}
	
	public String toString(){
		return String.format("%-8s %-8s %-8s %-8s",round,players,raises,action);
	}
	
	static enum Amount {
		NONE,ONE,FEW,MANY;
	}

	public Round getRound() {
		return round;
	}

	public int getNumPlayers() {
		return numPlayers;
	}
}
