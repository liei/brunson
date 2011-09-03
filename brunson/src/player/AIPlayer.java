package player;

public abstract class AIPlayer extends Player{

	//Value from 0 - 100 indicating how aggressive the player is.
	protected int aggression;
	
	//VP$IP, short for voluntarily put money in pot. Standard metric indicating how picky the player is pre-flop.
	//A vpip above 30 is generally regarded as too loose in 6-max games, the player plays 30% of the deck.
	//A vpip below 15 is extremely tight, or 'nitty'.
	protected int vpip;
	
	//Value from 0-100 indicating how willing the player is to run big bluffs.
	protected int bluffy;

	public AIPlayer(int buyin,int aggression,int vpip,int bluffy){
		super(buyin);
		this.aggression = aggression;
		this.vpip = vpip;
		this.bluffy = bluffy;
	}
}
