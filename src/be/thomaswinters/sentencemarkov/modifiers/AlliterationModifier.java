package be.thomaswinters.sentencemarkov.modifiers;

public class AlliterationModifier extends WordSimilarityMultiplicator {

	public AlliterationModifier(int weight, int decay, boolean allowSameWord) {
		super(weight, decay, allowSameWord);
	}

	@Override
	public int amountOfSimilarCharacters(String t1, String t2) {

		int length = Integer.min(t1.length(), t2.length());
		for (int i = 0; i < length; i++) {
			if (t1.charAt(i) != t2.charAt(i)) {
				return i;
			}
		}
		return length;
	}

}
