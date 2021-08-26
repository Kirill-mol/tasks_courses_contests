package tinkoff_internship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kirill Mololkin Kirill-mol 25.08.2021
 */
public class Task4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		double fee = 0.0;
		List<Integer> bankAccounts = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			bankAccounts.add(scanner.nextInt());
		}

		while (bankAccounts.size() > 1) {
			double transactionFee = findTwoMinAndCalculateFee(bankAccounts);
			fee += Math.round(transactionFee * 100.0) / 100.0;
		}

		System.out.printf("%.2f", fee);
	}



	private static double findTwoMinAndCalculateFee(List<Integer> list) {
		int min1 = list.get(0);
		int min2 = list.get(1);

		int index1 = 0;
		int index2 = 1;

		if (min1 > min2) {
			int temp = min1;
			min1 = min2;
			min2 = temp;

			index1 = 1;
			index2 = 0;
		}

		for (int i = 2; i < list.size(); i++) {
			if (list.get(i) < min1) {
				int temp = min1;
				min1 = list.get(i) ;
				min2 = temp;
				index2 = index1;
				index1 = i;
			} else if (list.get(i)  < min2) {
				min2 = list.get(i);
				index2 = i;
			}
		}

		list.set(index1, min1 + min2);
		list.remove(index2);

		return (min1 + min2) * 0.05;
	}
}
