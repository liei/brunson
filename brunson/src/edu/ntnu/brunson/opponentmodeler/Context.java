package edu.ntnu.brunson.opponentmodeler;

import edu.ntnu.brunson.manager.Round;
import edu.ntnu.brunson.player.Action;

public class Context {
	
	private Round round;
	private Action action;
	private Amount players;
	private Amount raises;
	
	public Context(Round round,int numPlayers, int numRaises,Action action) {
		this.round = round;
		this.action = action;
		switch(numPlayers){
		case 1: 
			players = Amount.ONE; 
			break;
		case 2:
		case 3: 
			players = Amount.FEW; 
			break;
		default: 
			if(numPlayers <= 0)
				throw new IllegalArgumentException("numPlayers must be >= 0");
			players = Amount.MANY;
		}
		switch(numRaises){
		case 1: 
			raises = Amount.NONE; 
			break;
		case 2:
			raises = Amount.ONE; 
			break;
		default: 
			if(numRaises < 0)
				throw new IllegalArgumentException("numRaises must be > 0");
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
		return String.format("%8s %8s %8s %8s",round,players,raises,action.getType());
	}
	
	static enum Amount {
		NONE,ONE,FEW,MANY;
	}
}
