package tests;

import static org.junit.Assert.*;
import cards.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testGetValue() {
		Card aceOfSpades = Deck.getCard("As");
		assertEquals("As", Value.ACE ,aceOfSpades.getValue());
		Card fiveOfDiamond = Deck.getCard("5d");
		assertEquals("5d", Value.FIVE, fiveOfDiamond.getValue() );
	}

	@Test
	public void testGetSuit() {
		Card aceOfSpades = Deck.getCard("As");
		assertEquals("As", Suit.SPADES, aceOfSpades.getSuit());
		Card fiveOfDiamond = Deck.getCard("5d");
		assertEquals("5d", Suit.DIAMONDS,  fiveOfDiamond.getSuit());
	}

	@Test
	public void testToString() {
		Card aceOfSpades = Deck.getCard("As");
		assertEquals("As", "As", aceOfSpades.toString());
		Card fiveOfDiamond = Deck.getCard("5d");
		assertEquals("5d", "5d",  fiveOfDiamond.toString());
	}
}
