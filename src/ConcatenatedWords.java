import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class ConcatenatedWords {

	private TreeMap<String, Integer> wordTreeMap;

	private List<String> wordList;
	private List<String> concatenatedWords;
	private String LongestWord;
	private String SecondLongestWord;

	public ConcatenatedWords() {
		this.wordTreeMap = new TreeMap<>(new myComparator());
		this.wordList = new ArrayList<>();
		this.concatenatedWords = new ArrayList<>();
		this.LongestWord = "";
		this.SecondLongestWord = "";
	}

	public void addToTreeAndToList(String word, int count) {
		wordTreeMap.put(word, count);
		wordList.add(word);
	}

	// Tree is already sorted
	// Get the first and second longest string
	public void process() {
		while (wordTreeMap.size() > 0) {
			String word = wordTreeMap.lastKey();
			wordTreeMap.remove(word);
			wordList.remove(word);
			if (isConcatenatedWord(word)) {
				concatenatedWords.add(word);
			}
		}
		int size=concatenatedWords.size();
		if ( size >= 1) {
			LongestWord = concatenatedWords.get(0);
		}
		if (size >= 2) {
			SecondLongestWord = concatenatedWords.get(1);
		}
	}

	private boolean isConcatenatedWord(String word) {
		if (wordList.contains(word)) {
			return true;
		}
		int wordLenght = word.length();
		for (int i = 0; i < wordLenght; i++) {
			String begin = word.substring(0, i);
			String end = word.substring(i, word.length());
			if (wordList.contains(begin)) {
				if (isConcatenatedWord(end)) {
					return true;
				}
			}
		}
		return false;
	}

	private class myComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
	}

	public List<String> getConcatenatedWords() {
		return concatenatedWords;
	}

	public String getLongestWord() {
		return LongestWord;
	}

	public String getSecondLongestWord() {
		return SecondLongestWord;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void Output(String filepath) {
		FileReadAndWrite.readToList(filepath, this);
		System.out.println("Total number of words:  " + this.getWordList().size());
		this.process();
		System.out.println("The total number of concatenated words: " + this.getConcatenatedWords().size());
		System.out.println("1st longest: " + this.LongestWord);

		System.out.println("2nd longest: " + this.SecondLongestWord);
      
	}
}
