package edu.ntnu.brunson.tests;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.ntnu.brunson.cards.*;
import edu.ntnu.brunson.manager.Round;
import edu.ntnu.brunson.player.Phase1Player;
import edu.ntnu.brunson.player.Action.Type;


public class BasicPlayerTest {

	//TODO: Add some more tests.
	
	@Test
	public void testAction() {
		Phase1Player player = new Phase1Player(100, 30, 25, 25);
		Pile communityCards = Deck.getPile("As9h2d");
		player.addCard(Deck.getCard("Ac"));
		player.addCard(Deck.getCard("5d"));
		//Player has 1 pair on flop and should call!
		assertTrue(player.act(Round.FLOP, communityCards, 4, 0, 6, null).getType() == Type.CALL);
		
		//Player has a set and should raise the flop.
		player.clearHand();
		player.addCard(Deck.getCard("Ac"));
		player.addCard(Deck.getCard("As"));
		assertTrue(player.act(Round.FLOP, communityCards, 4, 0, 6, null).getType() == Type.RAISE);
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
