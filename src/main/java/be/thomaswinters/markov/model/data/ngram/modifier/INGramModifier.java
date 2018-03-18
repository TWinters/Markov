package be.thomaswinters.markov.model.data.ngram.modifier;

import be.thomaswinters.markov.model.data.seed.NSeed;

public interface INGramModifier<E> {
	int modifyCount(NSeed<E> seed, int currentAmount);
}
