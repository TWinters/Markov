package be.thomaswinters.markov.model.builder;

import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.data.ngram.INGram;
import be.thomaswinters.markov.model.data.ngram.MultiplicativeNGram;

public class MarkovMultiplicativeGeneratorBuilder<E, F> extends MarkovCompositeGeneratorBuilder<E, F> {

	public MarkovMultiplicativeGeneratorBuilder(Collection<IMarkovGeneratorBuilder<E, F>> builders) {
		super(builders);
	}

	@Override
	public INGram<E, F> combineNGrams(List<INGram<E, F>> ngrams) {
		return new MultiplicativeNGram<E, F>(ngrams);
	}

}
