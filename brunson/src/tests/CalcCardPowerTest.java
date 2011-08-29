package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import cards.*;
import manager.*;

public class CalcCardPowerTest {

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
		
		//pair of queens
		pile.add(new Card("Qs"));
		pile.add(new Card("7d"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("6d"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
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
		pile.add(new Card("5S"));
		pile.add(new Card("7D"));
		pile.add(new Card("9C"));
		pile.add(new Card("8H"));
		pile.add(new Card("6D"));
		pile.add(new Card("AH"));
		pile.add(new Card("QD"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
	}

}
