package hackerrank.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kirill Mololkin Kirill-mol 11.09.2021
 */
public class Solution {

	public static void matchingAnything() {
		check("(.{3}\\.){3}.{3}");
	}

	public static void matchingDigit() {
		check("(\\d\\d\\D){2}\\d{4}");
	}

	public static void matchingWhitespace() {
		check("\\S+\\s\\S\\S\\s\\S+");

	}

	public static void matchingNonWord() {
		check("(\\w{3})\\W\\w{10}\\W\\w{3}");

	}

	public static void matchingEndStart() {
		checkPattern(Pattern.compile("^\\d\\w{4}\\.$"));
	}

	public static void matchingSameGroup() {
		checkPattern(Pattern.compile("([a-z])(\\w)(\\s)(\\W)(\\d)(\\D)([A-Z])([a-zA-Z])([aeiouAEIOU])(\\S)" +
				"\\1\\2\\3\\4\\5\\6\\7\\8\\9\\10"));
	}

	public static void matchingSpecific() {
		checkPattern(Pattern.compile("[123][120][xs0][30Aa][xsu][.,]"));
	}

	public static void matchingExclude() {
		checkPattern(Pattern.compile("[^\\d][^aeoiu][^dcDF][^\\s][^AEIOU][^.,]"));
	}

	public static void matchingRanges() {
		checkPattern(Pattern.compile("^[a-z][1-9][^a-z][^A-Z][A-Z]"));
	}

	public static void matchingRepetitions() {
		checkPattern(Pattern.compile("^[02468a-zA-z]{40}[13579\\s]{5}$"));
	}

	public static void matchingRepetitionsXY() {
		checkPattern(Pattern.compile("^\\d{1,2}[a-zA-Z]{3,}\\.{0,3}$"));
	}

	public static void matchingRepetitionsZeroOrMore() {
		checkPattern(Pattern.compile("^\\d{2,}[a-z]*[A-Z]*$"));
	}

	public static void matchingRepetitionsOneOrMore() {
		checkPattern(Pattern.compile("^\\d+[A-Z]+[a-z]+$"));
	}
	public static void matchingEnding() {
		checkPattern(Pattern.compile("^[A-Za-z]s$"));
	}

	public static void matchingWordBoundary() {
		checkPattern(Pattern.compile("\\b[aeiouAEIOU][A-Za-z]*\\b"));
	}

	public static void matchingAlternative() {
		checkPattern(Pattern.compile("^\\d\\d(-?)\\d\\d\\1\\d\\d\\1\\d\\d$"));
	}

	public static void matchingResetGroup() {
		checkPattern(Pattern.compile("^(\\d\\d((-)|(:)|(---)|.))(\\d\\d\\2){2}\\d\\d$"));
	}

	public static void matchingForwardReference() {
		checkPattern(Pattern.compile("^(\\2tic|(tac))+$"));
	}

	public static void matchingPositiveLookHead() {
		checkPattern(Pattern.compile("o(?=oo)"));
	}

	public static void matchingNegativeLookHead() {
		checkPattern(Pattern.compile("(.)(?!\\1)"));
	}

	public static void checkPattern(Pattern pattern) {
		Scanner sc = new Scanner(System.in);
		String inputStr = sc.nextLine();

		Matcher matcher = pattern.matcher(inputStr);

		System.out.println(matcher.find());
	}

	public static void check(String regex) {
		Scanner sc = new Scanner(System.in);
		String inputStr = sc.nextLine();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputStr);

		System.out.println(matcher.find());
	}

	public static void main(String[] args) {
		matchingForwardReference();
	}
}
