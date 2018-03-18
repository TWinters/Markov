package be.thomaswinters.markov.model.data;

public class Weighted<E> {
	private final E element;
	private final int weight;

	public Weighted(E element, int weight) {
		this.element = element;
		this.weight = weight;
	}

	public E getElement() {
		return element;
	}

	public int getWeight() {
		return weight;
	}
}
