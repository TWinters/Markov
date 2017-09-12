package be.thomaswinters.sentencemarkov.fixers;

import java.util.OptionalInt;

import be.thomaswinters.sentencemarkov.fixers.ISentenceFixer;
import be.thomaswinters.sentencemarkov.util.SentenceUtil;

public class TweetShortenerFixer implements ISentenceFixer {

	public static final int MAX_TWEET_LENGTH = 140;

	@Override
	public String fix(String tweet) {

		// Cut off sentences before applying other things
		if (tweet.length() > (MAX_TWEET_LENGTH - 1)) {

			// Try shortening to premise & conclusion
			OptionalInt conclusionStart = SentenceUtil.getLastSentenceEndIndex(tweet.substring(0, tweet.length() - 3));
			OptionalInt premiseEnd = SentenceUtil.getFirstSentenceEndIndex(tweet);
			if (premiseEnd.isPresent() && conclusionStart.isPresent()) {
				String premiseConclusion = tweet.substring(0, premiseEnd.getAsInt() + 1)
						+ tweet.substring(conclusionStart.getAsInt() + 1, tweet.length());
				if (premiseConclusion.length() < (MAX_TWEET_LENGTH - 1)) {
					tweet = premiseConclusion.trim();
				} else {
					// If previous trial too long: just abbreviate by finding
					// first end before 140 characters
					tweet = roughCut(tweet);
				}
			}

		}
		return tweet;
	}

	public static String roughCut(String tweet) {
		String roughCutTweet = tweet.substring(0, MAX_TWEET_LENGTH  - 1);
		OptionalInt lastIndex = SentenceUtil
				.getLastSentenceEndIndex(roughCutTweet.substring(0, roughCutTweet.length() - 1));
		if (lastIndex.isPresent()) {
			String newTweet = roughCutTweet.substring(0, lastIndex.getAsInt() + 1);
			return newTweet.trim();
		}
		return roughCutTweet;
	}

}
