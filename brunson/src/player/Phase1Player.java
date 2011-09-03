package player;

import java.util.Random;
import manager.*;

public class Phase1Player extends AIPlayer{
	
	public Phase1Player(int buyin, int aggression, int vpip, int bluffy) {
		super(buyin, aggression, vpip, bluffy);
	}
	
	public Action getAction() {
		//TODO: This player needs some idea about the community cards so he can call Utility.getPowerRating(Pile (communityCards+holeCards))
		return Action.call();
	}
}
