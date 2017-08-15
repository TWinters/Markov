package be.thomaswinters.markov.model.data.bags;

import java.util.Collection;

public interface WriteableBag<E> extends Bag<E> {

	default boolean add(E e) {
		return add(e, 1) > 0;
	}
	
	int add(E e, int amountOfTimes);

	default boolean addAll(Collection<? extends E> collection) {
		return collection.stream().allMatch(e -> add(e));
	}
}
