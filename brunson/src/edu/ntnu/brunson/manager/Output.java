package edu.ntnu.brunson.manager;

import java.util.ArrayList;
import java.util.Iterator;

public class Output {
	
	static ArrayList<String> handHistory = new ArrayList<String>();
	static ArrayList<String> debug = new ArrayList<String>();
	
	
	private Output(){
		
	}
	
	public static void addToHH(String s) {
		handHistory.add(s);
	}
	
	public static void addToDebug(String s) {
		debug.add(s);
	}
	
	public static void printHH() {
		Iterator<String> iterator = handHistory.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
