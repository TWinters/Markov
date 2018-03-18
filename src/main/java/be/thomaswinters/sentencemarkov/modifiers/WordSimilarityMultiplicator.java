package be.thomaswinters.sentencemarkov.modifiers;

import java.util.List;

import be.thomaswinters.markov.model.data.bags.modifier.IBagMultiplicator;
import be.thomaswinters.markov.model.data.element.IMarkovElement;

public abstract class WordSimilarityMultiplicator implements IBagMultiplicator<String, IMarkovElement<String>> {

	private final int weight;
	private final int decay;

	private final boolean allowSameWord;

	/**
	 * Multiplies the weight of an alliteration for weight*decay for every
	 * letter the first word to the right is an alliteration, (weight-i)*decay
	 * for every next word to the right from 1 to weight amount of words.
	 * 
	 * @param weight
	 * @param decay
	 */
	public WordSimilarityMultiplicator(int weight, int decay, boolean allowSameWord) {
		this.weight = weight;
		this.decay = decay;
		this.allowSameWord = allowSameWord;
	}

	@Override
	public int calculateMultiplier(List<String> seed, IMarkovElement<String> current) {
		int totalMultiplier = 1;
		for (int i = 0; i < Integer.min(seed.size(), weight); i++) {
			String element = seed.get(seed.size() - 1 - i);
			if (allowSameWord || !element.equals(current.getElement())) {
				totalMultiplier += amountOfSimilarCharacters(element, current.getElement()) * (weight - i) * decay;
			}
		}
		return totalMultiplier;
	}

	public abstract int amountOfSimilarCharacters(String t1, String t2);

}
