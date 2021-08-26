package tinkoff_internship;

import java.util.Scanner;

/**
 * @author Kirill Mololkin Kirill-mol 25.08.2021
 */
public class Task2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int w = scanner.nextInt();
		int h = scanner.nextInt();

		int minPrice = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int wi = scanner.nextInt();
			int hi = scanner.nextInt();
			int ci = scanner.nextInt();

			if (checkSize(w, h, wi, hi) && ci < minPrice) {
				minPrice = ci;
			}
		}

		System.out.println(minPrice);

	}

	private static boolean checkSize(int w, int h, int wi, int hi) {
		return (wi >= w && hi >= h) || (wi >= h && hi >= w);
	}
}
