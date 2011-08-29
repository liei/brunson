package manager;

import java.util.Collections;

import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		Deck deck = new Deck();
		Pile pile = new Pile();
		int i = 5;
		while(i-- > 0){
			pile.add(deck.pop());
		}
		System.out.println(pile);
		pile.sort();
		System.out.println(pile);
	}
}
