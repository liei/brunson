package edu.ntnu.brunson.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;


import edu.ntnu.brunson.manager.Round;
import edu.ntnu.brunson.opponentmodeler.Context;
import edu.ntnu.brunson.opponentmodeler.OpponentModeler;
import edu.ntnu.brunson.opponentmodeler.StrengthEstimate;
import edu.ntnu.brunson.player.Action;
import edu.ntnu.brunson.player.Phase1Player;
import edu.ntnu.brunson.player.Player;

public class OpponentModelerTest {

	
	@Test
	public void testOpponentModeler(){
		Player p = new Phase1Player(0, 0, 0, 0);
		OpponentModeler.init(Arrays.asList(new Player[]{p}));
		Context c = new Context(Round.FLOP, 2, 1,Action.fold());
		
		OpponentModeler.add(p, c, 0.25);
		OpponentModeler.add(p, c, 0.30);
		OpponentModeler.add(p, c, 0.90);
		StrengthEstimate se = OpponentModeler.get(p,c);
		assertEquals("TimesSeen", 3,  se.getTimesSeen());
		System.out.println(se.getStrength());
		assertTrue(se.getStrength() < 0.483334);
		assertTrue(se.getStrength() > 0.483333);
	}
	
	
}
