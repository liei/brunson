package cards;

public enum Suit {
		
	SPADES('S',"Spades"),HEARTS('H',"Hearts"),DIAMONDS('D',"Diamonds"),CLUBS('C',"Clubs");

	public final char pip;
	public final String name;
	
	private Suit(char pip,String name){
		this.pip = pip;
		this.name = name;
	}
}