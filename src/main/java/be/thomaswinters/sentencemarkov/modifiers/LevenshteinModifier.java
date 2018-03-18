package be.thomaswinters.sentencemarkov.modifiers;

import be.thomaswinters.sentencemarkov.util.LevenshteinDistance;

public class LevenshteinModifier extends WordSimilarityMultiplicator {

	public LevenshteinModifier(int weight, int decay, boolean allowSameWord) {
		super(weight, decay, allowSameWord);
	}

	@Override
	public int amountOfSimilarCharacters(String t1, String t2) {
		return Integer.max(t1.length(), t2.length()) - LevenshteinDistance.computeLevenshteinDistance(t1, t2);
	}

}
