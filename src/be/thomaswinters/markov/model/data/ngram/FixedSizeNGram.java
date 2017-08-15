package be.thomaswinters.markov.model.data.ngram;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.seed.INSeedCreator;
import be.thomaswinters.markov.model.data.seed.NSeed;

/**
 * N gram where N = seedSize+1 that easily maps n-1 elements to their potential
 * last element.
 * 
 * @author Thomas Winters
 *
 * @param <E>
 */
public class FixedSizeNGram<E, F> implements INGram<E, F>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4928858518919809111L;
	private final INSeedCreator<E, F> seedCreator;
	private final int seedSize;
	private final int skipSize;
	private final Map<NSeed<F>, ? extends Bag<IMarkovElement<E>>> seedMap;
	private final ImmutableList<List<E>> startSeeds;

	public FixedSizeNGram(int seedSize, int skipSize, Map<NSeed<F>, ? extends Bag<IMarkovElement<E>>> argChain,
			List<? extends List<E>> startSeeds, INSeedCreator<E, F> seedCreator) {
		this.seedSize = seedSize;
		this.skipSize = skipSize;
		this.startSeeds = ImmutableList.copyOf(startSeeds);
		this.seedCreator = seedCreator;

		// ImmutableMap.Builder<NSeed<E>, ImmutableBag<IMarkovElement<E>>>
		// mapBuilder = new ImmutableMap.Builder<>();
		//
		// for (Entry<? extends NSeed<E>, ? extends Bag<? extends
		// IMarkovElement<E>>> entry : argChain.entrySet()) {
		// mapBuilder.put(entry.getKey(), new
		// ImmutableBag<IMarkovElement<E>>(entry.getValue()));
		// }

		// this.seedMap = mapBuilder.build();
		this.seedMap = argChain;
	}

	public FixedSizeNGram(int seedSize, Map<NSeed<F>, ? extends Bag<IMarkovElement<E>>> argChain,
			List<? extends List<E>> startSeeds, INSeedCreator<E, F> seedCreator) {
		this(seedSize, 0, argChain, startSeeds, seedCreator);
	}

	public int getSeedSize() {
		return seedSize;
	}

	@Override
	public Optional<Bag<IMarkovElement<E>>> getBag(List<E> seedList) {
		Optional<NSeed<F>> seed = createSeed(seedList);
		if (!seed.isPresent()) {
			return Optional.empty();
		}
		return getSeedBag(seed.get());
	}

//	@Override
	public Optional<Bag<IMarkovElement<E>>> getSeedBag(NSeed<F> seed) {
		if (!seedMap.containsKey(seed)) {
			return Optional.empty();
		}
		return Optional.of(seedMap.get(seed));
	}

//	@Override
//	public Collection<NSeed<F>> getAllSeeds() {
//		return seedMap.keySet();
//	}

	@Override
	public List<List<E>> getStartElements() {
		return startSeeds;
	}

	private Optional<NSeed<F>> createSeed(List<E> seedList) {
		if (seedList.size() < seedSize + skipSize) {
			return Optional.empty();
		}
		List<E> subList = seedList.subList(seedList.size() - seedSize - skipSize, seedList.size() - skipSize);

		return Optional.of(seedCreator.create(subList));

	}
}
