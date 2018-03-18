package be.thomaswinters.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomPicker {
	private static final Random RANDOM = new Random();

	public static Set<Integer> pickConsequtiveIndices(int amount, int length) {
		if (amount > length) {
			throw new IllegalArgumentException("Amount must be smaller than the length");
		}
		int start = RANDOM.nextInt(length - amount);
		Set<Integer> result = new HashSet<Integer>(amount);
		for (int i = 0; i < amount; i++) {
			result.add(start + i);
		}
		return result;

	}

	public static Set<Integer> pickRandomUniqueIndices(int amount, int length) {
		if (amount > length) {
			throw new IllegalArgumentException("Amount must be smaller than the length");
		}
		Set<Integer> result = new HashSet<Integer>();

		for (int i = 0; i < amount; i++) {
			int randomInt = RANDOM.nextInt(length - i);
			int skipAmount = calculateSkipAmount(result, randomInt);
			result.add(randomInt + skipAmount);
		}

		return result;
	}

	public static int calculateSkipAmount(Set<Integer> indices, int possibleIndex) {
		int skipAmount = 0;
		int previous = skipAmount;
		do {
			previous = skipAmount;
			final int totalIndex = possibleIndex + skipAmount;
			skipAmount = (int) indices.stream().filter(e -> e <= totalIndex).mapToInt(e -> e).count();

		} while (skipAmount != previous);
		return skipAmount;
	}

	public static void main(String[] args) {
		System.out.println(pickRandomUniqueIndices(8, 8));
	}

	public static void setSeed(long seed) {
		RANDOM.setSeed(seed);
	}
}
