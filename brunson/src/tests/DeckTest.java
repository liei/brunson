package tests;

import static org.junit.Assert.*;
import cards.*;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testGetDeck() {
		Deck deck = new Deck();
		assertEquals("DeckSize", 52, deck.getDeck().size());
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
		Deck deck = new Deck();
		assertNotNull(deck.pop());
		assertTrue(deck.pop() instanceof Card);
		assertEquals("DeckSize", 50, deck.getDeck().size());
		
	}

}
