package be.thomaswinters.markov.model.data.bags.impl;

import java.io.Serializable;
import java.util.Collection;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;

import be.thomaswinters.markov.model.data.bags.Bag;

public class ImmutableBag<E> implements Bag<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5977555590620259944L;
	private final ImmutableMultiset<E> multiset;

	public ImmutableBag(Bag<? extends E> bag) {
		this.multiset = ImmutableMultiset.copyOf(bag.toMultiset());
	}
	
	@Override
	public boolean contains(E e) {
		return multiset.contains(e);
	}

	@Override
	public int getCount(E e) {
		return multiset.count(e);
	}

	@Override
	public Collection<E> getUniqueElements() {
		return multiset.elementSet();
	}

	@Override
	public Multiset<E> toMultiset() {
		return multiset;
	}

	@Override
	public String toString() {
		return "IBag" + (hashCode() % 1000);
	}
}
