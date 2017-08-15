package be.thomaswinters.markov.test;

import java.io.File;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import be.thomaswinters.markov.model.generator.IMarkovGenerator;
import be.thomaswinters.sentencemarkov.MarkovSentenceGenerator;
import be.thomaswinters.sentencemarkov.learner.MarkovSentenceLearner;

public class MarkovChainBuilderTest {

	@Test
	public void Simple_Test() {

		MarkovSentenceLearner learner = new MarkovSentenceLearner(1);
		learner.addLine("hello there world I am saying hello to you world yo world sup world hi");
		learner.addLine("world are what I love");

		IMarkovGenerator<String,?> chain = learner.build();
		for (int i = 0; i < 10; i++) {
			chain.generateSequence();
		}
	}

	@Test
	public void Capitals_Test() {

		MarkovSentenceLearner learner = new MarkovSentenceLearner(1);
		learner.addLine("HeLLO therE thIs IS A test");

		MarkovSentenceGenerator chain = new MarkovSentenceGenerator(learner.build());
		assertEquals("HeLLO therE thIs IS A test", chain.generateText());
		

		assertTrue(chain.getGenerator().canGenerateNext(Arrays.asList("hello","there")));
		assertFalse(chain.getGenerator().canGenerateNext(Arrays.asList("hello","may")));
	}
	
	@Test
	public void SkipGram_Test() {
		MarkovSentenceLearner learner = new MarkovSentenceLearner(1,1);
		learner.addLine("this is a test");

		MarkovSentenceGenerator chain = new MarkovSentenceGenerator(learner.build());
		assertEquals("this is a test", chain.generateText());
		

		assertTrue(chain.getGenerator().canGenerateNext(Arrays.asList("this","is")));
		assertTrue(chain.getGenerator().canGenerateNext(Arrays.asList("this","has")));
		assertTrue(chain.getGenerator().canGenerateNext(Arrays.asList("this","is","nothing")));
		assertFalse(chain.getGenerator().canGenerateNext(Arrays.asList("this","has","nothing")));
		
	}

	@Test
	public void Song_Test() throws IOException {

		MarkovSentenceLearner learner = new MarkovSentenceLearner(2);
	
		File file = new File("res/text/taylor.txt");
		File file2 = new File("res/text/trump.txt");
		learner.addFile(file);
		learner.addFile(file2);

		List<String> originalLines = Files.readAllLines(file.toPath());
		originalLines.addAll(Files.readAllLines(file2.toPath()));

		MarkovSentenceGenerator chain = new MarkovSentenceGenerator(learner.build());
		int amountOfLines = 100;

		Set<String> generated = new HashSet<>();
		while (generated.size() < amountOfLines) {
			String text = chain.generateText();

			if (!originalLines.contains(text) && !generated.contains(text)) {
				generated.add(text);
				System.out.println(text);
				if (generated.size() % 2 == 0 && Math.random() > 0.5) {
					System.out.println();
				}
			}

			// System.out.println((originalLines.contains(text) ? "*** " : "") +
			// text);

		}
	}

}
