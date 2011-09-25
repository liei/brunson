package edu.ntnu.brunson.player;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.ws.WebServiceException;

import edu.ntnu.brunson.preflop.PreFlopTable;
public abstract class AIPlayer extends Player{

	//Value from 0 - 100 indicating how aggressive the player is.
	protected int aggression;
	
	//VP$IP, short for voluntarily put money in pot. Standard metric indicating how picky the player is pre-flop.
	//A vpip above 30 is generally regarded as too loose in 6-max games, the player plays 30% of the deck.
	//A vpip below 15 is extremely tight, or 'nitty'.
	protected int vpip;
	protected static PreFlopTable pft = null;
	
	//Value from 0-100 indicating how willing the player is to run big bluffs.
	protected int bluffy;

	public AIPlayer(String name,int buyin,int aggression,int vpip,int bluffy){
		super(name, buyin);
		this.aggression = aggression;
		this.vpip = vpip;
		this.bluffy = bluffy;
	}
	
	public static void loadPFT(int players, String filepath) throws ParseException, IOException  {
			pft = PreFlopTable.load(filepath, players);
	}
	
	
}
