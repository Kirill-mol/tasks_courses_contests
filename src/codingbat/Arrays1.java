package codingbat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Kirill Mololkin kir.mololkin@yandex.ru 25.07.2021
 */
public class Arrays1 {
	public static void main(String[] args) {
		Arrays1 arrays1 = new Arrays1();
		Random random = new Random();
		/*int[] testArrWithRandomValues = random.ints(100_000_000).toArray();

		long startSysCopingTime = System.currentTimeMillis();
		arrays1.rotateLeft3Sys(testArrWithRandomValues);
		long endSysCopingTime = System.currentTimeMillis();
		System.out.printf("rotate3LeftSys() time for 10_000_000 elems - %d \n",
				startSysCopingTime - endSysCopingTime );

		long startCpRangeCopingTime = System.currentTimeMillis();
		arrays1.rotateLeft3CpRange(testArrWithRandomValues);
		long endCpRangeCopingTime = System.currentTimeMillis();
		System.out.printf("rotate3CpRange() time for 10_000_000 elems - %d \n",
				startCpRangeCopingTime - endCpRangeCopingTime );*/
//		System.out.println(arrays1.has23(new int[]{4, 3}));
//		System.out.println(arrays1.has23(new int[]{100, 2}));
//		Integer[] ints = {4, 2};
//		System.out.println(Arrays.asList(ints).contains(2));
//		int[] ints2 = {4, 2, 5};
//		System.out.println(Arrays.binarySearch(ints2, 2));
//		long startDouble23Loop = System.currentTimeMillis();
//		for (long i = 0; i < 1_000_000_000; i++) {
//			arrays1.double23_1(new int[]{3, 3});
//		}
//		long endDouble23Loop = System.currentTimeMillis();
//		System.out.println(endDouble23Loop - startDouble23Loop);
//		long startDouble23Equals = System.currentTimeMillis();
//		for (int i = 0; i < 100_000_000; i++) {
//			arrays1.double23_2(new int[]{2, 2});
//		}
//		long endDouble23Equals = System.currentTimeMillis();
//		System.out.println(endDouble23Equals - startDouble23Equals);
//		System.out.println(Arrays.toString(arrays1.plusTwo(new int[]{1, 2, 3}, new int[]{4, 5, 6})));
		System.out.println(arrays1.maxTriple(new int[]{3}));
	}

	/*
	Given an array of ints,
	return true if 6 appears as either the first or last element in the array.
	The array will be length 1 or more.
	 */
	public boolean firstLast6(int[] nums) {
		return nums[0] == 6 || nums[nums.length - 1] == 6;
	}

	/*
	Given an array of ints, return true if the array is length 1 or more,
	and the first element and the last element are equal.
	 */
	public boolean sameFirstLast(int[] nums) {
		return nums.length > 0 && (nums[0] == nums[nums.length - 1]);
	}

	/*
	Given 2 arrays of ints, a and b,
	return true if they have the same first element or they have the same last element.
	Both arrays will be length 1 or more.
	 */
	public boolean commonEnd(int[] a, int[] b) {
		return (a[0] == b[0]) || (a[a.length - 1] == b[b.length - 1]);
	}

	/*
	Given an array of ints length 3, return the sum of all the elements.
	 */
	public int sum3(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum;
	}

	/*
	Given an array of ints length 3, return an array with the elements
	"rotated left" so {1, 2, 3} yields {2, 3, 1}.
	 */
	public int[] rotateLeft3Sys(int[] nums) {
		int firstElem = nums[0];

		System.arraycopy(nums, 1, nums, 0, nums.length - 1);
		nums[nums.length - 1] = firstElem;
		return nums;
	}

	public int[] rotateLeft3CpRange(int[] nums) {
		int firstElem = nums[0];
		nums = Arrays.copyOfRange(nums, 1, nums.length + 1);
		nums[nums.length - 1] = firstElem;
		return nums;
	}

	/*
	Given an array of ints length 3, return a new array with
	the elements in reverse order, so {1, 2, 3} becomes {3, 2, 1}.
	 */
	public int[] reverse3(int[] nums) {
		for (int i = 0; i < nums.length / 2; i++) {
			int tmp = nums[i];
			nums[i] = nums[nums.length - 1 - i];
			nums[nums.length - 1 - i] = tmp;
		}
		return nums;
	}

	/*
	Given an array of ints length 3, figure out which is larger,
	the first or last element in the array, and set all the other
	elements to be that value. Return the changed array.
	 */
	public int[] maxEnd3(int[] nums) {
		Arrays.fill(nums, Math.max(nums[0], nums[nums.length - 1]));
		return nums;
	}

	/*
	Given an array of ints, return the sum of the first 2 elements in the array.
	If the array length is less than 2, just sum up the elements that exist,
	returning 0 if the array is length 0.
	 */
	public int sum2(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > 1) {
				break;
			}
			sum += nums[i];
		}
		return sum;
	}

	/*
	Given an int array length 2, return true if it contains a 2 or a 3.
	 */
	public boolean has23(int[] nums) {
		for (int num : nums) {
			if (num == 2 || num == 3) {
				return true;
			}
		}
		return false;
	}

	/*

	Given an int array, return a new array with double the length
	where its last element is the same as the original array, and all the other elements are 0.
	 The original array will be length 1 or more. Note: by default, a new int array contains all 0's.
	 */
	public int[] makeLast(int[] nums) {
		int[] ints = new int[nums.length * 2];
		ints[ints.length - 1] = nums[nums.length - 1];
		return ints;
	}

	/*
	Given an int array, return true if the array contains 2 twice, or 3 twice. The array will be length 0, 1, or 2.
	 */
	public boolean double23_1(int[] nums) {
		int count3 = 0;
		int count2 = 0;
		for (int num : nums) {
			if (num == 2) {
				count2++;
			}
			if (num == 3) {
				count3++;
			}
		}
		return count2 == 2 || count3 == 2;
	}

	public boolean double23_2(int[] nums) {
		return Arrays.equals(nums, new int[]{2,2}) || Arrays.equals(nums, new int[]{3,3});
	}

	/*
	Given an int array length 3,
	if there is a 2 in the array immediately followed by a 3,
	set the 3 element to 0. Return the changed array.
	 */
	public int[] fix23(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 2 && nums[i + 1] == 3) {
				nums[i + 1] = 0;
			}
		}
		return nums;
	}

	/*
	Start with 2 int arrays, a and b, each length 2.
	Consider the sum of the values in each array.
	 Return the array which has the largest sum.
	 In event of a tie, return a.
	 */
	public int[] biggerTwo(int[] a, int[] b) {
		return Arrays.stream(a).sum() >= Arrays.stream(b).sum() ? a : b;
	}

	/*
	Given 2 int arrays, each length 2, return a new array length 4 containing all their elements.
	 */
	public int[] plusTwo(int[] a, int[] b) {
		int[] connectedArr = new int[a.length + b.length];
		System.arraycopy(a, 0, connectedArr, 0, a.length);
		System.arraycopy(b, 0, connectedArr, a.length, b.length);
		return connectedArr;
	}

	public int[] plusTwo2(int[] a, int[] b) {
		int[] connectedArr = Arrays.copyOf(a, a.length + b.length);

		connectedArr[2] = b[0];
		connectedArr[3] = b[1];

		return connectedArr;
	}

	/*
	Given an array of ints, swap the first and last elements in the array.
	Return the modified array. The array length will be at least 1.
	*/
	public int[] swapEnds(int[] nums) {
		int tmp = nums[0];
		nums[0] = nums[nums.length - 1];
		nums[nums.length - 1] = tmp;
		return nums;
	}

	/*
	Given an array of ints of odd length, return a new array length 3
	containing the elements from the middle of the array. The array length will be at least 3.
	 */
	public int[] midThree(int[] nums) {
		return Arrays.copyOfRange(nums, nums.length / 2 - 1, nums.length / 2 + 1);
	}

	/*
	Given an array of ints of odd length, look at the first, last,
	and middle values in the array and return the largest. The array length will be a least 1.
	 */

	public int maxTriple(int[] nums) {
		return Math.max(nums[0], Math.max(nums[nums.length / 2], nums[nums.length - 1]));
	}

	/*
	Given an int array of any length, return a new array of its first 2 elements.
	If the array is smaller than length 2, use whatever elements are present.
	 */
	public int[] frontPiece(int[] nums) {
		switch(nums.length) {
			case 0:
				int c = 20;
				return new int[0];
			case 1:
				return new int[]{nums[0]};
			default:
				return Arrays.copyOfRange(nums, 0, 2);
		}
	}

	/*
	We'll say that a 1 immediately followed by a 3 in an array is an "unlucky" 1.
	Return true if the given array contains an unlucky 1 in the first 2 or last 2 positions in the array.
	 */
	public boolean unlucky1(int[] nums) {
		if (nums.length > 1 && (nums[0] == 1 && nums[1] == 3)) {
			return true;
		} else if (nums.length > 2) {
			return (nums[1] == 1 && nums[2] == 3)
					|| (nums[nums.length - 2] == 1 && nums[nums.length - 1] == 3);
		}

		return false;
	}


	/*
	Given 2 int arrays, a and b, return a new array length 2 containing,
	as much as will fit, the elements from a followed by the elements from b.
	The arrays may be any length, including 0,
	but there will be 2 or more elements available between the 2 arrays.
	*/
	public int[] make2(int[] a, int[] b) {
		switch (a.length) {
			case 0:
				return Arrays.copyOfRange(b, 0, 2);
			case 1:
				return new int[]{a[0], b[0]};
			default:
				return Arrays.copyOfRange(a, 0, 2);
		}
	}
	/*
	Given 2 int arrays, a and b, of any length,
	return a new array with the first element of each array.
	If either array is length 0, ignore that array.
	 */
	public int[] front11(int[] a, int[] b) {
		if (a.length > 0 && b.length > 0) {
			return new int[]{a[0], b[0]};
		}
		if (a.length > 0) {
			return new int[]{a[0]};
		}
		if (b.length > 0) {
			return new int[]{b[0]};
		}
		return new int[0];
	}

	public int[] makePi() {
		int[] piArr = new int[3];
		new Object();

		String[] chars = String.format("%.3f", Math.PI).replace(".", "").split("");
		for (int i = 0; i < chars.length; i++) {
			piArr[i] = Integer.parseInt(chars[i]);
		}
		return piArr;
	}
}

