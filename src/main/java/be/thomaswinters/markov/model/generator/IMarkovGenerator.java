package be.thomaswinters.markov.model.generator;

import java.util.List;
import java.util.Optional;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.ngram.INGram;

public interface IMarkovGenerator<E,F> {
	List<E> generateSequence();
	List<E> generateSequence(List<E> current);
	Optional<IMarkovElement<E>> generateNextElement(List<E> current);
	INGram<E,F> getNGram();
	
	default boolean canGenerateNext(List<E> current) {
		return generateNextElement(current).isPresent();
	}
	Optional<Bag<IMarkovElement<E>>> getBag(List<E> current);

}
	