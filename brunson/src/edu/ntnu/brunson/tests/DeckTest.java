package edu.ntnu.brunson.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ntnu.brunson.cards.*;

public class DeckTest {

	@Test
	public void testGetDeck() {
		Pile deck = Deck.fullDeck();
		assertEquals("DeckSize", 52, deck.size());
		boolean found = false;
		for(Card card : deck) {
			if(card.getSuit()==Suit.HEARTS) {
				if(card.getValue()==Value.FIVE) {
					found = true;
				}
			}
		}
		//found the 5h
		assertTrue(found);
	}

	@Test
	public void testPop() {
		Pile deck = Deck.fullDeck();
		assertNotNull(deck.pop());
		assertTrue(deck.pop() instanceof Card);
		assertEquals("DeckSize", 50, deck.size());
		
	}

}
