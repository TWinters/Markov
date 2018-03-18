package be.thomaswinters.sentencemarkov.learner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

import be.thomaswinters.markov.model.generator.IMarkovGenerator;

public interface IMarkovSentenceGeneratorBuilder {
	void addLine(String text, int weight);

	default void addLine(String text) {
		addLine(text, 1);
	}

	default void addLines(Collection<? extends String> texts, int weight) {
		texts.stream().forEach(e -> addLine(e, weight));
	}
	default void addLines(Collection<? extends String> texts) {
		addLines(texts, 1);
	}
	default void addFile(File file, int weight) throws IOException {
		List<String> resultingLines = Files.readAllLines(file.toPath());
		addLines(resultingLines, weight);
	}
	default void addFile(File file) throws IOException {
		addFile(file, 1);
	}

	IMarkovGenerator<String,?> build();
}
