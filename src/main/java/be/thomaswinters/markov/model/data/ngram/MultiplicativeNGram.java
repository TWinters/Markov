package be.thomaswinters.markov.model.data.ngram;

import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.bags.impl.MultiplicativeBag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

/**
 * Multiplies the results of several other ngrams together.
 * 
 * @author Thomas Winters
 *
 * @param <E>
 * @param <F>
 */
public class MultiplicativeNGram<E, F> extends CompositeNGram<E, F> {

	private static final long serialVersionUID = 3441288015078087909L;

	public MultiplicativeNGram(Collection<? extends INGram<E, F>> elements) {
		super(elements);
	}

	@Override
	public Bag<IMarkovElement<E>> createBag(List<Bag<IMarkovElement<E>>> potentialBags) {
		return new MultiplicativeBag<IMarkovElement<E>>(potentialBags);
	}

}
