package be.thomaswinters.markov.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.thomaswinters.markov.facade.MarkovSentenceGeneratorFacade;
import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.markov.model.generator.MarkovGenerator;
import be.thomaswinters.sentencemarkov.learner.MarkovSentenceLearner;

public class InteractiveMarkovChainFacadeTest {

	@Before
	public void setup() {
		MarkovGenerator.random.setSeed(123456789);
	}

	@Test
	public void test() {
		// Build chain
		MarkovSentenceLearner learner = new MarkovSentenceLearner(1);
		learner.addLine("hello there world I am saying hi to you world yo world sup world");
		learner.addLine("world are what I love");

		IMarkovGenerator<String, ?> chain1 = learner.build();

		// Add chain to facade
		MarkovSentenceGeneratorFacade facade = new MarkovSentenceGeneratorFacade(chain1);
		assertEquals("", facade.getTotalText());

		// Add manual words
		facade.addWord("hello");
		assertEquals("hello", facade.getTotalText());
		facade.addWord("world");
		assertEquals("hello world", facade.getTotalText());
		facade.startNewLine();
		assertEquals("hello world\n", facade.getTotalText());
		facade.addWord("saying");
		assertEquals("hello world\nsaying", facade.getTotalText());

		// Add generated words
		facade.addGeneratedWord();
		assertEquals("hello world\nsaying hi", facade.getTotalText());
		facade.addGeneratedWord();
		assertEquals("hello world\nsaying hi to", facade.getTotalText());
		facade.addGeneratedWord();
		assertEquals("hello world\nsaying hi to you", facade.getTotalText());

	}

}
