package textgen;

import java.util.*;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/**
	 * Train the generator by adding the sourceText
	 */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		try {
			ArrayList<String> words = getList(sourceText);
			starter = words.get(0);
			String prevWord = starter;
			String w;
			for (int i = 1; i <= words.size(); i++) {
				if (i == words.size()) w = starter;
				else w = words.get(i);

				if (isInWordList(prevWord)) {
					for (ListNode n : wordList) {
						if (n.getWord().equals(prevWord)) {
							n.addNextWord(w);
							break;
						}
					}
				} else {
					ListNode node = new ListNode(prevWord);
					node.addNextWord(w);
					wordList.add(node);
				}
				prevWord = w;
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Error! You can't train empty string!");
		}
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		StringBuilder output = new StringBuilder();
		String currWord = starter;
		output.append(currWord);
		int i = 0;
		while (i < numWords){
			for (ListNode n: wordList){
				if (n.getWord().equals(currWord)){
					currWord = n.getRandomNextWord(rnGenerator);
					output.append(" ");
					output.append(currWord);
					break;
				}
			}
			i++;
		}
		return output.toString();
	}


	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/**
	 * Retrain the generator from scratch on the source text
	 */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		train(sourceText);

	}

	// TODO: Add any private helper methods you need here.

	private ArrayList<String> getList(String s){
		ArrayList<String> words = new ArrayList<>();
		Collections.addAll(words, s.split(" "));
		if (words.contains("")) {
			for (int i = 0; i < words.size(); i++){
				if (words.get(i).equals("")) words.remove("");
			}
		}
		return words;
	}

	private Boolean isInWordList(String prevWord) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(prevWord)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}
/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


