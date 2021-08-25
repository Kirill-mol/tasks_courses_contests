package codingbat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Kirill Mololkin kir.mololkin@yandex.ru 22.07.2021
 */
public class WarmUp2 {

	public static void main(String[] args) {
		System.out.println(new WarmUp2().doubleX("xxx"));
		System.out.println(new WarmUp2().doubleX("axaxx"));
		System.out.println(new WarmUp2().stringSplosion("Code"));
		System.out.println(new WarmUp2().has271(new int[]{2, 7, 1}));
	}

	/*
	Given a string and a non-negative int n, we'll say that
	the front of the string is the first 3 chars,
	or whatever is there if the string is less than length 3. Return n copies of the front;
	 */
	public String frontTimes(String str, int n) {
		return String.join("", Collections.nCopies(n,
				str.substring(0, Math.min(str.length(), 3))));
	}

	/*
	Count the number of "xx" in the given string.
	We'll say that overlapping is allowed, so "xxx" contains 2 "xx".
	 */
	static int countXX(String str) {
		int xxCounter = 0;
		int xCounter = 0;

		for (char ch : str.toCharArray()) {
			if (ch == 'x') {
				++xCounter;
			} else if (xCounter > 0) {
				xxCounter += xCounter - 1;
				xCounter = 0;
			}
		}
		if (xCounter > 0) {
			xxCounter += xCounter - 1;
		}
		return xxCounter;
	}

	int countXX2(String str) {
		int count = 0;
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.startsWith("xx", i)) count++;
		}
		return count;
	}

	/*
	Given a string,
	return true if the first instance of "x" in the string is immediately followed by another "x".
	 */
	boolean doubleX(String str) {
		int indexNextChar = str.indexOf('x') + 1;
		if (indexNextChar >= str.length()) {
			return false;
		}
		return str.charAt(indexNextChar) == 'x';
	}

	/*

Given a string,
return a new string made of every other char starting with the first, so "Hello" yields "Hlo".
	 */

	public String stringBits(String str) {
		StringBuilder resultString = new StringBuilder();
		for (int i = 0; i < str.length(); i += 2) {
			resultString.append(str.charAt(i));
		}
		return resultString.toString();
	}

	/*
	Given a non-empty string like "Code" return a string like "CCoCodCode".
	 */
	public String stringSplosion(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < str.length() + 1; i++) {
			sb.append(str, 0, i);
		}
		return sb.toString();
	}

	/*
	Given a string, return the count of the number of times that a substring
	length 2 appears in the string and also as the last 2 chars of the string,
	so "hixxxhi" yields 1 (we won't count the end substring).
	 */

	public int last2(String str) {
		String substring = str.substring(0, str.length() - 1);
		substring = substring.replace(str.substring(str.length() - 2), "*");
		return (int) substring.chars().filter(x -> x == '*').count();
	}

	/*
	Given an array of ints, return the number of 9's in the array.
	 */

	public int arrayCount9(int[] nums) {
		int counter = 0;
		for (int num : nums) {
			counter += num == 9 ? 1 : 0;
		}

		//return (int) Arrays.stream(nums).filter(x -> x == 9).count();
		return counter;
	}

	/*
	Given an array of ints, return true if one of the first 4 elements in the array is a 9.
	The array length may be less than 4.
	 */
	public boolean arrayFront9(int[] nums) {
		for (int i = 0; i < (Math.min(nums.length, 4)); i++) {
			if (nums[i] == 9) {
				return true;
			}
		}
		return false;
	}

	/*
	Given an array of
	ints, return true if the sequence of numbers 1, 2, 3 appears in the array somewhere.
	 */
	public boolean array123(int[] nums) {
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] == 1 && nums[i + 1] == 2 && nums[i + 2] == 3) {
				return true;
			}
		}
		return false;
	}

	/*
	Given 2 strings, a and b, return the number of the positions where
	they contain the same length 2 substring. So "xxcaazz" and "xxbaaz"
	yields 3, since the "xx", "aa", and "az" substrings appear in the
	same place in both strings.
	 */
	public int stringMatch(String a, String b) {
		int counter = 0;
		for (int i = 0; i < Math.min(a.length(), b.length()) - 1; i++) {
			if (a.substring(i, i + 2).equals(b.substring(i, i + 2))) {
				counter++;
			}
		}
		return counter;
	}

	/*
	Given a string, return a version where all the "x" have been removed.
	Except an "x" at the very start or end should not be removed.
	 */
	public String stringX(String str) {

		return str.length() < 2 ? str :
				str.charAt(0)
						+ str.substring(1, str.length() - 1).replace("x", "")
						+ str.charAt(str.length() - 1);
	}

	/*
	Given a string,
	return a string made of the chars at indexes 0,1, 4,5, 8,9 ... so "kittens" yields "kien".
	 */
	public String altPairs(String str) {
		StringBuilder resultString = new StringBuilder();

		for (int i = 0; i < str.length(); i += 4) {
			resultString.append(str.charAt(i));
			if (i + 1 < str.length()) {
				resultString.append(str.charAt(i + 1));
			}
		}
		return resultString.toString();
	}

	/*
	Given an array of ints, return the number of times that two 6's
	are next to each other in the array.
	Also count instances where the second "6" is actually a 7.
	 */
	public int array667(int[] nums) {
		int counter = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 6 && (nums[i + 1] == 6 || nums[i + 1] == 7)) {
				counter++;
			}
		}
		return counter;
	}

	/*
	Given an array of ints, we'll say that a triple is a value appearing
	3 times in a row in the array. Return true if the array does not contain any triples.
	 */
	public boolean noTriples(int[] nums) {
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] == nums[i + 1] && nums[i] == nums[i + 2]) {
				return false;
			}
		}
		return true;
	}

	/*
	Given an array of ints, return true if it contains a 2, 7, 1 pattern:
	a value, followed by the value plus 5, followed by the value minus 1.
	Additionally the 271 counts even if the "1" differs by 2 or less from the correct value.
	 */

	public boolean has271(int[] nums) {
		for (int i = 0; i < nums.length - 2; i++) {
			if (nums[i] + 5 == nums[i + 1] && Math.abs(nums[i + 2] - (nums[i])) <= 2) {
				return true;
			}
		}
		return false;
	}

}
