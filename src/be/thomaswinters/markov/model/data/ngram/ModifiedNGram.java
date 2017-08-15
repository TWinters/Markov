package be.thomaswinters.markov.model.data.ngram;

import java.util.List;
import java.util.Optional;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.bags.impl.ModifiedBag;
import be.thomaswinters.markov.model.data.bags.modifier.IBagModifier;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public class ModifiedNGram<E, F> implements INGram<E, F> {

	private final INGram<E, F> ngram;
	private final IBagModifier<E, IMarkovElement<E>> modifier;

	public ModifiedNGram(INGram<E, F> ngram, IBagModifier<E, IMarkovElement<E>> modifier) {
		this.ngram = ngram;
		this.modifier = modifier;
	}

	@Override
	public Optional<Bag<IMarkovElement<E>>> getBag(List<E> seed) {
		return ngram.getBag(seed).map(bag->new ModifiedBag<E, IMarkovElement<E>>(bag, modifier, seed));
	}

	@Override
	public List<List<E>> getStartElements() {
		return ngram.getStartElements();
	}

}
