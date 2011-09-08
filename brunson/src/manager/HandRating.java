package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cards.Card;
import cards.Deck;
import cards.Pile;
import cards.Suit;
import cards.Value;

public class HandRating implements Comparable<HandRating>{

	private PokerHands type;
	private Value[] tieInfo;
	
	
	public static void main(String[] args) {
		Random r = new Random();
		for(int i = 0; i < 1000000; i++){
			Pile deck = Deck.fullDeck();
			deck.shuffle();
			Pile pile = deck.deal(r.nextInt(3) + 5);
			try{
				HandRating hr = rate(pile);
				if(hr.toString().equals("Royal Flush"))
					System.out.println(pile);
			} catch (Exception e){
				System.out.println(pile);
				e.printStackTrace();
			}
		}
	}
	
	
	private HandRating(PokerHands type, Value[] tieInfo){
		this.type = type;
		this.tieInfo = tieInfo;
	}
	
	
	
	public static HandRating rate(Pile... piles){
		Pile pile = new Pile(piles);
		
		List<Pile> partitions = partition(pile);
		Pile flush,straight;
		
		if((flush = findFlush(pile)) != null && (straight = findStraight(flush)) != null){
			return straightFlush(straight);
		} else if(partitions.get(0).size() == 4){
			return quad(partitions);
		} else if(partitions.get(0).size() == 3 && partitions.get(1).size() >= 2){
			return boat(partitions);
		} else if(flush != null){
			return flush(flush);
		} else if((straight = findStraight(pile)) != null){
			return straight(straight);
		} else if(partitions.get(0).size() == 3){
			return trips(partitions);
		} else if(partitions.get(0).size() == 2){
			if(partitions.get(1).size() == 2){
				return twopair(partitions);
			}
			return onepair(partitions);
		}
		return highcard(partitions);
	}


	private static Pile findFlush(Pile hand) {
		Map<Suit,Pile> map = new HashMap<Suit,Pile>();
		for(Card card : hand){
			Pile pile = map.get(card.getSuit());
			if(pile == null)
				map.put(card.getSuit(), new Pile(card));
			else
				pile.add(card);
		}
		
		for(Pile pile : map.values()){
			if(pile.size() >= 5)
				return pile;
		}
		return null;
	}

	private static Pile findStraight(Pile hand) {
		hand.sort();
		Card[] straight = new Card[5];
		int i = 0;
		straight[i] = hand.getCard(0);
		for (Card card : hand) {
			int comp = straight[i].compareTo(card);
			if(comp == -1){
				straight[++i] = card;
				if(i == 4)
					return new Pile(straight);
			} else if (comp < -1){
				straight[i = 0] = card;
			}
		}
		if(i == 3 && straight[0].getValue() == Value.FIVE && (straight[4] = hand.getCard(0)).getValue() == Value.ACE)
			return new Pile(straight);
		return null;
	}
	
	private static List<Pile> partition(Pile hand) {
		Map<Value,Pile> map = new HashMap<Value,Pile>();
		for(Card card : hand){
			Pile pile = map.get(card.getValue());
			if(pile == null)
				map.put(card.getValue(), new Pile(card));
			else
				pile.add(card);
		}
		List<Pile> list = new ArrayList<Pile>(map.values());
		Collections.sort(list);
		return list;
	}

	private static HandRating straightFlush(Pile straight) {
		Value[] tie = new Value[1];
		tie[0] = straight.getCard(0).getValue();
		return new HandRating(PokerHands.STRAIGHTFLUSH,tie);
	}

	private static HandRating quad(List<Pile> partitions) {
		Value[] tie = new Value[2];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.QUAD,tie);
	}
	
	private static HandRating boat(List<Pile> partitions) {
		Value[] tie = new Value[2];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.BOAT,tie);
	}
	
	private static HandRating flush(Pile flush) {
		flush.sort();
		Value[] tie = new Value[5];
		for(int i = 0; i < tie.length; i++)
			tie[i] = flush.getCard(i).getValue();
		return new HandRating(PokerHands.FLUSH,tie);
	}
	
	private static HandRating straight(Pile straight){ 
		Value[] tie = new Value[1];
		tie[0] = straight.getCard(0).getValue();
		return new HandRating(PokerHands.STRAIGHT,tie);
	}
	
	private static HandRating trips(List<Pile> partitions) {
		Value[] tie = new Value[3];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.TRIPS,tie);
	}
	
	private static HandRating twopair(List<Pile> partitions) {
		Value[] tie = new Value[3];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.TWOPAIR,tie);
	}
	
	private static HandRating onepair(List<Pile> partitions) {
		Value[] tie = new Value[4];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.ONEPAIR,tie);
	}
	
	private static HandRating highcard(List<Pile> partitions) {
		Value[] tie = new Value[5];
		setTieInfo(tie,partitions);
		return new HandRating(PokerHands.HIGHCARD,tie);
		
	}
	
	private static void setTieInfo(Value[] tie,List<Pile> partitions){
		for(int i = 0; i < tie.length; i++)
			tie[i] = partitions.get(i).getCard(0).getValue();
	}

	@Override
	public int compareTo(HandRating that) {
		int diff = this.type.ordinal() - that.type.ordinal();
		if(diff != 0)
			return diff;
		for(int i = 0; i < tieInfo.length; i++){
			diff = this.tieInfo[i].ordinal() - that.tieInfo[i].ordinal();
			if(diff != 0) 
				return diff;
		}
		return 0;
	}
	
	public String toString(){
		Value[] t = tieInfo;
		switch(type){
		
		case HIGHCARD: 
			return String.format("Highcards %c %c %c %c %c",t[0].pip,t[1].pip,t[2].pip,t[3].pip,t[4].pip);
		case ONEPAIR: 
			return String.format("Pair of %s, %c %c %c kickers",t[0].plural,t[1].pip,t[2].pip,t[3].pip);
		case TWOPAIR: 
			return String.format("Two pair %s and %s, %c kicker",t[0].plural,t[1].plural,t[2].pip);
		case TRIPS:
			return String.format("Three of a Kind %s, %c %c kickers",t[0].plural,t[1].pip,t[2].pip);
		case STRAIGHT:
			return String.format("Straight %s high",t[0].singular);
		case FLUSH:
			return String.format("Flush %s high, %s %s %s %s kickers",t[0].singular,t[1].pip,t[2].pip,t[3].pip,t[4].pip);
		case BOAT: 
			return String.format("Full House %s full of %s",t[0].plural,t[1].plural);
		case QUAD:
			return String.format("Four of a Kind %s, %c kicker",t[0].plural,t[1].pip);
		case STRAIGHTFLUSH:
			if(t[0] == Value.ACE)
				return "Royal Flush";
			return String.format("Straight Flush %s high",t[0].singular);
		}
		throw new RuntimeException("This shouldn't happen!");
	}
	
	static enum PokerHands {
		HIGHCARD,ONEPAIR,TWOPAIR,TRIPS,STRAIGHT,FLUSH,BOAT,QUAD,STRAIGHTFLUSH;
	}
}
