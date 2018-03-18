package be.thomaswinters.markov.model.builder;

import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.data.ngram.AdditiveNGram;
import be.thomaswinters.markov.model.data.ngram.INGram;

public class MarkovAdditiveGeneratorBuilder<E, F> extends MarkovCompositeGeneratorBuilder<E, F> {

	public MarkovAdditiveGeneratorBuilder(Collection<IMarkovGeneratorBuilder<E, F>> builders) {
		super(builders);
	}

	@Override
	public INGram<E, F> combineNGrams(List<INGram<E, F>> ngrams) {
		return new AdditiveNGram<E, F>(ngrams);
	}

}
