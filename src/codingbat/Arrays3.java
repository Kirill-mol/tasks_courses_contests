package codingbat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kirill Mololkin kir.mololkin@yandex.ru 28.07.2021
 */
public class Arrays3 {

	/**
	 * Consider the leftmost and righmost appearances of some value in an array.
	 * We'll say that the "span" is the number of elements between the two inclusive.
	 * A single value has a span of 1. Returns the largest span found in the given array.
	 * (Efficiency is not a priority.)
	 *
	 *
	 * maxSpan([1, 2, 1, 1, 3]) → 4
	 * maxSpan([1, 4, 2, 1, 4, 1, 4]) → 6
	 * maxSpan([1, 4, 2, 1, 4, 4, 4]) → 6
	 */
	public int maxSpan(int[] nums) {
		Map<Integer, Integer> mapOfSpans = new HashMap<>();
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if(!mapOfSpans.containsKey(nums[i])) {
				mapOfSpans.put(nums[i], i);
			} else {
				int firstInput = mapOfSpans.get(nums[i]);
				if (i - firstInput > max) {
					max = i - firstInput;
				}
			}
		}
		return nums.length == 0 ? 0 : max + 1;
	}

	/**
	 * Return an array that contains exactly the same numbers as the given array,
	 * but rearranged so that every 3 is immediately followed by a 4.
	 * Do not move the 3's, but every other number may move.
	 * The array contains the same number of 3's and 4's,
	 * every 3 has a number after it that is not a 3, and a 3 appears in the array before any 4.
	 *
	 *
	 * fix34([1, 3, 1, 4]) → [1, 3, 4, 1]
	 * fix34([1, 3, 1, 4, 4, 3, 1]) → [1, 3, 4, 1, 1, 3, 4]
	 * fix34([3, 2, 2, 4]) → [3, 4, 2, 2]
	 */
	public int[] fix34(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 3) {
				for (int j = 0; j < nums.length; j++) {
					if (nums[j] == 4 && (j == 0 || nums[j - 1] != 3)) {
						int tmp = nums[i + 1];
						nums[i + 1] = nums[j];
						nums[j] = tmp;
						break;
					}
				}
			}
		}
		return nums;
	}

	public int[] fix45(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 4) {
				for (int j = 0; j < nums.length; j++) {
					if (nums[j] == 5 && (j == 0 || nums[j - 1] != 4)) {
						int tmp = nums[i + 1];
						nums[i + 1] = nums[j];
						nums[j] = tmp;
						break;
					}
				}
			}
		}
		return nums;
	}

	/**
	 * Given a non-empty array, return true if there is a place to split t
	 * he array so that the sum of the numbers on one side
	 * is equal to the sum of the numbers on the other side.
	 */
	public boolean canBalance(int[] nums) {
		int indexL = -1;
		int indexR = nums.length;
		int sumL = 0;
		int sumR = 0;

		while (indexR - indexL > 1) {
			if (sumL < sumR) {
				sumL += nums[++indexL];
			} else {
				sumR += nums[--indexR];
			}
		}
		System.out.println(sumL);
		System.out.println(sumR);
		return sumL == sumR;
	}

	/**
	 * Given two arrays of ints sorted in increasing order, outer and inner,
	 * return true if all of the numbers in inner appear in outer.
	 * The best solution makes only a single "linear" pass of both arrays,
	 * taking advantage of the fact that both arrays are already in sorted order.
	 */
	public boolean linearIn(int[] outer, int[] inner) {
		Set<Integer> setOfOuter = Arrays.stream(outer).boxed().collect(Collectors.toSet());
		for (int i : inner) {
			if (!setOfOuter.contains(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given n>=0, create an array with the pattern {1,    1, 2,    1, 2, 3,   ... 1, 2, 3 .. n}
	 * (spaces added to show the grouping).
	 * Note that the length of the array will be 1 + 2 + 3 ... + n,
	 * which is known to sum to exactly n*(n + 1)/2.
	 */
	public int[] seriesUp(int n) {
		int[] resultArr = new int[n * (n + 1) / 2];
		int arrIndex = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				resultArr[arrIndex++] = j;
			}
		}
		return resultArr;
	}

	/**
	 * Say that a "clump" in an array is a series of 2 or more adjacent elements of the same value.
	 * Return the number of clumps in the given array.
	 *
	 * countClumps([1, 2, 2, 3, 4, 4]) → 2
	 * countClumps([1, 1, 2, 1, 1]) → 2
	 * countClumps([1, 1, 1, 1, 1]) → 1
	 */
	public int countClumps(int[] nums) {
		boolean isInClump = false;
		int clumpCounter = 0;

		for (int i = 1; i < nums.length; i++) {
			if (!isInClump && nums[i] == nums[i - 1]) {
				isInClump = true;
				clumpCounter++;
			} else if (nums[i] != nums[i - 1]) {
				isInClump = false;
			}
		}
		return clumpCounter;
	}

	/**
	 * Given n>=0, create an array length n*n with the following pattern,
	 * shown here for n=3 : {0, 0, 1,    0, 2, 1,    3, 2, 1}
	 * (spaces added to show the 3 groups).
	 *
	 *
	 * squareUp(3) → [0, 0, 1, 0, 2, 1, 3, 2, 1]
	 * squareUp(2) → [0, 1, 2, 1]
	 * squareUp(4) → [0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1]
	 *
	 *               [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15]
	 */
	public int[] squareUp(int n) {
		int[] resultArr = new int[n * n];

		for (int i = 1; i <= n; i++) {
			int startJ = (n * n) - (n * i) + (i - 1);
			int nCopy = n - i + 1;
			for (int j = startJ; j < startJ + n - i + 1; j++) {
				resultArr[j] = nCopy--;
			}
		}
		return resultArr;
	}

	public static void ggWp(String n) {

	}
	public static void ggWp(int n) {

	}

	public static void main(String[] args) {
		Arrays3 arrays3 = new Arrays3();

//		System.out.println(arrays3.maxSpan(new int[]{1, 4, 2, 1, 4, 1, 4}));
//		System.out.println(Arrays.toString(arrays3.fix34(new int[]{1, 3, 1, 4, 4, 3, 1})));
//		System.out.println(Arrays.toString(arrays3.fix34(new int[]{3, 2, 2, 4})));
//		System.out.println(Arrays.toString(arrays3.fix34(new int[]{3, 2, 2, 4})));
//		System.out.println(Arrays.toString(arrays3.fix45(new int[]{1, 4, 1, 5, 5, 4, 1})));
//		System.out.println(Arrays.toString(arrays3.fix45(new int[]{5, 4, 9, 4, 9, 5})));
//		System.out.println(arrays3.canBalance(new int[]{1, 1, 1, 2, 1}));
//		System.out.println(arrays3.canBalance(new int[]{2, 1, 1, 2, 1}));
//		System.out.println(arrays3.canBalance(new int[]{10, 0, 1, -1, 10}));
//		System.out.println(Arrays.toString(arrays3.seriesUp(3)));
//		System.out.println(arrays3.countClumps(new int[]{1, 2, 2, 3, 3, 4, 4}));
		System.out.println(Arrays.toString(arrays3.squareUp(4)));

	}

	public static void main(String args) {

	}
}
