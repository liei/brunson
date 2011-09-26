package edu.ntnu.brunson.manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Output {
	
	public static final int VERBOSE = 3;
	public static final int SPARSE  = 2;
	public static final int RESULTS = 1;
	public static final int SILENT = 0;
	
	private static boolean debug = true;
	private static boolean fileDebug = false;
	private static int output = VERBOSE;
	private static int toFile = SILENT;
	private static PrintStream ps = null;
	
	private Output(){}
	
	public static void setFile(String filename,int outputLevel){
		try {
			ps = new PrintStream(new FileOutputStream(filename));
			toFile = outputLevel;
		} catch (FileNotFoundException e) {
			System.err.printf("Couldn't find file %s. Writing to console.");
			toFile = SILENT;
			output = outputLevel;
		}
	}

	public static void setOutput(int outputLevel){
		Output.output = outputLevel;
	}
	
	public static void setFile(int outputLevel){
		toFile = outputLevel;
	}
	
	public static void setDebug(boolean b){
		debug = b;
	}
	
	public static void setFileDebug(boolean b){
		fileDebug = b;
	}
	
	private static void printf(int outputLevel,String line,Object... os){
		String s = String.format(line,os);
		
		if(outputLevel <= output)
			System.out.println(s);
		if(outputLevel <= toFile)
			ps.println(s);
	}
	
	public static void verbose(String line,Object... os){
		printf(VERBOSE,line,os);
	}
	
	public static void verbose(Object o) {
		printf(VERBOSE,"%s",o);
	}
	
	public static void sparse(String line,Object... os){
		printf(SPARSE,line,os);
	}
	
	public static void sparse(Object o) {
		printf(SPARSE,"%s",o);
	}
	
	public static void results(String line,Object... os){
		printf(RESULTS,line,os);
	}
	
	@Deprecated
	public static void printf(String line,Object... os){
		printf(VERBOSE,line,os);
	}
	
	@Deprecated
	public static void println(Object o){
		printf(VERBOSE,"%s%n",o);
	}

	@Deprecated
	public static void println() {
		printf(VERBOSE,"%n");
	}

	@Deprecated
	public static void print(Object o){
		printf(VERBOSE,"%s",o);
	}

	public static void debugf(String line,Object... os){
		String s = String.format("##DEBUG## %s%n",String.format(line,os));
		if(debug)
			System.out.println(s);
		if(fileDebug){
			ps.println(s);
		}
			
	}




}