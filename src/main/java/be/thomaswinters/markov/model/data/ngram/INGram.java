package be.thomaswinters.markov.model.data.ngram;

import java.util.List;
import java.util.Optional;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public interface INGram<E,F> {
	Optional<Bag<IMarkovElement<E>>> getBag(List<E> seed);
	List<List<E>> getStartElements();
	
	// DEBUG
//	Optional<Bag<IMarkovElement<E>>> getSeedBag(NSeed<F> seed);
//	Collection<NSeed<F>> getAllSeeds();
}
