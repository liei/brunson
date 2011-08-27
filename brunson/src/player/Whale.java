package player;

public class Whale extends Player {
	
	//The whale plays over half the deck, this is not a winning strategy.
		
		public Whale(int buyin) {
			super(buyin);
			this.aggression = 50;
			this.bluffy = 25;
			this.vpip = 65;
		}

}

