package com.rahul.genetics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jenes.population.Population.Statistics;
import jenes.tutorials.utils.Utils;

public class GeneCrusher {

	private static final int POPULATION_SIZE = 20;
	private static final int GENERATION_LIMIT = 1000;
	private static final int GENE_SIZE = 640;

	private PhoenixGA algorithm;

	public GeneCrusher() {
		algorithm = new PhoenixGA(POPULATION_SIZE, GENERATION_LIMIT, GENE_SIZE);
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
