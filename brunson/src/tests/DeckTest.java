package tests;

import static org.junit.Assert.*;
import cards.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testGetDeck() {
		Deck deck = new Deck();
		assertEquals("DeckSize", 52, deck.getDeck().size());
		assertTrue(deck.getDeck().contains(new Card(Suit.HEARTS, Value.NINE)));
	}

	@Test
	public void testPop() {
		Deck deck = new Deck();
		assertNotNull(deck.pop());
		assertTrue(deck.pop() instanceof Card);
		assertEquals("DeckSize", 50, deck.getDeck().size());
		
	}

}
