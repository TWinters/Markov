package be.thomaswinters.markov.model.data.seed;

import java.util.List;

import com.google.common.collect.ImmutableList;

public interface INSeedTransformer<E,F> {
	F transform(E element);
	default ImmutableList<F> transformAll(List<E> wordsArg) {
		return ImmutableList.copyOf(wordsArg.stream().map(e -> transform(e)).iterator());
	}
}
