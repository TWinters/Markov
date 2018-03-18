package be.thomaswinters.sentencemarkov;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

import be.thomaswinters.markov.model.state.IChainCompiler;

public class StringConjunctor implements IChainCompiler<String, String>, Serializable {

	private static final long serialVersionUID = -6801202653656576103L;
	private final String combinationString;

	public StringConjunctor(String combinationString) {
		this.combinationString = combinationString;
	}

	@Override
	public String combine(Collection<? extends String> elements) {
		return elements.stream().collect(Collectors.joining(combinationString));
	}

}
