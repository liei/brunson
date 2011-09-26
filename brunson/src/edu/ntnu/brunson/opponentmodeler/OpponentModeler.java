package edu.ntnu.brunson.opponentmodeler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ntnu.brunson.manager.Output;
import edu.ntnu.brunson.player.Player;

public class OpponentModeler {

	
	private static Map<Player,PlayerModel> models = new HashMap<Player,PlayerModel>();
	
	private OpponentModeler(){}
	
	public static void init(List<Player> players){
		models.clear();
		for(Player p : players)
			models.put(p,new PlayerModel());
	}
	
	private static PlayerModel getModel(Player p){
		PlayerModel pm = models.get(p);
		if(pm == null)
			throw new IllegalArgumentException(String.format("No such player: %s",p.getName()));
		return pm;
	}
	
	public static void add(Player p,Context c, double strength){
		getModel(p).add(c,strength);
	}
	
	public static StrengthEstimate get(Player p, Context c){
		return getModel(p).get(c);
	}
	
	
	
	static class PlayerModel{
		Map<Context,StrengthEstimate> estimates;
		
		private PlayerModel(){
			estimates = new HashMap<Context,StrengthEstimate>();
		}
		
		private void add(Context c,double strength){
			get(c).addStrength(strength);
		}
		
		private StrengthEstimate get(Context c){
			StrengthEstimate se = estimates.get(c);
			if(se == null){
				se = new StrengthEstimate();
				estimates.put(c,se);
			}
			return se;
		}
		
		public String toString(){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println(Context.getHeader());
			for(Map.Entry<Context,StrengthEstimate> e : estimates.entrySet()){
				StrengthEstimate se = e.getValue();
				pw.printf("%s  %.4f  %3d%n",e.getKey(),se.getStrength(),se.getTimesSeen());
			}
			return sw.toString();
		}
		
	}
	
	public static void print(int outputLevel){
		for(Map.Entry<Player,PlayerModel> e : models.entrySet()){
			Output.printf(outputLevel,"%s:",e.getKey().getName());
			Output.printf(outputLevel,"%s",e.getValue());
		}
	}
	
	
}
