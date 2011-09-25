package edu.ntnu.brunson.manager;

import java.util.ArrayList;
import java.util.Iterator;

public class Output {
	
	public static boolean debug = false;
	
	private static ArrayList<String> handHistory = new ArrayList<String>();
	//private static ArrayList<String> debug = new ArrayList<String>();
	private static boolean verbose = true;
	
	private Output(){
		
	}
	public static void println() {
		System.out.println();
	}
	public static void println(Object o){
		System.out.println(o);
	}

	public static void print(Object o){
		System.out.print(o);
	}
	
	public static void printf(String line,Object... os){
		System.out.printf(line,os);
	}
	
	public static void debugf(String line,Object... os){
		if(debug)
			System.out.printf(line,os);
	}

	
//	public static void addToHH(String s) {
//		handHistory.add(s);
//	}
//	
//	public static void addToDebug(String s) {
//		debug.add(s);
//	}
//	
//	public static void printHH() {
//		if(!verbose) {
//			handHistory.clear();
//			return;
//		}
//		Iterator<String> iterator = handHistory.iterator();
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		handHistory.clear();
//	}


	
	
}
