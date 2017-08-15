package be.thomaswinters.sentencemarkov;

import java.io.Serializable;
import java.util.stream.Collectors;

import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.textgenerator.ITextGenerator;

public class MarkovSentenceGenerator implements Serializable, ITextGenerator {
	private static final long serialVersionUID = 5380823619141206370L;
	private final IMarkovGenerator<String, ?> chain;

	public MarkovSentenceGenerator(IMarkovGenerator<String, ?> chain) {
		this.chain = chain;
	}

	public String generateText() {
		return chain.generateSequence().stream().collect(Collectors.joining(" "));
	}

	public IMarkovGenerator<String, ?> getGenerator() {
		return chain;
	}

	// public String generateText(String text) {
	// ImmutableList<String> words = MarkovSentenceUtil.textToList(text);
	//
	// }
}
