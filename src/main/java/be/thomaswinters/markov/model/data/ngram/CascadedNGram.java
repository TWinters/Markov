package be.thomaswinters.markov.model.data.ngram;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public class CascadedNGram<E,F> implements INGram<E,F>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9007493234216130378L;
	private final Collection<INGram<E,F>> elements;

	public CascadedNGram(Collection<? extends INGram<E,F>> elements) {
		this.elements = ImmutableList.copyOf(elements);
	}

	private Optional<Bag<IMarkovElement<E>>> findFirstBag(List<E> seed) {
		return elements.stream().filter(e -> e.getBag(seed).isPresent()).map(e -> e.getBag(seed).get()).findFirst();
	}

	@Override
	public Optional<Bag<IMarkovElement<E>>> getBag(List<E> seed) {
		return findFirstBag(seed);
	}

	@Override
	public List<List<E>> getStartElements() {
		return elements.stream().flatMap(e -> e.getStartElements().stream()).collect(Collectors.toList());
	}

	
//	@Override
//	public Optional<Bag<IMarkovElement<E>>> getSeedBag(NSeed<F> seed) {
//		return elements.stream().filter(e -> e.getSeedBag(seed).isPresent()).map(e -> e.getSeedBag(seed).get()).findFirst();
//	}
//	@Override
//	public Collection<NSeed<F>> getAllSeeds() {
//		return elements.stream().flatMap(e -> e.getAllSeeds().stream()).collect(Collectors.toSet());
//	}

}
