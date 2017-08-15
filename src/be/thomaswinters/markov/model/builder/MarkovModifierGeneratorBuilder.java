package be.thomaswinters.markov.model.builder;

import java.util.List;

import com.google.common.collect.ImmutableList;

import be.thomaswinters.markov.model.data.bags.modifier.IBagModifier;
import be.thomaswinters.markov.model.data.element.IMarkovElement;
import be.thomaswinters.markov.model.data.ngram.ModifiedNGram;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.markov.model.generator.MarkovGenerator;

public class MarkovModifierGeneratorBuilder<E, F> implements IMarkovGeneratorBuilder<E, F> {
	private final IMarkovGeneratorBuilder<E, F> builder;
	private final IBagModifier<E, IMarkovElement<E>> modifier;

	public MarkovModifierGeneratorBuilder(IMarkovGeneratorBuilder<E, F> builder,
			IBagModifier<E, IMarkovElement<E>> modifier) {
		this.builder = builder;
		this.modifier = modifier;
	}

	@Override
	public void addLine(ImmutableList<E> words, int weight) {
		builder.addLine(words, weight);
	}

	@Override
	public void addBegin(List<E> words, int weight) {
		builder.addBegin(words, weight);
	}

	@Override
	public IMarkovGenerator<E, F> build() {
		return new MarkovGenerator<E,F>(new ModifiedNGram<E,F>(builder.build().getNGram(), modifier));
	}

}
