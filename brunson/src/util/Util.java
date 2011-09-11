package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import player.Player;

public class Util {
	
	public static void main(String[] args) {
		List<Player> list = new ArrayList<Player>();
		
		Iterator<Player> it = new PlayerCycler(list,0);
		Random r = new Random();
		while(it.hasNext()){
			Player p = it.next();
			System.out.println(p);
			if(r.nextBoolean()){
				it.remove();
				System.out.printf("removed %s%n",p);
			}
		}
	}

	public static Iterator<Player> playerCycler(List<Player> list, int index){
		return new PlayerCycler(list,index);
	}
	
	static class PlayerCycler implements Iterator<Player>,Iterable<Player>{
	
		private List<Player> list;
		int index;
		boolean hasCurrent;
		
		public PlayerCycler(List<Player> list,int index){
			this.list = list;
			this.index = index == 0 ? list.size() - 1 : index - 1;
			hasCurrent = false;
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
			list.remove(index--);
			hasCurrent = false;
		}
	
		public Iterator<Player> iterator() {
			return this;
		}
	}
}
