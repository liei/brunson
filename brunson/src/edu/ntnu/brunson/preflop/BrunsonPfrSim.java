package edu.ntnu.brunson.preflop;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BrunsonPfrSim {
	
	private static final String USAGE = "java -jar brunson-pfrsim.jar [-p <players>] [-t <timeout>] [-r <runs>] [-w <workers>] [-o <outputfile>]";

	private static final Options  options = new Options();
	
	static {
		options.addOption("p","players",true,"Max players to simulate to. 10 is default.")
			.addOption("t","timeout",true,"Time to let the program run before closing, in hours. 24 is default.")
			.addOption("r","runs",true,"How many runs to to with each hand. 1000 is default.")
			.addOption("o","ofile",true,"File to put output, stdout chosen as default.")
			.addOption("w","workers",true,"How many worker threads to have. 4 is default");
	}
	
	public static void main(String[] args) {
		CommandLine cl = null;
		CommandLineParser clp = new BasicParser();
		try {
			cl = clp.parse(options,args);
		} catch (ParseException e) {
			e.printStackTrace();
			HelpFormatter hf = new HelpFormatter();
			hf.printHelp(USAGE,options);
			System.exit(1);
		}
		
		int players = Integer.parseInt(cl.getOptionValue('p',"10")); 
		int timeout = Integer.parseInt(cl.getOptionValue('t',"24"));
		int runs = Integer.parseInt(cl.getOptionValue('r',"1000"));
		int workers = Integer.parseInt(cl.getOptionValue('w',"4"));
		
		PrintStream out;
		if(cl.hasOption('o')){
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(cl.getOptionValue('o'));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			out = new PrintStream(fos);
		} else {
			out = System.out;
		}
		PreFlopSimulation.generate(out,players,timeout,runs,workers);
	}
}
