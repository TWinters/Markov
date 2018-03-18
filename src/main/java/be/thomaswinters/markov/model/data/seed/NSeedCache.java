package be.thomaswinters.markov.model.data.seed;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

public class NSeedCache<E, F> implements INSeedCreator<E, F>, Serializable {

	private static final long serialVersionUID = -3197468684281039990L;
	private final INSeedTransformer<E, F> IDENTITY_TRANSFORMER = new IdentityTransformer<>();

	private final Map<List<E>, ImmutableList<F>> listTransformations;
	private final Map<List<F>, NSeed<F>> pool;
	private final INSeedCreator<E, F> creator;

	public NSeedCache(Optional<INSeedCreator<E, F>> seedCreator) {
		this.creator = seedCreator.orElse(new NSeedCreator<E, F>(IDENTITY_TRANSFORMER));
		this.pool = new HashMap<>();
		this.listTransformations = new HashMap<>();
	}

	public NSeedCache() {
		this(Optional.empty());
	}

	public NSeedCache(INSeedCreator<E, F> creator) {
		this(Optional.of(creator));
	}

	public INSeedCreator<E, F> getSeedCreator() {
		return creator;
	}

	@Override
	public ImmutableList<F> transformAll(List<E> wordsArg) {
		if (listTransformations.containsKey(wordsArg)) {
			return listTransformations.get(wordsArg);
		}

		ImmutableList<F> list = creator.transformAll(wordsArg);
		listTransformations.put(wordsArg, list);

		return list;

	}

	@Override
	public NSeed<F> toSeed(List<F> list) {// Check if seed already exists
		NSeed<F> result;
		if (!pool.containsKey(list)) {
			result = creator.toSeed(list);
			pool.put(list, result);
			return result;
		} else {
			result = pool.get(list);
		}

		return result;
	}

	public void clear() {
		this.pool.clear();
		this.listTransformations.clear();
	}
}
