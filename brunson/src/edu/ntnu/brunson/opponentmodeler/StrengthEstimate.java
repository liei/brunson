package edu.ntnu.brunson.opponentmodeler;

public class StrengthEstimate {

	private double strength;
	private int timesSeen;
	
	StrengthEstimate(){
		this.strength = 0;
		this.timesSeen = 0;
	}
	
	public double getStrength(){
		return strength;
	}
	
	public int getTimesSeen(){
		return timesSeen;
	}
	
	void addStrength(double newStrength){
		strength = ((strength * timesSeen) + newStrength) / ++timesSeen;
	}
	
	
}
