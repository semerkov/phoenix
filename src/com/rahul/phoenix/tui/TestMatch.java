package com.rahul.phoenix.tui;

import java.io.IOException;

import com.rahul.engine.ComputerPlayer;
import com.rahul.genetics.Genes;

public class TestMatch {

	public static String play(double[] genes) {
		boolean verbose = false;

		ComputerPlayer whitePlayer = new ComputerPlayer(verbose, genes, false);
		ComputerPlayer blackPlayer = new ComputerPlayer(verbose, Genes.gnGenes1, false);
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
