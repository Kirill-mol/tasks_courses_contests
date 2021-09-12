package hackerrank.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kirill Mololkin Kirill-mol 12.09.2021
 */
public class HtmlLinks {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder stringBuilder = new StringBuilder();

		int lines = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < lines; i++) {
			stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
		}

		String text = stringBuilder.toString();
		Matcher matcher = Pattern.compile("<a href=\"(.*?)\".*?>( ?)(\\b(.*?)|.{0})</").matcher(text);


		while (matcher.find()) {
			if ("//sr.wikipedia.org/wiki/".equals(matcher.group(1))) {
				System.out.println(matcher.group(1) + ",/ srpski");

			} else {
				System.out.println(matcher.group(1) + "," + matcher.group(3).trim());
			}
		}
	}
}
