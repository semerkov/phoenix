package com.rahul.phoenix.tui;

import com.rahul.phoenix.genetics.Genes;

public class TestTournament {

	@SuppressWarnings("unused")
	private static void playAll() {
		int numOfMatches = 1000;

		for (double[] genes : Genes.allGenes) {
			double whiteScore = 0;
			for (int i = 0; i < numOfMatches; i++)
				whiteScore += Double.parseDouble(TestMatch.play(genes).split(
						"-")[0]);

			System.out.println("Genetic player won " + 100
					* (whiteScore / numOfMatches) + "% of the matches");
		}
	}

	private static void play(double[] genes) {
		int numOfMatches = 250;
		double whiteScore = 0;
		long start = System.currentTimeMillis();

		for (int i = 0; i < numOfMatches; i++) {
			whiteScore += Double
					.parseDouble(TestMatch.play(genes).split("-")[0]);
			System.out.println(TestMatch.pgn);
		}

		long end = System.currentTimeMillis();
		System.out.println("Genetic player won " + 100
				* (whiteScore / numOfMatches) + "% of the matches");
		System.out.println("Total execution time : " + (end - start) / 1000
				+ " seconds");
	}

	public static void main(String[] args) {
		// playAll();
		play(Genes.mngenes4);
	}
}
