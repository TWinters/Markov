package be.thomaswinters.markov.model.generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.element.MarkovElement;
import be.thomaswinters.markov.model.data.ngram.INGram;

public class MarkovGenerator<E,F> implements IMarkovGenerator<E,F>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1978758598875218023L;
	private final INGram<E,F> ngram;

	public MarkovGenerator(INGram<E,F> model) {
		this.ngram = model;
	}

	/*-********************************************-*
	 *  Generate
	 *-********************************************-*/
	public static Random random = new Random();

	public List<E> generateSequence() {
		List<E> initialSeed = getRandomFirstSeed();
		return generateSequence(initialSeed);
	}

	public List<E> generateSequence(List<E> initialSeed) {

		// Initialise resulting array
		List<E> result = new ArrayList<E>(initialSeed);

		boolean reachedEnd = false;
		while (!reachedEnd) {

			// Stop if there is no bag with the current seed
			if (!hasBag(result)) {
				reachedEnd = true;
			} else {
				IMarkovElement<E> chosenWord = chooseNextWord(result);

				result.add(chosenWord.getElement());
				reachedEnd = chosenWord.marksEnd();
			}
		}

		return result;
	}

	@Override
	public Optional<IMarkovElement<E>> generateNextElement(List<E> currentElements) {
		if (currentElements.isEmpty()) {
			return Optional.of(new MarkovElement<E>(getRandomFirstSeed().get(0)));
		}

		if (!hasBag(currentElements)) {
			return Optional.empty();
		}

		IMarkovElement<E> chosenWord = chooseNextWord(currentElements);
		return Optional.of(chosenWord);

	}

	private IMarkovElement<E> chooseNextWord(List<E> seed) {
		Optional<Bag<IMarkovElement<E>>> bagOptional = getBag(seed);
		if (!bagOptional.isPresent()) {
			throw new RuntimeException("No bag present for " + seed);
		}

		Bag<IMarkovElement<E>> bag = bagOptional.get();
		
		int amountOfElements = bag.getAmountOfElements();
		if (amountOfElements < 0) {
			System.out.println("Overflow in bag");

			amountOfElements = bag.getAmountOfElements();
			amountOfElements = Integer.MAX_VALUE;
		}
		int indexTaken = random.nextInt(amountOfElements);
		return bag.get(indexTaken);
	}

	public INGram<E,F> getNGram() {
		return ngram;
	}

	public List<E> getRandomFirstSeed() {
		List<List<E>> startElements = ngram.getStartElements();
		return startElements.get(random.nextInt(startElements.size()));
	}

	/*-********************************************-*/

	/*-********************************************-*
	 *  Helpers
	 *-********************************************-*/

	private boolean hasBag(List<E> seed) {
		return ngram.getBag(seed).isPresent();
	}

	@Override
	public Optional<Bag<IMarkovElement<E>>> getBag(List<E> current) {
		return ngram.getBag(current);
	}

	/*-********************************************-*/

}
