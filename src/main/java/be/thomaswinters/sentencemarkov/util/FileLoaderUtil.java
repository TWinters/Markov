package be.thomaswinters.sentencemarkov.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;

public class FileLoaderUtil {

	public static ImmutableList<String> getLinesFromFiles(Collection<File> files) {
		return ImmutableList.copyOf(files.stream().flatMap(e -> getLinesFromFile(e).stream()).iterator());
	}

	public static List<String> getLinesFromFile(File file) {
		try {
			return Files.readAllLines(file.toPath(), Charsets.UTF_8);
		} catch (IOException e1) {
			try {
				return Files.readAllLines(file.toPath(), Charsets.UTF_16);
			} catch (IOException e2) {
				e2.printStackTrace();
				throw new RuntimeException(e2);
			}
		}
	}
}
