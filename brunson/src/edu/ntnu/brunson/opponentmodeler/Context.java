package edu.ntnu.brunson.opponentmodeler;

import edu.ntnu.brunson.manager.Round;

public class Context {
	
	private Round round;
	private Amount players;
	private Amount raises;
	
	public Context(Round round,int numPlayers, int numRaises) {
		this.round = round;
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
			if(numRaises <= 0)
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
		return String.format("ROUND   PLAYERS RAISES");
	}
	
	public String toString(){
		return String.format("%7s %4s    %4",round,players,raises);
	}
	
	static enum Amount {
		NONE,ONE,FEW,MANY;
	}

}
