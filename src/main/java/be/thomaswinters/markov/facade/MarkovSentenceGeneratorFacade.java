package be.thomaswinters.markov.facade;

import java.util.Optional;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.ngram.INGram;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.markov.model.state.MarkovChainGeneratorStateHolder;
import be.thomaswinters.sentencemarkov.StringConjunctor;

public class MarkovSentenceGeneratorFacade {
	private final StringConjunctor spaceConjunctor = new StringConjunctor(" ");
	private final StringConjunctor enterConjunctor = new StringConjunctor("\n");
	private final MarkovChainGeneratorStateHolder<String, String> state;

	public MarkovSentenceGeneratorFacade(IMarkovGenerator<String, ?> generator) {
		this.state = new MarkovChainGeneratorStateHolder<>(generator, spaceConjunctor);
	}

	public void addWord(String text) {
		state.addWord(text);
	}

	public boolean canGenerateWord() {
		return state.canGenerateWord();
	}

	public void addGeneratedWord() {
		state.addGeneratedWord();
	}

	public String getTotalText() {
		return enterConjunctor.combine(state.getTotalText());
	}

	public void startNewLine() {
		state.startNewLine();
	}

	public INGram<String, ?> getCurrentNGram() {
		return state.getMarkovGenerator().getNGram();
	}

	public Optional<Bag<IMarkovElement<String>>> getNextBag() {
		return state.getNextBag();
	}
}
