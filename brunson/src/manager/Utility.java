package manager;

import cards.*;

import java.util.*;

public class Utility {
	
	private Utility(){
		
	}
	
	public static int[] calcCardPower(Pile pile) {
		int[] powerRating = new int[6];
		
		
		//Pair
		
		if(onePair(pile)) {
			return onePairRating(pile);
		}
		//Straight flush
		if(isStraight(pile) && isFlush(pile)) {
			powerRating = flushRating(pile);
			powerRating[0] = 9;
			return powerRating;
		}
		return powerRating;
		
	}
	
	private int[] highCardRating(Pile pile) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < pile.getCardCount(); i++) {
			values.add(pile.getCard(i).getIntValue());
		}
		Collections.sort(values);
		Collections.reverse(values);
		int[] v = new int[6];
		v[0] = 1;
		for(int i = 1; i < 6; i++) {
			v[i] = values.get(i);
		}
		return v;
		
	}
	
	private static int[] valueSort(Pile pile) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 0; i < pile.getCardCount(); i++) {
			values.add(pile.getCard(i).getIntValue());
		}
		Collections.sort(values);
		Collections.reverse(values);
		int[] v = new int[pile.getCardCount()];
		for(int i = 0; i < v.length; i++) {
			v[i] = values.get(i);
		}
		return v;
		
	}
	
	private static boolean isFlush(Pile pile) {
		int c = 0;
		int h = 0;
		int s = 0;
		int d = 0;
		
		for(int i = 0; i < pile.getCardCount(); i++) {
			switch(pile.getCard(i).getSuit()) {
			case CLUBS: c++;break;
			case HEARTS: h++;break;
			case SPADES: s++;break;
			case DIAMONDS: c++; break;
			}
		}
		if(s > 4 || c > 4 || h > 4 || d > 4) {
			return true;
		}
		return false;
	}
	
	private static int[] flushRating(Pile pile) {
		int[] rating = new int[6];
		int[] values = valueSort(pile);
		rating[0] = 6;
		for(int i=1; i<6; i++) {
			rating[i] = values[i-1];
		}
		return rating;
	}
	
	private static boolean isStraight(Pile pile) {
		int straighteningCards = pile.getCardCount();
		int[] values = valueSort(pile);
		for(int i = 0; i < pile.getCardCount() - 1; i++) {
			if(!(values[i] == values [i+1] - 1 || values[i] == values[i+1])) {
				straighteningCards--;
			}
			if(straighteningCards < 5) {
				return false;
			}
		}
		return true;
	}
	
	private static int[] straightRating(Pile pile) {
		int[] rating = new int[5];
		int[] values = valueSort(pile);
		rating[0] = 6;
		for(int i=1; i<6; i++) {
			rating[i] = values[i-1];
		}
		return rating;
	}
	
	private static boolean onePair(Pile pile){
		int[] values = valueSort(pile);
		int equalCards = 0;
		for(int i = 0; i < values.length - 1; i++) {
			
			if(values[i] == values[i+1]) {
				equalCards++;
			}
		}
		if(equalCards > 1) {
			return false;
		}
		return true;
	}
	
	private static int[] onePairRating(Pile pile) {
		int[] rating = new int[5];
		rating[0] = 2;
		int[] values = valueSort(pile);
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] == values[i+1]) {
				rating[1] = values[i];
				for(int j = 0; j < rating.length - 2; j++) {
					if(j==i) {
						continue;
					}
					rating[2 + j] = values[j];
				}					
			}
		}
		return rating;
	}
	
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}
	
	
}
