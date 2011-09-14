package edu.ntnu.brunson.preflop;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BrunsonPfrSim {
	
	private static final String USAGE = "java -jar brunson-pfrsim.jar [-p <players>] [-t <timeout>] [-r <runs>] [-o <outputfile>]";

	private static final Options  options = new Options();
	
	static {
		options.addOption("p","players",true,"Max players to simulate to. 10 is default.")
			.addOption("t","timeout",true,"Time to let the program run before closing, in hours.24 is default.")
			.addOption("r","runs",true,"How many runs to to with each hand. 1000 is default.")
			.addOption("o","ofile",true,"File to put output, stdout chosen as default.");
	}
	
	public static void main(String[] args) {
		CommandLineParser clp = new GnuParser();
		CommandLine cl = null;
		try {
			clp.parse(options,args);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(USAGE);
			System.exit(1);
		}
		for(String s : cl.getArgs()){
			System.out.println(s);
		}
		
	}
	
}