package com.rahul.genetics;

import java.util.List;

import jenes.GenerationEventListener;
import jenes.GeneticAlgorithm;
import jenes.chromosome.DoubleChromosome;
import jenes.population.Fitness;
import jenes.population.Individual;
import jenes.population.Population;
import jenes.stage.operator.common.OnePointCrossover;
import jenes.stage.operator.common.SimpleMutator;
import jenes.stage.operator.common.TournamentSelector;

public class PhoenixGA extends GeneticAlgorithm<DoubleChromosome> implements
		GenerationEventListener<DoubleChromosome> {

	private double wins;

	private static final int MAX_WEIGHT = 100;
	private static final int MIN_WEIGHT = -100;
	private static final int ELITES = 2;

	private class PhoenixFitness extends Fitness<DoubleChromosome> {
		private PhoenixFitness(boolean maximize) {
			super(maximize);
		}

		@Override
		public void evaluate(Individual<DoubleChromosome> individual) {

		}
	}

	private PhoenixFitness maximize = new PhoenixFitness(true);

	public PhoenixGA(int popsize, int generations, int chainLength) {
		super(new Population<DoubleChromosome>(
				new Individual<DoubleChromosome>(new DoubleChromosome(
						chainLength, MIN_WEIGHT, MAX_WEIGHT)), popsize),
				generations);


		this.setFitness(this.maximize);
		this.setElitism(ELITES);
		this.setElitismStrategy(ElitismStrategy.WORST);

		this.addStage(new TournamentSelector<DoubleChromosome>(2));
		this.addStage(new OnePointCrossover<DoubleChromosome>(0.8));
		this.addStage(new SimpleMutator<DoubleChromosome>(0.02));
	}

	@Override
	protected void onInit(long time) {
		super.onInit(time);
	}

	@Override
	public void onGeneration(GeneticAlgorithm<DoubleChromosome> ga, long time) {

	}
	
	@SuppressWarnings("deprecation")
	private void playTournament() {
		Population<DoubleChromosome> pop = this.getCurrentPopulation();
		List<Individual<DoubleChromosome>> individuals = pop.getAllLegalIndividuals();
	}
}
