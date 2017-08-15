package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableList;

/**
 * Multiplies the current multiplier with several other multipliers
 * 
 * @author Thomas Winters
 *
 * @param <E>
 * @param <F>
 */
public abstract class CompositeBagMultiplicatorModifier<E, F> implements IBagModifier<E, F> {

	private final List<IBagMultiplicator<E, F>> multiplicators;

	public CompositeBagMultiplicatorModifier(Collection<? extends IBagMultiplicator<E, F>> multiplicators) {
		this.multiplicators = ImmutableList.copyOf(multiplicators);
	}

	@Override
	public int modifyBagCount(List<E> seed, F current, int currentCount) {
		return currentCount
				* aggregateCount(multiplicators.stream().mapToInt(mul -> mul.calculateMultiplier(seed, current)));
	}

	protected abstract int aggregateCount(IntStream stream);

}
