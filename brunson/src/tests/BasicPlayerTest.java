package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;

import player.Phase1Player;

public class BasicPlayerTest {

	//TODO: Fix the testAction() test
	
	@Test
	public void testAction() {
		Phase1Player player = new Phase1Player(100, 30, 25, 25);
		CommunityCards communityCards = new CommunityCards()
		//Player has 1 pair on flop and should call!
		assertTrue(player.act(FLOP, communityCards, bet, raises, pot));
	}

	@Test
	public void testAddCard() {
		Phase1Player player = new Phase1Player(100, 30, 25, 25);
		player.addCard(Deck.getCard("5d"));
		player.addCard(Deck.getCard("9s"));
		assertEquals(Suit.DIAMONDS,player.getHand().getCard(0).getSuit());
		assertEquals(Suit.SPADES,player.getHand().getCard(1).getSuit());
		assertEquals(Value.FIVE,player.getHand().getCard(0).getValue());
		assertEquals(Value.NINE,player.getHand().getCard(1).getValue());
	}
	
	@Test
	public void testStack() {
		Phase1Player player = new Phase1Player(100, 30, 25, 25);
		assertEquals(100, player.getStackSize());
		player.updateStack(11);
		assertEquals(111, player.getStackSize());
		player.updateStack(-21);
		assertEquals(90, player.getStackSize());
	}

}
