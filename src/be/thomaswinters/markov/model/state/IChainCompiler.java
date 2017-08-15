package be.thomaswinters.markov.model.state;

import java.util.Collection;

public interface IChainCompiler<E, C> {
	C combine(Collection<? extends E> elements);
}
