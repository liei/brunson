package edu.ntnu.brunson.cards;

public enum Value {
	
	TWO('2',"Deuce","Deuces"),
	THREE('3',"Three","Threes"),
	FOUR('4',"Four","Fours"),
	FIVE('5',"Five","Fives"),
	SIX('6',"Six","Sixes"),
	SEVEN('7',"Seven","Sevens"),
	EIGHT('8',"Eight","Eights"),
	NINE('9',"Nine","Nines"),
	TEN('T',"Ten","Tens"),
	JACK('J',"Jack","Jacks"),
	QUEEN('Q',"Queen","Queens"),
	KING('K',"King","Kings"),
	ACE('A',"Ace","Aces");
	
	public final char pip;
	public final String singular,plural;
	
	private Value(char pip, String singular,String plural){
		this.pip = pip;
		this.singular = singular;
		this.plural = plural;
	}
	
	public String toString(){
		return Character.toString(pip);
	}
}
