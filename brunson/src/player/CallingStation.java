package player;

public class CallingStation extends Player {
	
	//The calling station is passive and just wants to see how his hand plays out. He chases all draws and is so passive he never gets value from his strong hands.
	
	public CallingStation(int buyin) {
		super(buyin);
		this.aggression = 10;
		this.bluffy = 10;
		this.vpip = 45;
	}

}
