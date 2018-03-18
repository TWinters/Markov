package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.List;
import java.util.stream.IntStream;

public class MultiplicativeBagModifier<E, F> extends CompositeBagModifier<E, F> {

	public MultiplicativeBagModifier(List<IBagModifier<E, F>> modifiers) {
		super(modifiers);
	}

	@Override
	public int aggregateCount(IntStream counts) {
		return counts.reduce(1, (a, b) -> a * b);
	}

}
