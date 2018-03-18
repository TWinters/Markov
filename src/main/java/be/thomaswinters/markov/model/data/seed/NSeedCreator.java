package be.thomaswinters.markov.model.data.seed;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class NSeedCreator<E, F> implements INSeedCreator<E, F> {

	private final INSeedTransformer<E, F> transformer;

	public NSeedCreator(INSeedTransformer<E, F> transformer) {
		this.transformer = transformer;
	}

	@Override
	public ImmutableList<F> transformAll(List<E> wordsArg) {
		return transformer.transformAll(wordsArg);
	}

	@Override
	public NSeed<F> toSeed(List<F> words) {
		return new NSeed<>(words);
	}

	@Override
	public String toString() {
		return "NSeedCreator [transformer=" + transformer + "]";
	}

}
