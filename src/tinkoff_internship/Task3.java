package tinkoff_internship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kirill Mololkin Kirill-mol 25.08.2021
 */
public class Task3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String number = scanner.nextLine();

		List<Integer> delimiters = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

		for (int i = 0; i < 10; i++) {
			int numberReplaced = Integer.parseInt(number.replace('*', Character.forDigit(i, 10)));

			delimiters.removeIf(num -> numberReplaced % num != 0);
		}

		for (Integer delimiter : delimiters) {
			System.out.print(delimiter + " ");
		}
	}
}
