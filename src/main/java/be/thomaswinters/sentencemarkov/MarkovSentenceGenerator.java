package be.thomaswinters.sentencemarkov;

import be.thomaswinters.markov.model.generator.IMarkovGenerator;

import java.io.Serializable;
import java.util.stream.Collectors;

public class MarkovSentenceGenerator implements Serializable {
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
