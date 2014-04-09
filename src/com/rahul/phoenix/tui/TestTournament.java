package com.rahul.phoenix.tui;

public class TestTournament {

	public static void main(String[] args) {
		double whiteScore = 0;
		int numOfMatches = 100;

		for(int i=0; i<numOfMatches; i++)
			whiteScore += Double.parseDouble(TestMatch.play().split("-")[0]);
		
		System.out.println("Genetic player won " + 100
				* (whiteScore / numOfMatches) + "% of the matches");
	}
}
