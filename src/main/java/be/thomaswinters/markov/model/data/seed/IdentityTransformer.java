package be.thomaswinters.markov.model.data.seed;

import java.io.Serializable;

public class IdentityTransformer<E,F> implements INSeedTransformer<E,F>, Serializable {

	private static final long serialVersionUID = -4414547080709270846L;

	@SuppressWarnings("unchecked")
	@Override
	public F transform(E element) {
		return  (F) element;
	}

	@Override
	public String toString() {
		return "IdentityTransformer";
	}

}