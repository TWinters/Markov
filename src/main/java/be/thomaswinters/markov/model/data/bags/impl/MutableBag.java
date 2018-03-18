package be.thomaswinters.markov.model.data.bags.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import be.thomaswinters.markov.model.data.bags.WriteableBag;

public class MutableBag<E> implements WriteableBag<E>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4305907243813634804L;
	// private final Multiset<E> multiset;
	private final HashMap<E, Integer> map;

	public MutableBag() {
		// this.multiset = HashMultiset.create();
		this.map = new HashMap<>();
	}

	@Override
	public int add(E e, int amount) {
		// return multiset.add(e, amount);
		if (!map.containsKey(e)) {
			map.put(e, amount);
		}
		return map.put(e, map.get(e) + amount);
	}

	@Override
	public boolean contains(E e) {
//		return multiset.contains(e);
		return map.containsKey(e);
	}

	@Override
	public int getCount(E e) {
		// return multiset.count(e);
		return map.containsKey(e) ? map.get(e) : 0;
	}

	@Override
	public Collection<E> getUniqueElements() {
		// return multiset.elementSet();
		return map.keySet();
	}

	@Override
	public Multiset<E> toMultiset() {
		// return ImmutableMultiset.copyOf(multiset);
		Multiset<E> result = HashMultiset.create();
		map.entrySet().stream().forEach(val -> result.add(val.getKey(), val.getValue()));
		return result;
	}

	@Override
	public String toString() {
		return "MBag" + toMultiset();
	}

}
