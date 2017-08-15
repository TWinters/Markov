package be.thomaswinters.markov.model.data.bags;

import java.util.Collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public interface Bag<E> {

	Collection<E> getUniqueElements();

	int getCount(E e);

	default boolean contains(E e) {
		return getUniqueElements().contains(e);
	}


	default Multiset<E> toMultiset() {
		Multiset<E> result = HashMultiset.create();
		getUniqueElements().forEach(e -> result.add(e, getCount(e)));
		return result;
	}

	default boolean containsAll(Collection<? extends E> collection) {
		return collection.stream().allMatch(e -> contains(e));
	}

	default int size() {
		return getAmountOfElements();
	}

	default int getAmountOfElements() {
		return getUniqueElements().stream().mapToInt(e -> getCount(e)).sum();
	}

	default int getAmountOfUniqueElements() {
		return getUniqueElements().size();
	}

	default E get(int index) {
		int currentIndex = 0;
		for (E e : getUniqueElements()) {
			currentIndex += getCount(e);
			if (currentIndex > index) {
				return e;
			}
		}
		throw new IllegalArgumentException("No element with index " + index + " in " + this);
	}

	default boolean isEmpty() {
		return size() == 0;
	}
}
