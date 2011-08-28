package player;

import java.util.Random;

public class BasicPlayer extends Player{
	
	Random random = new Random();
	
	public BasicPlayer(int buyin, int aggression, int vpip, int bluffy) {
		super(buyin, aggression, vpip, bluffy);
	}
	
	public int action() {
		return random.nextInt(3);
	}

}
