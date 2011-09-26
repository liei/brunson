package edu.ntnu.brunson.opponentmodeler;

public class StrengthEstimate {

	private double strength;
	private int timesSeen;
	
	StrengthEstimate(double strength, int timesSeen){
		this.strength = strength;
		this.timesSeen = timesSeen;
	}
	
	public double getStrength(){
		return strength;
	}
	
	public int getTimesSeen(){
		return timesSeen;
	}
	
	void addStrength(double newStrength){
		timesSeen++;
		strength += newStrength / timesSeen;
	}
	
	
}
