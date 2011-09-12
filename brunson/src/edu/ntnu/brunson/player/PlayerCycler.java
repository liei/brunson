package edu.ntnu.brunson.player;

import java.util.Iterator;
import java.util.List;


public class PlayerCycler implements Iterator<Player>,Iterable<Player>{

	public static PlayerCycler cycler(List<Player> list, int startIndex){
		return new PlayerCycler(list,startIndex);
	}

	private List<Player> list;
	int index,start;
	boolean hasCurrent;
	
	public PlayerCycler(List<Player> list,int startIndex){
		this.list = list;
		index = start = startIndex;
		hasCurrent = false;
	}

	public List<Player> list(){
		return list;
	}
	
	public boolean hasNext() {
		return list.size() > 1;
	}

	public Player next() {
		index = (index + 1) % list.size();
		hasCurrent = true;
		return  list.get(index);
	}
	
	public void remove() {
		if(!hasCurrent)
			throw new IllegalStateException();
		if(index <= start)
			start--;
		list.remove(index--);
		hasCurrent = false;
	}
	
	public void reset() {
		index = start;
		hasCurrent = false;
	}

	public Iterator<Player> iterator() {
		return this;
	}
}