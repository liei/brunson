package edu.ntnu.brunson.player;
import edu.ntnu.brunson.manager.Round;
public class Context {
	
	private String hash;
	
	public Context(Round round, int players, int raises) {
		String p;
		String r;
		if(players == 1) {
			p = "one";
		}
		else if(players==2) {
			p ="two";
		}
		else p="several";
	
	if(raises == 1) {
		r = "one";
	}
	else if(raises==2) {
		r ="two";
	}
	else 
		r="several";
	
	hash = p + r + round.toString();
	}

	public String getHash() {

		return hash;
	}
}
