package be.thomaswinters.markov.model.data.element;

public interface IMarkovElement<E> {
	E getElement();
	boolean marksEnd();
}
