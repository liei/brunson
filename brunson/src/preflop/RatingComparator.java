package preflop;

import java.util.Comparator;

public class RatingComparator implements Comparator<int[]>{

	@Override
	public int compare(int[] r1, int[] r2) {
		int diff = r1[0] - r2[0];
		if(diff != 0) 
			return diff;
		int length = Math.max(r1.length,r2.length);
		for(int i = 1; i < length; i++){
			diff = r1[i] - r2[i];
			if(diff != 0) 
				return diff;
		}
		return 0;
	}
}
