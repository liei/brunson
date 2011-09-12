package edu.ntnu.no.brunson.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ntnu.brunson.cards.*;

public class CommunityCardTest {

	CommunityCards CC = new CommunityCards();
	Pile flop = Deck.getPile("TsAs3c");
	Pile turn = Deck.getPile("2h");
	Pile river= Deck.getPile("7c");
	@Test
	public void testGetFlop() {
		assertTrue(flop.size() == 3);
		assertTrue(flop.getCard(0).toString().compareTo("Ts") == 0);
		assertTrue(flop.getCard(1).toString().compareTo("As") == 0);
		assertTrue(flop.getCard(2).toString().compareTo("3c") == 0);
	}

	@Test
	public void testGetTurn() {
		assertTrue(turn.size() == 1);
		assertTrue(turn.getCard(0).toString().compareTo("2h") == 0);
	}
	
	@Test
	public void testGetRiver() {
		assertTrue(river.size() == 1);
		assertTrue(river.getCard(0).toString().compareTo("7c") == 0);
	}

}
