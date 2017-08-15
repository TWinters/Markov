package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableList;

public abstract class CompositeBagModifier<E, F> implements IBagModifier<E, F> {

	private final List<IBagModifier<E, F>> modifiers;

	public CompositeBagModifier(Collection<? extends IBagModifier<E, F>> modifiers) {
		this.modifiers = ImmutableList.copyOf(modifiers);
	}

	public int modifyBagCount(List<E> seed, F current, int currentCount) {
		return aggregateCount(modifiers.stream().mapToInt(mod -> mod.modifyBagCount(seed, current, currentCount)));
	}

	public abstract int aggregateCount(IntStream counts);

}
