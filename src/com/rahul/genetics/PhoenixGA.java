package com.rahul.genetics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import jenes.GeneticAlgorithm;
import jenes.chromosome.DoubleChromosome;
import jenes.population.Fitness;
import jenes.population.Individual;
import jenes.population.Population;
import jenes.stage.operator.common.OnePointCrossover;
import jenes.stage.operator.common.SimpleMutator;
import jenes.stage.operator.common.TournamentSelector;

import com.rahul.phoenix.tui.TUIGame;

public class PhoenixGA extends GeneticAlgorithm<DoubleChromosome> {

	/** This is a list of players who compete in the tournament */
	private ArrayList<GeneticPlayer> players;
	private static final int ELITES = 0;

	private class PhoenixFitness extends Fitness<DoubleChromosome> {
		private PhoenixFitness(boolean maximize) {
			super(maximize);
		}

		@Override
		public void evaluate(Individual<DoubleChromosome> individual) {
			// Fitness values are already set during the Tournament
		}
	}

	private PhoenixFitness maximize = new PhoenixFitness(true);

	public PhoenixGA(int popsize, int generations, DoubleChromosome initialChromosome) {
		super(new Population<DoubleChromosome>(
				new Individual<DoubleChromosome>(initialChromosome), popsize),
						generations);

		this.setFitness(this.maximize);
		// this.setElitism(ELITES);
		// this.setElitismStrategy(ElitismStrategy.WORST);

		this.addStage(new TournamentSelector<DoubleChromosome>(2));
		this.addStage(new OnePointCrossover<DoubleChromosome>(0.8));
		this.addStage(new SimpleMutator<DoubleChromosome>(0.1));
	}

	@SuppressWarnings("deprecation")
	private void playTournament() {
		Population<DoubleChromosome> pop = this.getCurrentPopulation();
		List<Individual<DoubleChromosome>> individuals = pop.getIndividuals();
		Random random = new Random();
		double score = 0.0;
		int numOfPlayers = individuals.size();
		players = new ArrayList<GeneticPlayer>();

		for (int playerIndex = 0; playerIndex < numOfPlayers; playerIndex++) {
			players.add(new GeneticPlayer(individuals.get(playerIndex)
					.getChromosome().getValues()));
		}
		for (int playerIndex = 0; playerIndex < numOfPlayers; playerIndex++) {
			GeneticPlayer player1 = players.get(playerIndex);
			int opponentIndex;
			while (player1.getNumOfGames() < 3) {
				while ((opponentIndex = random.nextInt(numOfPlayers)) == playerIndex)
					;
				GeneticPlayer player2 = players.get(opponentIndex);
				player1.setNumOfGames(player1.getNumOfGames() + 1);
				player2.setNumOfGames(player2.getNumOfGames() + 1);
				TUIGame game = new TUIGame(player1.getPlayer(),
						player2.getPlayer());
				try {
					score = getScore(game.play(false));
				} catch (IOException e) {
					e.printStackTrace();
				}
				player1.setNumOfWins(player1.getNumOfWins() + score);
				player2.setNumOfWins(player2.getNumOfWins() + (1 - score));
			}
			// Set fitness value for the individual
			individuals.get(playerIndex).setScore(
					player1.getNumOfWins() / player1.getNumOfGames());
		}
	}

	@Override
	protected void onGeneration(long time) {
		super.onGeneration(time);
		
		playTournament();
		double[] wins = new double[players.size()];
		for (int i = 0; i < players.size(); i++) {
			wins[i] = players.get(i).getNumOfWins();
		}
		Arrays.sort(wins);

		double highestScore = wins[wins.length - 1];
		for (GeneticPlayer player : players) {
			if (player.getNumOfWins() == highestScore) {
				System.out.println(this.getGeneration() + " : "
						+ prettyPrint(player.getGenes()));
			}
		}
	}

	private String prettyPrint(double[] genes) {
		StringBuilder sb = new StringBuilder();
		for (double d : genes)
			sb.append(d + " ");
		return sb.toString();
	}

	private double getScore(String finalScore) {
		try {
			return Double.parseDouble(finalScore.split("-")[0]);
		} catch (Exception e) {
			System.out
					.println("Error converting to double. PhoenixGA -> getScore");
			System.exit(-1);
		}
		return 0.0;
	}
}
