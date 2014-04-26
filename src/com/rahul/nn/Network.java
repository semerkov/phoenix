package com.rahul.nn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class Network {
	private MultiLayerPerceptron mlp;

	private static final double highInputLimit = 4;
	private static final double lowInputLimit = -4;
	private static final double highWeightLimit = 10;
	private static final double lowWeightLimit = -10;

	public Network() {
		mlp = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 16, 10, 1);
	}

	public void setWeights(double[] weights) {
		mlp.setWeights(normalize(weights, lowWeightLimit, highWeightLimit));
	}

	public void negateWeights() {
		Double[] weights = mlp.getWeights();
		double[] negatedWeights = new double[weights.length];
		for (int i = 0; i < weights.length; i++) {
			negatedWeights[i] = -weights[i];
		}
		mlp.setWeights(negatedWeights);
	}

	public double propogate(double[] input) {
		mlp.setInput(normalize(input, lowInputLimit, highInputLimit));
		mlp.calculate();
		return mlp.getOutput()[0];
	}

	private double[] normalize(double[] input, double lowLimit, double highLimit) {
		double[] normalizedInput = new double[input.length];
		Arrays.sort(input);
		double minimum = input[0];
		double maximum = input[input.length - 1];

		for (int i = 0; i < input.length; i++) {
			normalizedInput[i] = ((highLimit - lowLimit) * (input[i] - minimum))
					/ (maximum - minimum) + lowLimit;
		}
		return normalizedInput;
	}

	public static void main(String[] args) {
		new Network();
	}
}
