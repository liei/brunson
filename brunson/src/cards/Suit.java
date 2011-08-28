package cards;

public enum Suit {
		
	SPADES("s","Spades"),
	HEARTS("h","Hearts"),
	DIAMONDS("d","Diamonds"),
	CLUBS("c","Clubs");

	public final String pip;
	public final String name;
	
	private Suit(String pip,String name){
		this.pip = pip;
		this.name = name;
	}
	
	public String toString(){
		return pip;
	}
	
	public static Suit getSuitFromPip(char s) {
			for(Suit suit : Suit.values()){
				if(s == suit.pip.toCharArray()[0]) {
					return suit;
				}
			}
			throw new IllegalArgumentException();	
		}
}
