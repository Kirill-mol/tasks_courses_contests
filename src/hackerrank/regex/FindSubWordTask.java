package hackerrank.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kirill Mololkin Kirill-mol 12.09.2021
 */
public class FindSubWordTask {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int numberSentences = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < numberSentences; i++) {
			sb.append(scanner.nextLine()).append(System.lineSeparator());
		}

		int numberWords = Integer.parseInt(scanner.nextLine());
		List<Pattern> patterns = new ArrayList<>();

		for (int i = 0; i < numberWords; i++) {
			patterns.add(Pattern.compile("\\w" + scanner.nextLine() + "\\w"));
		}

		String text = sb.toString();

		for (Pattern pattern : patterns) {
			Matcher matcher = pattern.matcher(text);
			int counter = 0;
			while (matcher.find()) {
				counter++;
			}
			System.out.println(counter);
		}
	}
}
