package be.thomaswinters.markov.model.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.thomaswinters.markov.model.data.bags.Bag;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;

public class MarkovChainGeneratorStateHolder<E, C> {
	private final IMarkovGenerator<E,?> generator;
	private final IChainCompiler<E, C> compiler;
	private final List<C> finishedText;
	private final List<E> currentText;

	/*-********************************************-*
	 *  Constructor
	 *-********************************************-*/
	public MarkovChainGeneratorStateHolder(IMarkovGenerator<E,?> generator, IChainCompiler<E, C> chainCompiler) {
		this.generator = generator;
		this.compiler = chainCompiler;
		finishedText = new ArrayList<C>();
		currentText = new ArrayList<>();
	}
	/*-********************************************-*/

	/*-********************************************-*
	 *  State
	 *-********************************************-*/
	public void addWord(E text) {
		currentText.add(text);
	}
	/*-********************************************-*/

	/*-********************************************-*
	 *  Generate word
	 *-********************************************-*/
	public Optional<IMarkovElement<E>> generateWord() {
		return generator.generateNextElement(currentText);
	}

	public boolean canGenerateWord() {
		return generator.canGenerateNext(currentText);
	}

	public void addGeneratedWord() {
		Optional<IMarkovElement<E>> result = generateWord();
		if (!result.isPresent()) {
			throw new IllegalStateException("Can't generate word on current text: " + getTotalText());
		}

		addWord(result.get().getElement());
		if (result.get().marksEnd()) {
			startNewLine();
		}
	}

	/*-********************************************-*/

	/*-********************************************-*
	 *  Current text
	 *-********************************************-*/
	public List<C> getTotalText() {
		List<C> total = new ArrayList<>(finishedText);
		total.add(getCurrentText());
		return total;
	}

	private C getCurrentText() {
		return compiler.combine(currentText);
	}

	public void startNewLine() {
		finishedText.add(getCurrentText());
		currentText.clear();
	}

	public IMarkovGenerator<E,?> getMarkovGenerator() {
		return generator;
	}

	public Optional<Bag<IMarkovElement<E>>> getNextBag() {
		return generator.getBag(currentText);
	}

	/*-********************************************-*/

}
