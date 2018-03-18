package be.thomaswinters.markov.model.data.bags.impl;

import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.bags.modifier.IBagModifier;

public class ModifiedBag<E, F> implements Bag<F> {
	private final Bag<F> bag;
	private final IBagModifier<E, F> modifier;
	private final List<E> seed;

	// public ModifiedBag(Bag<E> bag, BagModifier<E> modifier, List<E> seed) {
	// }

	public ModifiedBag(Bag<F> bag, IBagModifier<E, F> modifier, List<E> seed) {
		this.bag = bag;
		this.modifier = modifier;
		this.seed = seed;
	}


	@Override
	public Collection<F> getUniqueElements() {
		return bag.getUniqueElements();
	}

	@Override
	public int getCount(F e) {
		return modifier.modifyBagCount(seed, e, bag.getCount(e));
	}


	@Override
	public String toString() {
		return "ModifiedBag[" + seed + " * "+ modifier + " * " + bag + "]";
	}
	
	
}
