package be.thomaswinters.markov.model.data.ngram;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public abstract class CompositeNGram<E, F> implements INGram<E, F>, Serializable {

	private static final long serialVersionUID = 3094183914642263869L;
	private final Collection<INGram<E, F>> elements;
	private final ImmutableList<List<E>> startElements;

	public CompositeNGram(Collection<? extends INGram<E, F>> elements) {
		this.elements = ImmutableList.copyOf(elements);
		this.startElements = ImmutableList
				.copyOf(elements.stream().flatMap(e -> e.getStartElements().stream()).iterator());
	}

	@Override
	public Optional<Bag<IMarkovElement<E>>> getBag(List<E> seed) {
		List<Bag<IMarkovElement<E>>> potentialBags = elements.stream().filter(e -> e.getBag(seed).isPresent())
				.map(e -> e.getBag(seed).get()).collect(Collectors.toList());
		if (potentialBags.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(createBag(potentialBags));
	}

	@Override
	public List<List<E>> getStartElements() {
		return startElements;
	}

	public abstract Bag<IMarkovElement<E>> createBag(List<Bag<IMarkovElement<E>>> potentialBags);

	// @Override
	// public Optional<Bag<IMarkovElement<E>>> getSeedBag(NSeed<F> seed) {
	// List<Bag<IMarkovElement<E>>> potentialBags = elements.stream().filter(e
	// -> e.getSeedBag(seed).isPresent())
	// .map(e -> e.getSeedBag(seed).get()).collect(Collectors.toList());
	// if (potentialBags.isEmpty()) {
	// return Optional.empty();
	// }
	// return Optional.of(new CombinedBag<IMarkovElement<E>>(potentialBags));
	// }
	// @Override
	// public Collection<NSeed<F>> getAllSeeds() {
	// return elements.stream().flatMap(e ->
	// e.getAllSeeds().stream()).collect(Collectors.toSet());
	// }
}
