package be.thomaswinters.markov.model.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.ngram.INGram;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.markov.model.generator.MarkovGenerator;

public abstract class MarkovCompositeGeneratorBuilder<E, F> implements IMarkovGeneratorBuilder<E, F> {
	private final ImmutableList<IMarkovGeneratorBuilder<E, F>> builders;

	public MarkovCompositeGeneratorBuilder(Collection<IMarkovGeneratorBuilder<E, F>> builders) {
		this.builders = ImmutableList.copyOf(builders);
	}

	@Override
	public void addLine(ImmutableList<E> words, int weight) {
		builders.stream().forEach(e -> e.addLine(words, weight));
	}

	@Override
	public void addBegin(List<E> words, int weight) {
		builders.stream().forEach(e -> e.addBegin(words, weight));
	}

	@Override
	public IMarkovGenerator<E, F> build() {
		List<INGram<E, F>> ngrams = builders.stream().map(e -> e.build().getNGram()).collect(Collectors.toList());
		return new MarkovGenerator<>(combineNGrams(ngrams));
	}
	
	public abstract INGram<E,F> combineNGrams(List<INGram<E, F>> ngrams);
}
