package cards;

public enum Suit {
		
	CLUBS('c',"Clubs"),
	HEARTS('h',"Hearts"),
	SPADES('s',"Spades"),
	DIAMONDS('d',"Diamonds");

	public final char pip;
	public final String name;
	
	private Suit(char pip,String name){
		this.pip = pip;
		this.name = name;
	}
	
	public String toString(){
		return Character.toString(pip);
	}
}
