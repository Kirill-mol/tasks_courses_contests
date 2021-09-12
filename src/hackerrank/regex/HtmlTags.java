package hackerrank.regex;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kirill Mololkin Kirill-mol 12.09.2021
 */
public class HtmlTags {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder stringBuilder = new StringBuilder();
		Set<String> set = new TreeSet<>();

		int lines = Integer.parseInt(scanner.nextLine());

		for (int i = 0; i < lines; i++) {
			stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
		}

		String text = stringBuilder.toString();
		Matcher matcher = Pattern.compile("<([a-z\\d]+)").matcher(text);

		while (matcher.find()) {
			set.add(matcher.group(1));
		}

		System.out.println(String.join(";", set));

		//a;b;div;h2;li;span;ul
		//a;b;div;h;li;span;ul
	}
}
