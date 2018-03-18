package be.thomaswinters.markov.model.data.bags.impl;

import java.util.List;
import java.util.stream.IntStream;

import be.thomaswinters.markov.model.data.bags.Bag;

public class MultiplicativeBag<E> extends CompositeBag<E> {

	private static final long serialVersionUID = -1430449571073102536L;

	public MultiplicativeBag(List<Bag<E>> bags) {
		super(bags);
	}

	@Override
	public int reduceBagCounts(IntStream counts) {
		return counts.reduce(1, (a, b) -> a * b);
	}
}
