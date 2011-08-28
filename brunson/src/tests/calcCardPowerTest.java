package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import cards.*;
import manager.*;

import cards.Suit;
import cards.Value;

public class calcCardPowerTest {

	@Test
	public void testCalcCardPower() {
		
		
		Pile pile = new Pile();
		int[] powerRating;
		
		//high cards
		pile.add(new Card("5s"));
		pile.add(new Card("Kd"));
		pile.add(new Card("Ac"));
		pile.add(new Card("Jd"));
		pile.add(new Card("9h"));
		pile.add(new Card("7c"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(1, powerRating[0]);
		assertEquals(14, powerRating[1]);
		assertEquals(13, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		pile.clear();	
		
		pile.add(new Card(12, 's'));
		pile.add(new Card(7, 'd'));
		pile.add(new Card(9, 'c'));
		pile.add(new Card(8, 'h'));
		pile.add(new Card(6, 'd'));
		pile.add(new Card(14, 'h'));
		pile.add(new Card(12, 'd'));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(9, powerRating[1]);
		
		//pair of 5s
		pile.add(new Card("5s"));
		pile.add(new Card("5d"));
		pile.add(new Card("Ac"));
		pile.add(new Card("Jd"));
		pile.add(new Card("9h"));
		pile.add(new Card("7c"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(13, powerRating[3]);
		assertEquals(9, powerRating[4]);
		pile.clear();			
		
		//straight, 9 high
		pile.add(new Card(5, 's'));
		pile.add(new Card(7, 'd'));
		pile.add(new Card(9, 'c'));
		pile.add(new Card(8, 'h'));
		pile.add(new Card(6, 'd'));
		pile.add(new Card(14, 'h'));
		pile.add(new Card(12, 'd'));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();

	}

}
