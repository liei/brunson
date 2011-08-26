package cards;

public class Card implements Comparable<Card>{
	
	//field
	private int face;
	private Suit suit;	
	
	//constructor
	public Card(String suit,int face){
		this.suit = new Suit(suit.charAt(0));
		this.face = (1 <= face && face <= 13) ? face : -1;
	}
	
	public int getFace(){
		return face;
	}
	
	public String getSuit(){
		return suit.getSuit();
	}
	
	public String toString(){
		return suit.getSuit() + face;
	}
	
	public int compareTo(Card that){
		return this.cardValueAceHigh() - that.cardValueAceHigh();
	}
	
	public int cardValueAceHigh() {
		return (getFace()+11)%13 * 4 + suit.value();
	}
	
	public int cardValueAceLow(){
		return (getFace()-1)*4 + suit.value();
	}
}

class Suit {
	
	public static final char SPADES 	= 'S';
	public static final char HEARTS 	= 'H';
	public static final char DIAMONDS 	= 'D';
	public static final char CLUBS 		= 'C';
	public static final char[] SUITS = {'C','D','H','S'};
	
	//field
	private char suit;
	
	//constructor
	public Suit(char suit){
		this.suit = isLegalSuit(suit) ? suit : this.suit; 
	}

	public String getSuit(){
		return Character.toString(suit);
	}
	
	public int value(){
		for(int i = 0;i < SUITS.length;i++){
			if(SUITS[i] == suit) return i;
		}
		return -1;
	}
	
	private boolean isLegalSuit(char suit) {
		return (suit == SPADES || suit == HEARTS || suit == DIAMONDS || suit == CLUBS);
	}
}

