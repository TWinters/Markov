package be.thomaswinters.sentencemarkov.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Optional;

import be.thomaswinters.markov.model.data.Weighted;
import be.thomaswinters.sentencemarkov.MarkovSentenceGenerator;
import be.thomaswinters.sentencemarkov.learner.MarkovSentenceLearner;

public class MarkovSentenceLearnerStorer {

	/**
	 * Stores a Markov sentence generator, or trains one if it doesn't yet exist.
	 * @param serializedPath
	 * @param learner
	 * @param files
	 * @return
	 */
	public static MarkovSentenceGenerator loadGenerator(Optional<String> serializedPath, MarkovSentenceLearner learner,
			Collection<Weighted<File>> files) {

		boolean exists = serializedPath.isPresent() && Files.exists(new File(serializedPath.get()).toPath());
		if (!serializedPath.isPresent() || !exists) {
			System.out.println("Training generator!");

			files.forEach(e -> learner.addLines(FileLoaderUtil.getLinesFromFile(e.getElement()), e.getWeight()));
			// learner.addLines(originalLines);
			MarkovSentenceGenerator result = new MarkovSentenceGenerator(learner.build());

			// Save
			if (serializedPath.isPresent()) {
				try {
					System.out.println("Saving generator");
					FileOutputStream fout = new FileOutputStream(serializedPath.get());
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(result);
					oos.close();
					System.out.println(">> SAVED MODEL TO " + serializedPath.get());
				} catch (IOException e) {
					System.out.println("Couldn't output generator " + result);
					e.printStackTrace();
				}
			}
			return result;
		}

		// Read
		try {
			System.out.println("Started loading generator!");
			FileInputStream fin = new FileInputStream(serializedPath.get());
			ObjectInputStream ois = new ObjectInputStream(fin);
			MarkovSentenceGenerator generator = (MarkovSentenceGenerator) ois.readObject();
			ois.close();
			System.out.println("Successfully read " + generator + " from " + serializedPath.get());
			return generator;
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't read generator on path " + serializedPath.get());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't read generator on path " + serializedPath.get());
			e.printStackTrace();
		}
		return loadGenerator(Optional.empty(), learner, files);
	}
}
