package be.thomaswinters.sentencemarkov;

import java.io.Serializable;

import be.thomaswinters.markov.model.data.seed.INSeedTransformer;

public class NSeedSentenceTransformer implements INSeedTransformer<String, String>, Serializable {

	private static final long serialVersionUID = -3607873404807148676L;

	@Override
	public String transform(String element) {
		return element.toLowerCase().replaceAll("[^a-z.,?!]+", "");
	}

	@Override
	public String toString() {
		return "NSeedSentenceTransformer";
	}

}
