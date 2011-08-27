package cards;

public enum Value {
	
	TWO("2","Two"),
	THREE("3","Three"),
	FOUR("4","Four"),
	FIVE("5","Five"),
	SIX("6","Six"),
	SEVEN("7","Seven"),
	EIGHT("8","Eight"),
	NINE("9","Nine"),
	TEN("T","Ten"),
	JACK("J","Jack"),
	QUEEN("Q","Queen"),
	KING("K","King"),
	ACE("A","Ace");
	
	public final String pip;
	public final String name;
	
	private Value(String pip, String name){
		this.pip = pip;
		this.name = name;
	}
}
