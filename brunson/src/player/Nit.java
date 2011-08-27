package player;

public class Nit extends Player {
	
	//The Nit sits around all day and waits for a hand. He's aggressive because he almost always has a strong hand post-flop and he doesn't really need to bluff much.
	public Nit(int buyin) {
		super(buyin);
		this.aggression = 50;
		this.bluffy = 25;
		this.vpip = 16;
	}

}
