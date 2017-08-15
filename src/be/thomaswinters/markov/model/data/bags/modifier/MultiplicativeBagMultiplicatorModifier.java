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
public class MultiplicativeBagMultiplicatorModifier<E, F> extends CompositeBagMultiplicatorModifier<E, F> {

	public MultiplicativeBagMultiplicatorModifier(Collection<? extends IBagMultiplicator<E, F>> multiplicators) {
		super(multiplicators);
	}

	@Override
	protected int aggregateCount(IntStream mapToInt) {
		return mapToInt.reduce(1, (a, b) -> a * b);
	}

}
