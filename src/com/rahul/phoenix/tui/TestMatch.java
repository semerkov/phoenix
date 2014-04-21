package com.rahul.phoenix.tui;

import java.io.IOException;

import com.rahul.engine.ComputerPlayer;
import com.rahul.genetics.Genes;

public class TestMatch {

	private static double[] randomizeWeights() {
		double[] weights = new double[640];
		int min = -100;
		int max = 100;

		for (int i = 0; i < 640; i++) {
			weights[i] = min + (int) (Math.random() * ((max - min) + 1));
		}
		return weights;
	}

	private static double[] zeroWeights() {
		return new double[640];
	}

	public static String play() {
		boolean verbose = false;

		ComputerPlayer whitePlayer = new ComputerPlayer(verbose, Genes.testGenes3);
		// ComputerPlayer blackPlayer = new ComputerPlayer(verbose);
		ComputerPlayer blackPlayer = new ComputerPlayer(verbose, Genes.defaultGenes);
		whitePlayer.setTTLogSize(10);
		blackPlayer.setTTLogSize(10);
		TUIGame game = new TUIGame(whitePlayer, blackPlayer);
		try {
			return game.play(verbose);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
