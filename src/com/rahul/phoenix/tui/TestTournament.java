package com.rahul.phoenix.tui;

import com.rahul.genetics.Genes;

public class TestTournament {

	public static void main(String[] args) {
		int numOfMatches = 1000;

		for (double[] genes : Genes.allGenes) {
			double whiteScore = 0;
			for (int i = 0; i < numOfMatches; i++)
				whiteScore += Double
						.parseDouble(TestMatch.play(genes).split("-")[0]);

			System.out.println("Genetic player won " + 100
					* (whiteScore / numOfMatches) + "% of the matches");
		}
	}
}
