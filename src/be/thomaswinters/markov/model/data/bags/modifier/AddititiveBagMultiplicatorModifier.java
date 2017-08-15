package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.Collection;
import java.util.stream.IntStream;

/**
 * Multiplies the current multiplier with several other multipliers
 * 
 * @author Thomas Winters
 *
 * @param <E>
 * @param <F>
 */
public class AddititiveBagMultiplicatorModifier<E, F> extends CompositeBagMultiplicatorModifier<E, F> {

	public AddititiveBagMultiplicatorModifier(Collection<? extends IBagMultiplicator<E, F>> multiplicators) {
		super(multiplicators);
	}

	@Override
	protected int aggregateCount(IntStream counts) {
		return counts.sum();
	}

}
