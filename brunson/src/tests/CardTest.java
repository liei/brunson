package tests;

import static org.junit.Assert.*;
import cards.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testGetValue() {
		Card aceOfSpades = new Card(Suit.SPADES, Value.ACE );
		assertEquals("As", Value.ACE ,aceOfSpades.getValue());
		Card fiveOfDiamond = new Card(Suit.DIAMONDS, Value.FIVE);
		assertEquals("5d", Value.FIVE, fiveOfDiamond.getValue() );
	}

	@Test
	public void testGetSuit() {
		Card aceOfSpades = new Card(Suit.SPADES, Value.ACE );
		assertEquals("As", Suit.SPADES, aceOfSpades.getSuit());
		Card fiveOfDiamond = new Card(Suit.DIAMONDS, Value.FIVE);
		assertEquals("5d", Suit.DIAMONDS,  fiveOfDiamond.getSuit());
	}

	@Test
	public void testToString() {
		Card aceOfSpades = new Card(Suit.SPADES, Value.ACE );
		assertEquals("As", "As", aceOfSpades.toString());
		Card fiveOfDiamond = new Card(Suit.DIAMONDS, Value.FIVE);
		assertEquals("5d", "5d",  fiveOfDiamond.toString());
	}

}
