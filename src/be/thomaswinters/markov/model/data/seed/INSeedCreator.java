package be.thomaswinters.markov.model.data.seed;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface INSeedCreator<E, F> {
	default NSeed<F> create(List<E> elements) {
		return toSeed(transformAll(elements));
	}

	ImmutableList<F> transformAll(List<E> wordsArg);

	NSeed<F> toSeed(List<F> list);

}
