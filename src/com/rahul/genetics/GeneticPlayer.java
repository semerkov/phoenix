package com.rahul.genetics;

import com.rahul.engine.ComputerPlayer;

public class GeneticPlayer {
	private int numOfGames;
	private double numOfWins;
	private ComputerPlayer player;
	
	private static final int LOG_SIZE = 10;
	
	public GeneticPlayer(double[] genes) {
		numOfGames = 0;
		numOfWins = 0;
		player = new ComputerPlayer(false, genes);
		player.setTTLogSize(LOG_SIZE);
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}

	public double getNumOfWins() {
		return numOfWins;
	}

	public void setNumOfWins(double numOfWins) {
		this.numOfWins = numOfWins;
	}

	public ComputerPlayer getPlayer() {
		return player;
	}
}
