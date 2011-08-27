package player;

public class Aggrotard extends Player{
	
	//This player is probably from Scandinavia and has to win every hand by bluffing with air. Since he bluffs so much he can fast-play all his stronger hands.
	//This leads to a high aggression factor.
	
	public Aggrotard(int buyin) {
		super(buyin);
		this.aggression = 75;
		this.bluffy = 75;
		this.vpip = 28;
	}

}
