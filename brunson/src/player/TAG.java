package player;

public class TAG extends Player{
	
	//This acronym is short for tight-aggressive and defines the style most professional poker players employ.
	public TAG(int buyin) {
		super(buyin);
		this.aggression = 50;
		this.bluffy = 50;
		this.vpip = 21;
	}

}
