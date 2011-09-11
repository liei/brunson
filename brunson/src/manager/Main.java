package manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cards.Deck;
import cards.Pile;

public class Main {

	private Main(){};
	
	public static void main(String[] args) {
		
		Random r = new Random();
		
		List<Pile> piles = new LinkedList<Pile>();
		for(int i = 0; i < 1000000; i++){
			Pile deck = Deck.fullDeck();
			deck.shuffle();
			piles.add(deck.deal(r.nextInt(3) + 5));
		}
		
		System.out.println("**Start HandRating**");
		for(Pile pile : piles){
			try{
				HandRating.rate(pile);
			} catch (Exception e){
				System.out.println(pile);
				e.printStackTrace();
			}
		}
	}
}
