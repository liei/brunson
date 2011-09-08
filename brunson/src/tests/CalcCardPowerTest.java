package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import cards.*;
import manager.*;

public class CalcCardPowerTest {

	@Test
	public void testCalcCardPower() {

		Pile pile;
		int[] powerRating;
		
		//high cards
		pile = Deck.getPile("5sKdAcJd9h7c2c");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(1, powerRating[0]);
		assertEquals(14, powerRating[1]);
		assertEquals(13, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		assertEquals(7, powerRating[5]);
		pile.clear();	
		
		//high cards 6, pile
		pile = Deck.getPile("5sKdAcJd9h7c");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(1, powerRating[0]);
		assertEquals(14, powerRating[1]);
		assertEquals(13, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		assertEquals(7, powerRating[5]);
		pile.clear();	
		
		//high cards, 5 pile
		pile = Deck.getPile("KdAcJd9h7c");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(1, powerRating[0]);
		assertEquals(14, powerRating[1]);
		assertEquals(13, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		assertEquals(7, powerRating[5]);
		pile.clear();
		
		//pair of queens
		pile = Deck.getPile("Qs7d9c8h6dAhQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
		pile.clear();
		
		//pair of queens, 6 pile
		pile = Deck.getPile("Qs7d9c8hAhQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
		pile.clear();
		
		//pair of queens, 5 pile

		pile = Deck.getPile("Qs9c8hAhQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
		pile.clear();
		
		//pair of 5s
		pile = Deck.getPile("5s5dAcJd9h7c");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		pile.clear();	
		
		//trips
		pile = Deck.getPile("5s5d5cJd9h7cAc");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//trips, 6 pile
		pile = Deck.getPile("5s5d5cJd9hAc");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//trips, 5 pile
		pile = Deck.getPile("5s5d5cJdAc");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//straight, 9 high
		pile = Deck.getPile("5s7d9c8h6dAhQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//straight, 9 high, 6 pile
		pile = Deck.getPile("5s7d9c8h6dAh");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//straight, 9 high, 5 pile
		pile = Deck.getPile("5s7d9c8h6d");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
				
		//straight, the wheel
		pile = Deck.getPile("5s4d3c2hAd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(5, powerRating[1]);
		pile.clear();
		
		//flush, 9 high
		pile = Deck.getPile("2s7s9s4s3sAhQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//flush, 9 high 5 pile
		pile = Deck.getPile("2s7s9s4s3s");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//flush, 9 high 6 pile
		pile = Deck.getPile("2s7s9s4s3s");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//Boat,  99922
		pile = Deck.getPile("9s9d9c2s3s2hQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Boat,  99922, 6 pile
		pile = Deck.getPile("9s9d9c2s2hQd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Boat,  99922, 5 pile
		pile = Deck.getPile("9s9d9c2s2h");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker

		pile = Deck.getPile("9s9d9c9hAh2h5h");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 6 pile
		pile = Deck.getPile("9s9d9c9hAhAd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 5 pile
		pile = Deck.getPile("9s9d9c9hAh");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 7 pile
		pile = Deck.getPile("9s9d9c9hAhAdAc");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//5 high straight flush
		pile = Deck.getPile("9s2d5d6h3dAd4d");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(5, powerRating[1]);
		pile.clear();
		
		//5 high straight flush, 6 pile
		pile = Deck.getPile("9s8s5s6s7sAd");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//5 high straight flush, 5 pile
		pile = Deck.getPile("JsTsAsKsQs");
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(14, powerRating[1]);
		pile.clear();
	
	}

	@Test
	public void testRandomHands(){
		//calculate hand rating for 1000 random hands
		for(int i = 0; i < 1000; i++){
			Pile deck = Deck.fullDeck();
			deck.shuffle();
			Utility.calcCardPower(deck.deal(5));
		}
	}
}
