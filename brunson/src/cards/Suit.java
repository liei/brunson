package cards;

public enum Suit {
		
	CLUBS('C',"Clubs"),
	HEARTS('H',"Hearts"),
	SPADES('S',"Spades"),
	DIAMONDS('D',"Diamonds");

	public final char pip;
	public final String name;
	
	private Suit(char pip,String name){
		this.pip = pip;
		this.name = name;
	}
	
	public String toString(){
		return Character.toString(pip);
	}
	
	public static Suit getSuitFromPip(char s) {
		for(Suit suit : Suit.values()){
			if(s == suit.pip) {
				return suit;
			}
		}	
		throw new IllegalArgumentException();	
	}
}
