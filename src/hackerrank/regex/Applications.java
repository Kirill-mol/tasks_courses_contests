package hackerrank.regex;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Kirill Mololkin Kirill-mol 12.09.2021
 */
public class Applications {

	public static void alienUsername() {
		Scanner sc = new Scanner(System.in);

		int i = Integer.parseInt(sc.nextLine());

		Pattern pattern = Pattern.compile("([_.])\\d+[a-zA-Z]*_?");

		for (int j = 0; j < i; j++) {
			System.out.println(pattern.matcher(sc.nextLine()).matches() ? "VALID" : "INVALID");
		}
	}
	public static void main(String[] args) {
		alienUsername();
	}
}