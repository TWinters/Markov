package be.thomaswinters.sentencemarkov.learner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.thomaswinters.markov.model.composite.MarkovGeneratorCascade;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;

public class MarkovCascadeSentenceLearner implements IMarkovSentenceGeneratorBuilder {
	private List<MarkovSentenceLearner> learners;

	public MarkovCascadeSentenceLearner(int maxSeedSize) {
		if (maxSeedSize <= 0) {
			throw new IllegalArgumentException();
		}
		learners = new ArrayList<>();
		for (int i = maxSeedSize; i >= 0; i--) {
			learners.add(new MarkovSentenceLearner(i));
		}
	}

	@Override
	public void addLine(String text, int weight) {
		learners.forEach(e -> e.addLine(text, weight));
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMarkovGenerator<String, ?> build() {
		return new MarkovGeneratorCascade<String, String>(
				learners.stream().map(e -> (IMarkovGenerator<String, String>) e.build()).collect(Collectors.toList()));
	}

}
