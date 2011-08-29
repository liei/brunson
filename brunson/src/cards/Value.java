package cards;

public enum Value {
	
	TWO('2',"Two"),
	THREE('3',"Three"),
	FOUR('4',"Four"),
	FIVE('5',"Five"),
	SIX('6',"Six"),
	SEVEN('7',"Seven"),
	EIGHT('8',"Eight"),
	NINE('9',"Nine"),
	TEN('T',"Ten"),
	JACK('J',"Jack"),
	QUEEN('Q',"Queen"),
	KING('K',"King"),
	ACE('A',"Ace");
	
	public final char pip;
	public final String name;
	
	private Value(char pip, String name){
		this.pip = pip;
		this.name = name;
	}
	
	public String toString(){
		return Character.toString(pip);
	}
	
	public static Value getValueFromInt(int v){
		return Value.values()[v+2];
	}
		
	public static Value getValueFromPip(char v) {
		for(Value value : Value.values()){
			if(v == value.pip) {
				return value;
			}
		}
		throw new IllegalArgumentException();	
	}
}
