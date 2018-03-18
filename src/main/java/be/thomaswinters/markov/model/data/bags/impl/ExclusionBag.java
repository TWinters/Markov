package be.thomaswinters.markov.model.data.bags.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import be.thomaswinters.markov.model.data.bags.Bag;

public class ExclusionBag<E> implements Bag<E> {
	private final Bag<E> bag;
	private final Set<E> elementsToExclude;

	public ExclusionBag(Bag<E> bag, Collection<E> elementsToExclude) {
		this.bag = bag;
		this.elementsToExclude = new HashSet<E>(elementsToExclude);
	}

	@Override
	public Collection<E> getUniqueElements() {
		Set<E> result = new HashSet<>(bag.getUniqueElements());
		result.removeAll(elementsToExclude);
		return result;
	}

	@Override
	public int getCount(E e) {
		if (elementsToExclude.contains(e)) {
			return 0;
		}
		return bag.getCount(e);
	}
}
