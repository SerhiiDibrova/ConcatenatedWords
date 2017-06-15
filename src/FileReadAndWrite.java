
import java.io.*;
import java.util.ArrayList;

public class FileReadAndWrite {

	public static void readToList(String filePath, ConcatenatedWords concatenatedWord) {
		int count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while (br.ready()) {
				concatenatedWord.addToTreeAndToList(br.readLine(), count);
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeListToFile(ArrayList<String> wordsList, String fileName) {
		try (PrintWriter pw = new PrintWriter(fileName)) {
			for (int i = 0; i < wordsList.size(); i++) {
				pw.println(wordsList.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
