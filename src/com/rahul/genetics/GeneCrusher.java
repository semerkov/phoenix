package com.rahul.genetics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import jenes.chromosome.DoubleChromosome;
import jenes.population.Population.Statistics;
import jenes.tutorials.utils.Utils;

public class GeneCrusher {

	private static final int POPULATION_SIZE = 20;
	private static final int GENERATION_LIMIT = 100;
	private static final int GENE_SIZE = 640 + (3 * 181);
	private static final int MAX_WEIGHT = 48;
	private static final int MIN_WEIGHT = -56;

	private PhoenixGA algorithm;

	public GeneCrusher() {
		Random r = new Random();
		int min = -1;
		int max = 1;
		DoubleChromosome initialChromosome = new DoubleChromosome(GENE_SIZE,
				MIN_WEIGHT, MAX_WEIGHT);
		for (int i = 0; i < 640; i++) {
			initialChromosome.setValue(i, Genes.defaultGenes[i]);
		}
		for(int i = 640; i < GENE_SIZE; i++) {
			initialChromosome.setValue(i, min + (r.nextDouble() * ((max - min) + 1)));
		}
		algorithm = new PhoenixGA(POPULATION_SIZE, GENERATION_LIMIT,
				initialChromosome);
	}

	public void run() {
		this.algorithm.evolve();

		Statistics stat = algorithm.getCurrentPopulation().getStatistics();
		Utils.printStatistics(stat);
	}

	public static void main(String[] args) {
		String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar
				.getInstance().getTime());

		System.out.println("GENE CRUSHER FOR THE PHOENIX - RunLog : " + time);
		GeneCrusher geneCrusher = new GeneCrusher();
		geneCrusher.run();
	}
}
