package be.thomaswinters.markov.model.data.bags.impl;

import java.util.List;
import java.util.stream.IntStream;

import be.thomaswinters.markov.model.data.bags.Bag;

public class AdditiveBag<E> extends CompositeBag<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8986917510012786629L;

	public AdditiveBag(List<Bag<E>> bags) {
		super(bags);
	}

	@Override
	public int reduceBagCounts(IntStream counts) {
		return counts.sum();
	}
}
