package be.thomaswinters.markov.model.data.element;

import java.io.Serializable;

public class EndMarkovElement<E> implements IMarkovElement<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8501438249063030358L;
	private final E endElement;

	public EndMarkovElement(E endElement) {
		this.endElement = endElement;
	}

	@Override
	public E getElement() {
//		if (!endElement.isPresent()) {
//			throw new IllegalStateException("End element does not contain an element.");
//		}
		return endElement;
	}

	@Override
	public boolean marksEnd() {
		return true;
	}
	
	@Override
	public String toString() {
		return endElement.toString() + " (end)";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endElement == null) ? 0 : endElement.hashCode());
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
		@SuppressWarnings("rawtypes")
		EndMarkovElement other = (EndMarkovElement) obj;
		if (endElement == null) {
			if (other.endElement != null)
				return false;
		} else if (!endElement.equals(other.endElement))
			return false;
		return true;
	}

}
