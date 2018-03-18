package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.Collection;
import java.util.stream.IntStream;

public class AdditiveBagModifier<E, F> extends CompositeBagModifier<E, F> {

	public AdditiveBagModifier(Collection<? extends IBagModifier<E, F>> modifiers) {
		super(modifiers);
	}

	@Override
	public int aggregateCount(IntStream counts) {
		return counts.sum();
	}

}
