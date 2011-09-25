package edu.ntnu.brunson.manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Output {
	
	private static boolean debug = false;
	private static boolean output = true;
	private static boolean toFile = false;
	private static PrintStream ps = null;
	
	private Output(){}

	public static void setFile(String filename){
		try {
			ps = new PrintStream(new FileOutputStream(filename));
			toFile = true;
		} catch (FileNotFoundException e) {
			System.err.printf("Couldn't find file %s. Writing to console.");
			toFile = false;
			output = true;
		}
	}
	
	public static void setOutput(boolean b){
		output = b;
	}
	
	public static void printf(String line,Object... os){
		if(output){
			System.out.printf(line,os);
		} 
		if(toFile){
			ps.printf(line,os);
		}
	}
	
	public static void println(Object o){
		printf("%s%n",o);
	}
	
	public static void println() {
		printf("%n");
	}

	public static void print(Object o){
		printf("%s",o);
	}
	
	
	public static void debugf(String line,Object... os){
		if(debug)
			System.out.printf("##DEBUG## %s%n",String.format(line,os));
	}
}
