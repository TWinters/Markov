package be.thomaswinters.markov.model.data.seed;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

public class NSeed<F> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7036881244855904187L;
	private final List<F> words;

	/*-********************************************-*
	 *  Constructors
	 *-********************************************-*/
	public NSeed(List<F> words) {
//		this.words = ImmutableList.copyOf(words);
		this.words = words;
	}

	public NSeed(F word) {
		this(ImmutableList.of(word));
	}
	/*-********************************************-*/

	/*-********************************************-*
	 *  Getters
	 *-********************************************-*/
	public int getSize() {
		return words.size();
	}

	public List<F> getWords() {
		return words;
	}
	/*-********************************************-*/

//	public NSeed<F> addNext(E word, INSeedCreator<E,F> creator) {
//		List<E> seed = new ArrayList<E>(words.subList(1, words.size()));
//		seed.add(word);
//		return creator.create(ImmutableList.copyOf(seed));
//	}
//
//	public NSeed<F> getSuffix(int argSize, INSeedCreator<E,F> creator) {
//		if (this.getSize() < argSize) {
//			throw new IllegalArgumentException(
//					"Can't get suffix of size " + argSize + " on seed " + this + " of size " + getSize());
//		}
//		return creator.create(words.subList(getSize() - argSize, getSize()));
//	}

	/*-********************************************-*
	 *  Comparing & Equals
	 *-********************************************-*/
	// @Override
	// public int compareTo(NSeed o) {
	// for (int i = 0; i < Math.min(this.getWords().size(),
	// o.getWords().size()); i++) {
	// int compare = this.getWords().get(i).compareTo(o.getWords().get(i));
	// if (compare != 0) {
	// return compare;
	// }
	// }
	// return this.getSize() - o.getSize();
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((words == null) ? 0 : words.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		NSeed<F> other = (NSeed<F>) obj;
		if (words == null) {
			if (other.words != null)
				return false;
		} else if (!words.equals(other.words))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + words.stream().map(e -> e.toString()).collect(Collectors.joining(" ")) + "]";
	}

	/*-********************************************-*/

}
