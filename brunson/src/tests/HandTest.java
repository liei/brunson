package tests;

import static org.junit.Assert.*;
import cards.*;

import org.junit.Test;

public class HandTest {

	@Test
	public void testGetCardCount() {
		Hand hand = new Hand(new Card[] {new Card(Suit.SPADES, Value.FOUR), new Card(Suit.CLUBS, Value.KING)});
		assertEquals(2, hand.getCardCount());
	}

	@Test
	public void testGetCards() {
		Hand hand = new Hand(new Card[] {new Card(Suit.SPADES, Value.FOUR), new Card(Suit.CLUBS, Value.KING)});
		Card[] cards = hand.getCards();
		assertTrue(cards.length == 2);
		assertTrue(cards[0] instanceof Card && cards[1] instanceof Card);
	}

}
