package be.thomaswinters.sentencemarkov.modifiers;

public class AssonanceModifier extends WordSimilarityMultiplicator {

	public AssonanceModifier(int weight, int decay, boolean allowSameWord) {
		super(weight, decay, allowSameWord);
	}

	@Override
	public int amountOfSimilarCharacters(String t1, String t2) {
		int length = Integer.min(t1.length(), t2.length());
		for (int i = length - 1; i >= 0; i--) {
			if (t1.charAt(i) != t2.charAt(i)) {
				int amountOfSameCharacters = (length - 1) - i;
				return amountOfSameCharacters;
			}
		}
		return 0;
	}

}
