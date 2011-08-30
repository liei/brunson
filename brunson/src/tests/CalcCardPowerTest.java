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
		pile.add(new Card("2c"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(1, powerRating[0]);
		assertEquals(14, powerRating[1]);
		assertEquals(13, powerRating[2]);
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		assertEquals(7, powerRating[5]);
		pile.clear();	
		
		//high cards 6, pile
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
		assertEquals(7, powerRating[5]);
		pile.clear();	
		
		//high cards, 5 pile
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
		assertEquals(7, powerRating[5]);
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
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
		pile.clear();
		
		//pair of queens, 6 pile
		pile.add(new Card("Qs"));
		pile.add(new Card("7d"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
		pile.clear();
		
		//pair of queens, 5 pile
		pile.add(new Card("Qs"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(2, powerRating[0]);
		assertEquals(12, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(9, powerRating[3]);
		assertEquals(8, powerRating[4]);
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
		assertEquals(11, powerRating[3]);
		assertEquals(9, powerRating[4]);
		pile.clear();	
		
		//trips
		pile.add(new Card("5s"));
		pile.add(new Card("5d"));
		pile.add(new Card("5c"));
		pile.add(new Card("Jd"));
		pile.add(new Card("9h"));
		pile.add(new Card("7c"));
		pile.add(new Card("Ac"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//trips, 6 pile
		pile.add(new Card("5s"));
		pile.add(new Card("5d"));
		pile.add(new Card("5c"));
		pile.add(new Card("Jd"));
		pile.add(new Card("9h"));
		pile.add(new Card("Ac"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//trips, 5 pile
		pile.add(new Card("5s"));
		pile.add(new Card("5d"));
		pile.add(new Card("5c"));
		pile.add(new Card("Jd"));
		pile.add(new Card("Ac"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(4, powerRating[0]);
		assertEquals(5, powerRating[1]);
		assertEquals(14, powerRating[2]);
		assertEquals(11, powerRating[3]);
		pile.clear();	
		
		//straight, 9 high
		pile.add(new Card("5s"));
		pile.add(new Card("7d"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("6d"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//straight, 9 high, 6 pile
		pile.add(new Card("5s"));
		pile.add(new Card("7d"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("6d"));
		pile.add(new Card("Ah"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//straight, 9 high, 5 pile
		pile.add(new Card("5s"));
		pile.add(new Card("7d"));
		pile.add(new Card("9c"));
		pile.add(new Card("8h"));
		pile.add(new Card("6d"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
				
		//straight, the wheel
		pile.add(new Card("5s"));
		pile.add(new Card("4d"));
		pile.add(new Card("3c"));
		pile.add(new Card("2h"));
		pile.add(new Card("Ad"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(5, powerRating[0]);
		assertEquals(5, powerRating[1]);
		pile.clear();
		
		//flush, 9 high
		pile.add(new Card("2s"));
		pile.add(new Card("7s"));
		pile.add(new Card("9s"));
		pile.add(new Card("4s"));
		pile.add(new Card("3s"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//flush, 9 high 5 pile
		pile.add(new Card("2s"));
		pile.add(new Card("7s"));
		pile.add(new Card("9s"));
		pile.add(new Card("4s"));
		pile.add(new Card("3s"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//flush, 9 high 6 pile
		pile.add(new Card("2s"));
		pile.add(new Card("7s"));
		pile.add(new Card("9s"));
		pile.add(new Card("4s"));
		pile.add(new Card("3s"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(6, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//Boat,  99922
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("2s"));
		pile.add(new Card("3s"));
		pile.add(new Card("2h"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Boat,  99922, 6 pile
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("2s"));
		pile.add(new Card("2h"));
		pile.add(new Card("Qd"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Boat,  99922, 5 pile
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("2s"));
		pile.add(new Card("2h"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(7, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(2, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("9h"));
		pile.add(new Card("Ah"));
		pile.add(new Card("2h"));
		pile.add(new Card("5h"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 6 pile
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("9h"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Ad"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 5 pile
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("9h"));
		pile.add(new Card("Ah"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//Quad 9s, A kicker, 7 pile
		pile.add(new Card("9s"));
		pile.add(new Card("9d"));
		pile.add(new Card("9c"));
		pile.add(new Card("9h"));
		pile.add(new Card("Ah"));
		pile.add(new Card("Ad"));
		pile.add(new Card("Ac"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(8, powerRating[0]);
		assertEquals(9, powerRating[1]);
		assertEquals(14, powerRating[2]);
		pile.clear();
		
		//5 high straight flush
		pile.add(new Card("9s"));
		pile.add(new Card("2d"));
		pile.add(new Card("5d"));
		pile.add(new Card("6h"));
		pile.add(new Card("3d"));
		pile.add(new Card("Ad"));
		pile.add(new Card("4d"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(5, powerRating[1]);
		pile.clear();
		
		//5 high straight flush, 6 pile
		pile.add(new Card("9s"));
		pile.add(new Card("8s"));
		pile.add(new Card("5s"));
		pile.add(new Card("6s"));
		pile.add(new Card("7s"));
		pile.add(new Card("Ad"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(9, powerRating[1]);
		pile.clear();
		
		//5 high straight flush, 5 pile
		pile.add(new Card("Js"));
		pile.add(new Card("Ts"));
		pile.add(new Card("As"));
		pile.add(new Card("Ks"));
		pile.add(new Card("Qs"));
		powerRating = Utility.calcCardPower(pile);
		assertEquals(9, powerRating[0]);
		assertEquals(14, powerRating[1]);
		pile.clear();
	
	}

}
