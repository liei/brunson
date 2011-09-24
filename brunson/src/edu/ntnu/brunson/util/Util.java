package edu.ntnu.brunson.util;

import java.util.Random;

public class Util {

	public static final Random RNG = new Random();
	
	/**
	 * returns a true chance % of the time, uses Util.RNG to get randomness.
	 * @param chance, the chance of returning true, [0,100]
	 *        if chance is < 0 or > 100 throws an IllegalArgumentException()
	 * @return will be true chance %
	 *         always returns false if chance == 0
	 *         always returns true if chance == 100
	 */
	public static boolean randomBoolean(int chance) {
		return RNG.nextInt(100) + 1 <= chance;
	}
	
	public float potOdds(int pot, int bet) {
		float fPot = pot;
		float fBet = bet;
		return fBet / (fPot + fBet);
	}
	
	
}
