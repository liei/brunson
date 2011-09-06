package preflop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import manager.Utility;

import cards.Card;
import cards.Deck;
import cards.Pile;
import cards.Value;

public class PreFlop {

	private static Comparator<int[]> compareRating = new RatingComparator();
	
	private static Map<String,Double> table = null;
	
	private PreFlop(){
	}

	public static void main(String[] args) {
		generate(System.out,1000);
	}
	
	public static void generate(PrintStream writer,int runs){
		long start = System.currentTimeMillis();
		List<Simulation> sims = new LinkedList<Simulation>();

		for(int players = 2; players <= 10; players++){
		
			Value[] values = Value.values();
			for(int i = 0; i < values.length; i++){
				Value v1 = values[i];
			
				Pile pocket = Deck.getPile(String.format("%sc%sh",v1,v1));
				sims.add(new Simulation(pocket,players,runs));
			
				for(int j = i + 1; j < values.length; j++){
					Value v2 = values[j];

					Pile offSuit = Deck.getPile(String.format("%sc%sh",v1,v2));
					sims.add(new Simulation(offSuit,players,runs));
					

					Pile suited = Deck.getPile(String.format("%sc%sc",v1,v2));
					sims.add(new Simulation(suited,players,runs));
				}
			}
		}
		for(Simulation sim : sims){
			sim.start();
		}
		for(Simulation sim : sims){
			try {
				sim.join();
			} catch (InterruptedException e) {}
				writer.printf("%s %f%n",equivClass(sim.getHand(),sim.getPlayers()),sim.getResult());
		}
		long stop = System.currentTimeMillis();
		System.out.printf("Time: %d",(stop - start)/1000);
	}
	
	public static String equivClass(Pile hand,int players){
		if(hand.size() != 2)
			throw new IllegalArgumentException("hand must have size 2");
		Card c1 = hand.getCard(0);
		Card c2 = hand.getCard(1);
		boolean suited = c1.getSuit() == c2.getSuit();
		return String.format("%s%s%s-%d",c1.getValue(),c2.getValue(),suited ? "s" : "",players);
	}
	
	public static void load(String filepath) throws IOException{
		table = new HashMap<String,Double>();
		BufferedReader br = null; 

		br = new BufferedReader(new FileReader(filepath));
		String line;
		while((line = br.readLine()) != null){
			String[] toks = line.split(" ");
			table.put(toks[0],Double.parseDouble(toks[1]));
		}
	}
	
	public double get(Pile hand,int players){
		String eqClass = equivClass(hand,players);
		return table.get(eqClass);
	}
	
	static class Simulation extends Thread {

		private Pile hand;
		private int players;
		private int runs;
		private double wins,ties;
		
		public Simulation(Pile hand, int players,int runs){
			this.runs = runs;
			this.hand = hand;
			this.players = players;
			wins = 0;
			ties = 0;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < runs; i++){
				runHand(hand,players);
			}
		}
		
		public Pile getHand(){
			return hand;
		}
		
		public int getPlayers(){
			return players;
		}
		
		public double getResult(){
			return Math.pow((wins + ties/2) / runs,players);
		}
		
		private void runHand(Pile myHand,int players) {
			Pile deck = Deck.fullDeck();
			deck.remove(myHand);
			deck.shuffle();
			Pile community = deck.deal(3);
			boolean tie = false;
			int[] myRating = Utility.calcCardPower(myHand,community);
			for(int i = 0; i < players; i++){
				Pile theirHand = deck.deal(2);
				int[] theirRating = Utility.calcCardPower(theirHand,community);
				int result = compareRating.compare(myRating,theirRating);
				if(result  < 0){
					return;
				} else if(result == 0){
					tie = true;
				}
			}
			if(tie) {
				ties++;
			} else {
				wins++;
			}	
		}
	}
	
}
