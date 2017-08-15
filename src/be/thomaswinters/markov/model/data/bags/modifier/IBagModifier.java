package be.thomaswinters.markov.model.data.bags.modifier;

import java.util.List;

public interface IBagModifier<E,F> {
	int modifyBagCount(List<E> seed, F current, int currentCount);
}
