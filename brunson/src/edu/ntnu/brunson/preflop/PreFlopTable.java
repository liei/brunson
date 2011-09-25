package edu.ntnu.brunson.preflop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import edu.ntnu.brunson.cards.Card;
import edu.ntnu.brunson.cards.Deck;
import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.util.PileUtil;

public class PreFlopTable {
	
	private Map<String, Integer> table;
	
	private PreFlopTable(){
		table = new HashMap<String,Integer>(); 
	}
	
	public static PreFlopTable load(String filepath, int players) throws IOException, ParseException{

		BufferedReader br = null; 

		br = new BufferedReader(new FileReader(filepath));
		String line;
		int lineno = 1;
		while((line = br.readLine()) != null){
			if(line.length() > 0 && line.charAt(0) != '#'){
				try {
					String[] toks = line.split("\\|");
					if(toks.length != 2){
						for(String s : toks)
							System.out.println(s);
						throw new Exception();
					}
					if(players == Integer.parseInt(toks[0])){
						return loadTable(toks[1].split("\\s+"));
					}
				} catch (Exception e) {
					throw new ParseException(line,lineno);
				}
			}
			lineno++;
		}
		throw new IllegalArgumentException(String.format("%s does not have preflop table for %d players",filepath,players));
	}

	private static PreFlopTable loadTable(String[] hands) throws Exception {
		PreFlopTable pft = new PreFlopTable();
		for(String s : hands){
			String[] toks = s.split(":");
			if(toks.length != 2)
				throw new Exception();
			pft.table.put(toks[0],Integer.parseInt(toks[1]));
		}
		return pft;
	}
	
	static String equivClass(Pile hand){
		if(hand.size() != 2)
			throw new IllegalArgumentException("hand must have size 2");
		hand.sort();
		Card c1 = hand.getCard(0);
		Card c2 = hand.getCard(1);
		boolean suited = c1.getSuit() == c2.getSuit();
		return String.format("%s%s%s",c1.getValue(),c2.getValue(),suited ? "s" : "");
	}
	
	public int get(Pile hand){
		String eqClass = equivClass(hand);
		return table.get(eqClass);
	}
	
	public boolean inPercentile(Pile hand, int percentile){
		return get(hand) >= percentile;
	}
}
