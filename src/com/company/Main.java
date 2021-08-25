package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	static String[][] zero = {
			{"*", "*", "*"},
			{"*", " ", "*"},
			{"*", " ", "*"},
			{"*", " ", "*"},
			{"*", " ", "*"},
			{"*", "*", "*"}
	};

	public static void main(String[] args) {
		// write your code here
//		List<List<String>> arr = new ArrayList<>();
//
//		for (int i = 0; i < 6; i++) {
//			arr.add(new ArrayList<>());
//		}
//
//		for (int j = 0; j < 3; j++) {
//			for (int i = 0; i < 6; i++) {
//				arr.get(i).addAll(Arrays.asList(zero[i]));
//			}
//		}
//
//		for (List<String> strings : arr) {
//			System.out.println(strings);
//		}


		int[] arr = new int[]{1, 2, 3};
		List<Integer> intList = Arrays.stream(arr).boxed().toList();

		for (final int i : arr) {
			System.out.println(i);
		}

		for (final Integer num : intList) {
			System.out.println(num);
		}
	}
}

class A {
	private void sayHello() {
		System.out.println();
	}
}