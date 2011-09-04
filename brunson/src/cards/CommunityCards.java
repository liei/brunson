package cards;

public class CommunityCards extends Pile{
	
	private Pile flop;
	private Pile turn;
	private Pile river;
	
	public CommunityCards() {
		this.flop = new Pile();
		this.turn = new Pile();
		this.river = new Pile();		
	}

	public Pile getFlop() {
		return flop;
	}

	public void setFlop(Pile flop) {
		this.flop = flop;
	}

	public Pile getTurn() {
		return turn;
	}

	public void setTurn(Pile turn) {
		this.turn = turn;
	}

	public Pile getRiver() {
		return river;
	}

	public void setRiver(Pile river) {
		this.river = river;
	}
	
	
}
