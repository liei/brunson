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
		//Two pairs		
		else if(twoPair(pile)) {
			return twoPairRating(pile);
		}
		//Three of a kind.
		else if(trips(pile)) {
			return tripsRating(pile);
		}
		
		//Straight flush
		else if(straight(pile) && flush(pile)) {
			powerRating = flushRating(pile);
			powerRating[0] = 9;
			return powerRating;
		}
		//straight
		else if(straight(pile)) {
			return straightRating(pile);
		}
		
		//Flush
		else if(flush(pile)) {
			return flushRating(pile);
		}
		
		//Boat
		else if(boat(pile)) {
			return boatRating(pile);
		}
		
		//Quads
		else if(quads(pile)) {
			return quadsRating(pile);
		}
		return highCardRating(pile);
	}
	
	private static int[] highCardRating(Pile pile) {
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
	
	private static boolean flush(Pile pile) {
		int s = 0;
		int c = 0;
		int d = 0;
		int h = 0;
		char suit;
		int[] powerRating = new int[6];
		
		for(int i = 0; i < pile.getCardCount(); i++) {
			suit = pile.getCard(i).getSuit().toString().toCharArray()[0];
			
			switch(suit) {
			case 's': s++; continue;
			case 'd': d++; continue;
			case 'h':  h++; continue;
			case 'c': c++; continue;
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
	
	private static boolean straight(Pile pile) {
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
		if(flush(pile) || straight(pile)) {
			return false;
		}
		int[] values = valueSort(pile);
		int equalCards = 0;
		for(int i = 0; i < values.length - 1; i++) {
			
			if(values[i] == values[i+1]) {
				equalCards++;
			}
		}
		//two pairs, full house, quads
		if(equalCards > 1) {
			return false;
		}
		return equalCards > 0;
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
					rating[3 + j] = values[j];
				}					
			}
		}
		return rating;
	}
	
	public static boolean twoPair(Pile pile) {
		int[] rating = new int[5];
		rating[0] = 3;
		int[] values = valueSort(pile);
		int pairCount = 0;
		for(int i = 0; i < values.length - 2; i++) {
			//trips, boat, quads
			if(values[i] == values[i+1] && values[i]== values[i+2]) {
				return false;
			}
		}
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] == values[i+1]) {
				pairCount++;
			}
		}
		return pairCount > 1;
	}
	
	public static int[] twoPairRating(Pile pile) {
		int[] rating = new int[4];
		rating[0] = 3;
		int[] values = valueSort(pile);
		int highPair = 0;
		int lowPair = 0;
		
		//Set high pair
		for(int i = 0; i < values.length - 1; i++) {
			if(values[i] == values[i+1]) {
				rating[1] = values[i];
				highPair = i;
			}
		}
		//Set second pair
		for(int i = highPair; i < values.length - 1; i++) {
			if(values[i] == values[i+1]) {
				rating[1] = values[i];
				lowPair = i;
			}
		}
		for(int j = 0; j < values.length; j++) {
			if(j==highPair || j==highPair + 1 || j==lowPair ||j==lowPair+1) {
				continue;
			}
			rating[4] = values[j];
		}					
		return rating;
	}
	
	public static boolean trips(Pile pile) {
		int[] values = valueSort(pile);
		int tripIndex = -1 ;
		if(flush(pile) || straight(pile)) {
			return false;
		}
		for(int i = 0; i < values.length -3; i++) {
			//quads
			if(values[i] == values[i+1] && values[i]== values[i+2] && values [i] == values[i+3]) {
				return false;
			}
		}
		
		for(int i = 0; i < values.length - 2; i++) {
			//trips
			if(values[i] == values[i+1] && values[i]== values[i+2]) {
				tripIndex = i;
			}
		}
		//if we find trips we have to make sure we cannot find another pair as this would mean a full house.
		for(int i = 0; i < values.length; i++) {
			//we found trips, skip index containing this value, check for pair.
			if(tripIndex != - 1 && i != tripIndex && i != tripIndex + 1 && i != tripIndex + 2) {
				if(values[i] == values[i+1]) {
					return false;
				}
			}
		}
		return tripIndex > 0;
	}
	
	public static int[] tripsRating(Pile pile) {
		int[] values = valueSort(pile);
		int[] rating = {4, 0, 0, 0};
		int tripIndex = -1;
		for(int i = 0; i < values.length; i++) {
			if(values[i] == values[i+1]) {
				tripIndex = i;
				rating[1] = values[i];
			}
		}
		
		for(int i = 0; i < values.length; i++) {
			if( i== tripIndex || i == tripIndex + 1 || i == tripIndex +2) {
				continue;
			}
			rating[2+i] = values[i];
		}
		return rating;
	}
	
	public static boolean boat(Pile pile) {
		int[] values = valueSort(pile);
		int tripIndex = -1 ;
		if(flush(pile) || straight(pile)) {
			return false;
		}
		for(int i = 0; i < values.length -4; i++) {
			//quads
			if(values[i] == values[i+1] && values[i]== values[i+2] && values [i] == values[i+3]) {
				return false;
			}
		}
		
		for(int i = 0; i < values.length; i++) {
			//trips
			if(values[i] == values[i+1] && values[i]== values[i+2]) {
				tripIndex = i;
			}
		}
		//if we find trips we have to make sure we find another pair as this would mean a full house.
		for(int i = 0; i < values.length; i++) {
			//we found trips, skip index containing this value, check for pair.
			if(tripIndex != - 1 && i != tripIndex && i != tripIndex + 1 && i != tripIndex + 2) {
				if(values[i] == values[i+1]) {
					return true;
				}
			}
		}
		return false;		
	}
	
	public static int[] boatRating(Pile pile) {
		int[] values = valueSort(pile);
		int[] rating = {7, 0, 0};
		int tripIndex = -1;
		for(int i = 0; i < values.length -2; i++) {
			if(values[i] == values[i+1] && values[i] == values[i+2]) {
				tripIndex = i;
				rating[1] = values[i];
			}
		}
		
		for(int i = 0; i < values.length -2; i++) {
			if(i == tripIndex ||i == tripIndex + 1 ||i == tripIndex + 2) {
				continue;
			}
			if(values[i] == values[i+1]) {
				rating[2] = values[i];
			}
		}
		return rating;
	}
	
	public static boolean quads(Pile pile) {
		int[] values = valueSort(pile);
		if(flush(pile) || straight(pile)) {
			return false;
		}
		for(int i = 0; i < values.length -3; i++) {
			//quads
			if(values[i] == values[i+1] && values[i]== values[i+2] && values [i] == values[i+3]) {
				return true;
			}
		}
		return false;
	}
	
	public static int[] quadsRating(Pile pile) {
		int[] values = valueSort(pile);
		int[] rating = {7, 0, 0};
		
		for(int i = 0; i < values.length -4; i++) {
			if(values[i] == values[i+1]) {
				rating[1] = values[i];
				rating[2] = values[i+4];
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
