package be.thomaswinters.markov.model.data.bags.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import be.thomaswinters.markov.model.data.bags.Bag;

public abstract class CompositeBag<E> implements Bag<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771735784245897060L;
	private final List<Bag<E>> bags;

	public CompositeBag(List<Bag<E>> bags) {
		this.bags = bags;
	}

	@Override
	public Collection<E> getUniqueElements() {
		return bags.stream().flatMap(e -> e.getUniqueElements().stream()).collect(Collectors.toSet());
	}

	@Override
	public int getCount(E element) {
		return reduceBagCounts(bags.stream().mapToInt(e -> e.getCount(element)));
	}
	
	public abstract int reduceBagCounts(IntStream counts);

	@Override
	public String toString() {
		return bags.stream().map(e -> e.toString()).collect(Collectors.joining(" + "));
	}

}
