package be.thomaswinters.markov.model.data.element;

import java.io.Serializable;

public class MarkovElement<E> implements IMarkovElement<E>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5037892737795448054L;
	private final E content;

	public MarkovElement(E content) {
		this.content = content;
	}

	@Override
	public E getElement() {
		return content;
	}

	@Override
	public boolean marksEnd() {
		return false;
	}

	@Override
	public String toString() {
		return content.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		MarkovElement other = (MarkovElement) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	


}