package player;

import java.util.Random;

public class Phase1Player extends AIPlayer{
	
	Random random = new Random();
	
	public Phase1Player(int buyin, int aggression, int vpip, int bluffy) {
		super(buyin, aggression, vpip, bluffy);
	}
	
	public int getAction() {
		return random.nextInt(3);
	}
}
