package tests;

import static org.junit.Assert.*;
import cards.*;
import org.junit.Test;

public class CommunityCardTest {

	CommunityCards CC = new CommunityCards();
	Pile flop = Pile.newPile(new String[] {"Ts", "As", "3c"});
	Pile turn = Pile.newPile(new String[] {"2h"});
	Pile river= Pile.newPile(new String[] {"7c"});
	@Test
	public void testGetFlop() {
		assertTrue(flop.getCardCount() == 3);
		assertTrue(flop.getCard(0).toString().compareTo("Ts") == 0);
		assertTrue(flop.getCard(1).toString().compareTo("As") == 0);
		assertTrue(flop.getCard(2).toString().compareTo("3c") == 0);
	}

	@Test
	public void testGetTurn() {
		assertTrue(turn.getCardCount() == 1);
		assertTrue(turn.getCard(0).toString().compareTo("2h") == 0);
	}
	
	@Test
	public void testGetRiver() {
		assertTrue(river.getCardCount() == 1);
		assertTrue(river.getCard(0).toString().compareTo("7c") == 0);
	}

}
