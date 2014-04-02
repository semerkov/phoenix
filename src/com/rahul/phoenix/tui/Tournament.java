package com.rahul.phoenix.tui;

import java.io.IOException;

import com.rahul.engine.ComputerPlayer;

public class Tournament {
	public static void main(String[] args) {
		boolean verbose = false;
        ComputerPlayer whitePlayer = new ComputerPlayer(verbose);
        ComputerPlayer blackPlayer = new ComputerPlayer(verbose);
        whitePlayer.setTTLogSize(19);
        blackPlayer.setTTLogSize(19);
        TUIGame game = new TUIGame(whitePlayer, blackPlayer);
        try {
			game.play(verbose);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
