package be.thomaswinters.markov.model.data.ngram;

import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.bags.impl.AdditiveBag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public class AdditiveNGram<E, F> extends CompositeNGram<E, F> {

	private static final long serialVersionUID = 3441288015078087909L;

	public AdditiveNGram(Collection<? extends INGram<E, F>> elements) {
		super(elements);
	}

	@Override
	public Bag<IMarkovElement<E>> createBag(List<Bag<IMarkovElement<E>>> potentialBags) {
		return new AdditiveBag<IMarkovElement<E>>(potentialBags);
	}

}
