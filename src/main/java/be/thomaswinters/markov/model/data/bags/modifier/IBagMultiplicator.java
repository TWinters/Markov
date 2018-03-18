package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.List;

public interface IBagMultiplicator<E, F> {
	int calculateMultiplier(List<E> seed, F current);
}
