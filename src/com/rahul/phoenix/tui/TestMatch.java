package com.rahul.phoenix.tui;

import java.io.IOException;

import com.rahul.phoenix.engine.ComputerPlayer;
import com.rahul.phoenix.genetics.Genes;

public class TestMatch {

	static String pgn = null;
	
	public static String play(double[] genes) {
		boolean verbose = false;

		ComputerPlayer whitePlayer = new ComputerPlayer(verbose, genes, false);
		ComputerPlayer blackPlayer = new ComputerPlayer(verbose, Genes.gnGenes1, false);
		whitePlayer.setTTLogSize(10);
		blackPlayer.setTTLogSize(10);
		TUIGame game = new TUIGame(whitePlayer, blackPlayer);
		try {
			String result;
			result = game.play(verbose);
			pgn = game.getMoveListString(true);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
