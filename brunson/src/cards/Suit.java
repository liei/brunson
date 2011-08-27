package cards;

public enum Suit {
		
	SPADES("S","Spades"),
	HEARTS("H","Hearts"),
	DIAMONDS("D","Diamonds"),
	CLUBS("C","Clubs");

	public final String pip;
	public final String name;
	
	private Suit(String pip,String name){
		this.pip = pip;
		this.name = name;
	}
	
	public String toString(){
		return pip;
	}
}