package com.rahul.genetics;

import jenes.population.Individual;
import jenes.population.Population;
import jenes.population.Population.Statistics;
import jenes.population.Population.Statistics.Group;
import jenes.tutorials.utils.Utils;

public class GeneCrusher {
	
    private static final int POPULATION_SIZE = 20;
    private static final int GENERATION_LIMIT = 100;
    private static final int GENE_SIZE = 640;
    
    private PhoenixGA algorithm;
    
    public GeneCrusher() {
    	algorithm = new PhoenixGA(POPULATION_SIZE, GENERATION_LIMIT, GENE_SIZE);
    }
    
    public void run() {
        this.algorithm.evolve();
        
        Statistics stat = algorithm.getCurrentPopulation().getStatistics();
        Group legals = stat.getGroup(Population.LEGALS);
        Individual solution = legals.get(0);
        System.out.println(solution.getChromosome());
        Utils.printStatistics(stat);
    }
    
	public static void main(String[] args) {
		System.out.println("GENE CRUSHER FOR THE PHOENIX");
		GeneCrusher geneCrusher = new GeneCrusher();
		geneCrusher.run();
	}
}
