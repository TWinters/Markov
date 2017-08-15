package be.thomaswinters.markov.model.builder;

import java.util.List;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.generator.IMarkovGenerator;

public interface IMarkovGeneratorBuilder<E,F> {
	void addLine(ImmutableList<E> words, int weight);
	default void addLine(ImmutableList<E> words) {
		addLine(words,1);
	}

	void addBegin(List<E> words, int weight);

	IMarkovGenerator<E,F> build();

}
