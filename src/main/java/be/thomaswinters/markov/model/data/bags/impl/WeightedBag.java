package be.thomaswinters.markov.model.data.bags.impl;

import java.io.Serializable;
import java.util.Collection;

import be.thomaswinters.markov.model.data.bags.Bag;

public class WeightedBag<E> implements Bag<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6743189979427693388L;
	
	private final Bag<E> bag;
	private final int weight;

	public WeightedBag(Bag<E> bag, int weight) {
		this.bag = bag;
		this.weight = weight;
	}

	public int getCount(E e) {
		return bag.getCount(e) * weight;
	}

	public Collection<E> getUniqueElements() {
		return bag.getUniqueElements();
	}

	@Override
	public String toString() {
		return weight + "*" + bag.toString();
	}
}
