package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import cards.Card;
import cards.Pile;

public class PileUtil{
	
	private PileUtil(){}

	public static Iterable<Pile> combinations(Pile pile,int n){
		return new LazyPileCombinations(pile, n);
	}
	
	private static class LazyPileCombinations implements Iterator<Pile>,Iterable<Pile>{
		
		private Card[] org;
		private Card[] comb;
		private int[] indexes;
	
		boolean hasNext = true;
	
		private LazyPileCombinations(Pile pile,int length){
			org = new Card[pile.size()];
			for (int i = 0; i < pile.size(); i++)
				org[i] = pile.getCard(i);
			comb = new Card[length];
			
			indexes = new int[length];
			for(int i = 0; i < indexes.length; i++){
				comb[i] = org[i];
				indexes[i] = i;
			}
		}
	
		public boolean hasNext() {
			return hasNext;
		}
	
		public Pile next() {
			if(!hasNext)
				throw new NoSuchElementException();
			Pile next = new Pile(comb);
			hasNext = false;
			OUTER_LOOP:
			for(int i = indexes.length - 1; i >= 0; i--){
				indexes[i]++;
				if(indexes[i] < org.length){
					comb[i] = org[indexes[i]];
					for(int j = i + 1; j < indexes.length; j++){
						indexes[j] = indexes[j-1] + 1;
						if(indexes[j] >= org.length)
							continue OUTER_LOOP;
						comb[j] = org[indexes[j]]; 
					}
					hasNext = true;
					break;
				}
			}
			return next;
		}
	
		public void remove() {
			throw new UnsupportedOperationException();
		}
	
		public Iterator<Pile> iterator() {
			return this;
		}
	}
}

