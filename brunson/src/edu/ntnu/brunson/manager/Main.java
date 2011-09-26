package edu.ntnu.brunson.manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.ntnu.brunson.cards.Deck;
import edu.ntnu.brunson.cards.Pile;
import edu.ntnu.brunson.player.AIPlayer;
import edu.ntnu.brunson.player.Phase1Player;
import edu.ntnu.brunson.player.Phase2Player;
import edu.ntnu.brunson.preflop.*;
import java.io.PrintStream;
import java.io.File;

public class Main {

	private Main(){};
	
	private void run(){
		GameManager gameManager = new GameManager();
		try{
			AIPlayer.loadPFT(6, "preflop.txt");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		Output.setOutput(Output.RESULTS);
		gameManager.addPlayer(new Phase1Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase1Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase1Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase2Player(100, 40, 20, 15));
		gameManager.addPlayer(new Phase2Player(100, 25, 20, 15));
		gameManager.addPlayer(new Phase2Player(100, 5, 20, 15));
		gameManager.playGames(10000);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
}
