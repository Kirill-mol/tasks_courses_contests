package tinkoff_internship;

import java.util.Scanner;

/**
 * @author Kirill Mololkin Kirill-mol 25.08.2021
 */
public class Task1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int n = scanner.nextInt();

		System.out.println(Math.min(a + b, n));
	}
}
