package be.thomaswinters.sentencemarkov.learner;

import java.util.Optional;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.builder.IMarkovGeneratorBuilder;
import be.thomaswinters.markov.model.builder.MarkovGeneratorBuilder;
import be.thomaswinters.markov.model.data.seed.NSeedCache;
import be.thomaswinters.markov.model.data.seed.NSeedCreator;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.sentencemarkov.NSeedSentenceTransformer;

public class MarkovSentenceLearner implements IMarkovSentenceGeneratorBuilder {
	public static final int DEFAULT_AMOUNT_OF_SKIP_WORDS = 0;
	public static final int DEFAULT_AMOUNT_OF_WORDS = 3;

	private final boolean addAllSentenceStarts;
	private final IMarkovGeneratorBuilder<String, ?> builder;

	public MarkovSentenceLearner(boolean addAllSenteceStarts, IMarkovGeneratorBuilder<String, ?> builder) {
		this.addAllSentenceStarts = addAllSenteceStarts;
		this.builder = builder;
	}

	public MarkovSentenceLearner(IMarkovGeneratorBuilder<String, String> builder) {
		this(false, builder);
	}

	public MarkovSentenceLearner(int amountOfWords, int skipWords) {
		this(new MarkovGeneratorBuilder<String, String>(amountOfWords, skipWords, Optional
				.of(new NSeedCache<String, String>(new NSeedCreator<String, String>(new NSeedSentenceTransformer())))));
	}

	public MarkovSentenceLearner(int amountOfWords) {
		this(amountOfWords, DEFAULT_AMOUNT_OF_SKIP_WORDS);
	}

	public MarkovSentenceLearner() {
		this(DEFAULT_AMOUNT_OF_WORDS, DEFAULT_AMOUNT_OF_SKIP_WORDS);
	}

	@Override
	public void addLine(String text, int ngramWeight) {
		ImmutableList<String> words = textToList(text);
		builder.addLine(words, ngramWeight);

		if (addAllSentenceStarts) {
			int i = 0;
			while (i < words.size()) {
				while (i < words.size() && !words.get(i).contains(".") && !words.get(i).contains("!")
						&& !words.get(i).contains("?")) {
					i++;
				}
				i++;
				if (i < words.size()) {
					builder.addBegin(words.subList(i, words.size()),ngramWeight);
				}

			}
		}
	}

	@Override
	public IMarkovGenerator<String, ?> build() {
		return builder.build();
	}

	public ImmutableList<String> textToList(String text) {
		return ImmutableList.copyOf(Stream.of(text.split(" ")).filter(e -> e.trim().length() != 0).iterator());
	}

}
