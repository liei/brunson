package edu.ntnu.brunson.manager;

import edu.ntnu.brunson.player.AIPlayer;
import edu.ntnu.brunson.player.Phase1Player;
import edu.ntnu.brunson.player.Phase2Player;
import edu.ntnu.brunson.player.Phase3Player;

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
		Output.setOutput(Output.SPARSE);
		Output.setDebug(false);
		gameManager.addPlayer(new Phase1Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase1Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase2Player(100, 50, 20, 25));
		gameManager.addPlayer(new Phase2Player(100, 40, 20, 15));
		gameManager.addPlayer(new Phase3Player(100, 25, 20, 15));
		gameManager.addPlayer(new Phase3Player(100, 5, 20, 15));
		gameManager.playGames(10000);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
}
