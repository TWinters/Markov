package be.thomaswinters.markov.model.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.bags.WriteableBag;
import be.thomaswinters.markov.model.data.bags.impl.MutableBag;
import be.thomaswinters.markov.model.data.element.EndMarkovElement;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.element.MarkovElement;
import be.thomaswinters.markov.model.data.ngram.FixedSizeNGram;
import be.thomaswinters.markov.model.data.seed.NSeed;
import be.thomaswinters.markov.model.data.seed.NSeedCache;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.markov.model.generator.MarkovGenerator;

public class MarkovGeneratorBuilder<E, F> implements IMarkovGeneratorBuilder<E, F> {
	private final NSeedCache<E, F> seedCache;
	private final int skipSize;
	private final int seedSize;
	private final int ngramWeight;
	private final Map<NSeed<F>, WriteableBag<IMarkovElement<E>>> ngramMap;
	private final List<List<E>> startSeeds;

	/*-********************************************-*
	 *  Constructor
	 *-********************************************-*/
	public MarkovGeneratorBuilder(int seedSize, int skipSize, int ngramWeight,
			Optional<NSeedCache<E, F>> seedCreator) {
		this.seedSize = seedSize;
		this.skipSize = skipSize;
		this.ngramWeight = ngramWeight;
		this.ngramMap = new HashMap<>();
		this.startSeeds = new ArrayList<>();
		this.seedCache = seedCreator.orElse(new NSeedCache<E,F>(Optional.empty()));
	}

	public MarkovGeneratorBuilder(int seedSize, int skipSize, Optional<NSeedCache<E, F>> seedCreator) {
		this(seedSize, skipSize, 1, seedCreator);
	}

	public MarkovGeneratorBuilder(int seedSize, Optional<NSeedCache<E, F>> seedCreator) {
		this(seedSize, 0, seedCreator);
	}

	public MarkovGeneratorBuilder(int seedSize, int skipSize, int weight) {
		this(seedSize, skipSize, weight, Optional.empty());
	}

	public MarkovGeneratorBuilder(int seedSize, int skipSize) {
		this(seedSize, skipSize, Optional.empty());
	}

	public MarkovGeneratorBuilder(int seedSize) {
		this(seedSize, Optional.empty());
	}
	/*-********************************************-*/

	@Override
	public void addLine(ImmutableList<E> words, int weight) {
		if (words.size() < seedSize + skipSize) {
			return;
		}
		
		ImmutableList<F> transformedWords = seedCache.transformAll(words);
		
		// Mark starting point
		addStartOfLineOccurrence(words.subList(0, seedSize + skipSize), weight);

		for (int i = 0; i < words.size() - seedSize - skipSize; i++) {
			ImmutableList<F> seed = transformedWords.subList(i, i + seedSize);
			E wordAfter = words.get(i + seedSize + skipSize);

			// Check if last word
			if (i < words.size() - seedSize - 1) {
				increaseOccurrence(seed, wordAfter, weight);
			} else {
				increaseEndOfLineOccurrence(seed, wordAfter, weight);
			}
		}

		// Mark end of line
		// ImmutableList<E> endSeed = words.subList(words.size() - seedSize,
		// words.size());
		// increaseEndOfLineOccurrence(endSeed);

	}

	private void increaseOccurrence(ImmutableList<F> seedWords, E occurringElement, int weight) {
		addToChain(seedWords, new MarkovElement<E>(occurringElement), weight);
	}

	private void increaseEndOfLineOccurrence(ImmutableList<F> seedWords, E occuringElement, int weight) {
		addToChain(seedWords, new EndMarkovElement<E>(occuringElement), weight);
	}

	private void addToChain(ImmutableList<F> seedWords, IMarkovElement<E> element, int wordWeight) {
		NSeed<F> seed = seedCache.toSeed(seedWords);
		if (!ngramMap.containsKey(seed)) {
			ngramMap.put(seed, new MutableBag<IMarkovElement<E>>());
		}
		ngramMap.get(seed).add(element, this.ngramWeight*wordWeight);
	}

	@Override
	public void addBegin(List<E> seed, int weight) {
		if (seed.size() > seedSize + skipSize) {
			addStartOfLineOccurrence(seed.subList(0, seedSize + skipSize), weight);
		}

	}

	private void addStartOfLineOccurrence(List<E> startSeed, int weight) {
		startSeeds.add(startSeed);
	}

	/*-********************************************-*
	 *  Build
	 *-********************************************-*/
	@Override
	public IMarkovGenerator<E, F> build() {
		// Map<NSeed<E>, Bag<IMarkovElement<E>>> buildChain = new HashMap<>();
		//
		// for (Entry<NSeed<E>, WriteableBag<IMarkovElement<E>>> entry :
		// ngramMap.entrySet()) {
		// Bag<IMarkovElement<E>> imBag = new ImmutableBag<>(entry.getValue());
		// buildChain.put(entry.getKey(), imBag);
		// }

		// Clear seedcache for efficiency
		seedCache.clear();
		
		// Return with the creator instead of the cache
		FixedSizeNGram<E, F> model = new FixedSizeNGram<>(seedSize, skipSize, ngramMap, startSeeds, seedCache.getSeedCreator());
		
		return new MarkovGenerator<>(model);
	}

	/*-********************************************-*/

}
