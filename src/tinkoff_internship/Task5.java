package tinkoff_internship;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Kirill Mololkin Kirill-mol 25.08.2021
 */
public class Task5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		scanner.nextLine();

		List<Double> coefficients = Arrays.stream(scanner.nextLine().split(" "))
				.mapToDouble(Double::parseDouble).boxed()
				.collect(Collectors.toList());

		double eps = 0.00000000000000001;
		double x = 100;

		do {
			if (df(coefficients, x) == 0) {
				break;
			}
			x -= f(coefficients, x) / df(coefficients, x);

		} while (Math.abs(f(coefficients, x)) > eps);
		System.out.println(x);

	}

	public static double f(List<Double> coefficients, double x) {
		double result = 0.0;

		for (int i = 0; i < coefficients.size(); i++) {
			result += coefficients.get(i)*Math.pow(x, i);
		}

		return result;
	}

	public static double df(List<Double> coefficients, double x) {
		double result = 0.0;

		for (int i = 1; i < coefficients.size(); i++) {
			result += coefficients.get(i)*Math.pow(x, i - 1) * i;
		}

		return result;
	}

	public static double d2f(List<Double> coefficients, double x) {
		double result = 0.0;

		for (int i = 2; i < coefficients.size(); i++) {
			result += coefficients.get(i)*Math.pow(x, i - 2) * i * (i - 1);
		}

		return result;
	}
}
