package be.thomaswinters.sentencemarkov.fixers;

import be.thomaswinters.sentencemarkov.util.SentenceUtil;

public class PunctuationFixer implements ISentenceFixer {

	@Override
	public String fix(String text) {
		if (SentenceUtil.isPunctuation(text.charAt(text.length() - 1))) {
			return text;
		}
		return text.trim() + ".";
	}

}
