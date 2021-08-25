package codingbat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Kirill Mololkin kir.mololkin@yandex.ru 25.07.2021
 */
public class Arrays2 {
	/*
	Return the number of even ints in the given array.
	Note: the % "mod" operator computes the remainder, e.g. 5 % 2 is 1.
	 */
	public int countEvens(int[] nums) {

		return (int) Arrays.stream(nums).filter(x -> x % 2 == 0).count();
	}

	/*
	Given an array length 1 or more of ints, return the difference
	 between the largest and smallest values in the array.
	Note: the built-in Math.min(v1, v2) and Math.max(v1, v2) methods return the smaller or larger of two values.
	 */
	public int bigDiff(int[] nums) {
		int max = nums[0];
		int min = nums[0];

		for (int num : nums) {
			max = Math.max(num, max);
			min = Math.min(num, min);
		}
		return max - min;
	}

	/*
	Return the "centered" average of an array of ints,
	which we'll say is the mean average of the values,
	except ignoring the largest and smallest values in the array.
	If there are multiple copies of the smallest value, ignore just one copy, and likewise for the largest value.
	Use int division to produce the final average. You may assume that the array is length 3 or more.
	 */
	public int centeredAverage(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		int sum = 0;

		for (int num : nums) {
			if (num < min) {
				min = num;
			}
			if (num >= max) {
				max = num;
			}
			sum += num;
		}

		return (sum - min - max) / (nums.length - 2);
	}

	/*
	Return the sum of the numbers in the array, returning 0 for an empty array.
	Except the number 13 is very unlucky, so it does not count and numbers
	that come immediately after a 13 also do not count.
	 */
	public int sum13(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 13) {
				i++;
				continue;
			}
			sum += nums[i];
		}
		return sum;
	}

	/*
	Return the sum of the numbers in the array, except ignore sections of
	 numbers starting with a 6 and extending to the next 7
	 (every 6 will be followed by at least one 7). Return 0 for no numbers.
	 */
	public int sum67(int[] nums) {
		int sum = 0;
		boolean is6Founded = false;

		for (int num : nums) {
			if (num == 6) {
				is6Founded = true;
				continue;
			} else if (is6Founded) {
				if (num == 7) {
					is6Founded = false;
				}
				continue;
			}
			sum += num;
		}

		return sum;
	}

	/*
	Given an array of ints, return true if the array contains a 2 next to a 2 somewhere.
	 */
	public boolean has22(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 2 && nums[i + 1] == 2) {
				return true;
			}
		}
		return false;
	}

	/*
	Given an array of ints, return true if the array contains no 1's and no 3's.
	 */
	public boolean lucky13(int[] nums) {
		return Arrays.stream(nums).allMatch(num -> num != 1 && num != 3);
	}

	/*
	Given an array of ints, return true if the sum of all the 2's in the array is exactly 8.
	 */
	public boolean sum28(int[] nums) {
		return Arrays.stream(nums).reduce(0, (sum, x) -> x == 2 ? sum + x : sum) == 8;
	}

	/*
	Given an array of ints, return true if the number of 1's is greater than the number of 4's
	 */
	public boolean more14(int[] nums) {
		int numberOf1 = 0;
		int numberOf4 = 0;
		for (int num : nums) {
			if (num == 1) {
				numberOf1++;
			}
			if (num == 4) {
				numberOf4++;
			}
		}
		return numberOf1 > numberOf4;
	}

	/*
	Given a number n, create and return a new int array of length n,
	containing the numbers 0, 1, 2, ... n-1. The given n may be 0,
	in which case just return a length 0 array. You do not need a
	separate if-statement for the length-0 case; the for-loop should naturally execute 0 times in that case,
	so it just works. The syntax to make a new int array is: new int[desired_length]
	 */
	public int[] fizzArray(int n) {
		if (n == 0) {
			return new int[0];
		}
		int[] fizzArray = new int[n];
		for (int i = 0; i < n; i++) {
			fizzArray[i] = i;
		}
		return fizzArray;
	}

	/*
	Given an array of ints, return true if every element is a 1 or a 4.
	 */
	public boolean only14(int[] nums) {
		return Arrays.stream(nums).allMatch(x -> x == 1 || x == 4);
	}

	/*
	Given a number n, create and return a new string array of length n,
	containing the strings "0", "1" "2" .. through n-1. N may be 0,
	in which case just return a length 0 array. Note: String.valueOf(xxx) will make the String form of most types.
	The syntax to make a new string array is: new String[desired_length]
	 */
	public String[] fizzArray2(int n) {
		if (n == 0) {
			return new String[0];
		}
		String[] fizzArray = new String[n];
		for (int i = 0; i < n; i++) {
			fizzArray[i] = Integer.toString(i);
		}
		return fizzArray;
	}

	/*
	Given an array of ints, return true if it contains no 1's or it contains no 4's.
	 */
	public boolean no14(int[] nums) {
		boolean contain1 = false;
		boolean contain4 = false;
		for (int num : nums) {
			if (num == 1) {
				contain1 = true;
			}
			if (num == 4) {
				contain4 = true;
			}
			if (contain4 && contain1) {
				return false;
			}
		}
		return true;
	}

	/*
	We'll say that a value is "everywhere" in an array if for every pair
	of adjacent elements in the array, at least one of the pair is that value.
	Return true if the given value is everywhere in the array.
	 */
	public boolean isEverywhere(int[] nums, int val) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (!(nums[i] == val || nums[i + 1] == val)) {
				return false;
			}
		}
		return true;
	}

	/*
	Given an array of ints, return true if the array contains a 2 next to a 2 or a 4 next to a 4, but not both.
	 */
	public boolean either24(int[] nums) {
		boolean contain22 = false;
		boolean contain44 = false;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 2 && nums[i + 1] == 2) {
				contain22 = true;
			}
			if (nums[i] == 4 && nums[i + 1] == 4) {
				contain44 = true;
			}
			if (contain44 && contain22) {
				return false;
			}
		}
		return contain22 || contain44;
	}

	/*
	Given arrays nums1 and nums2 of the same length, for every element in nums1,
	consider the corresponding element in nums2 (at the same index).
	Return the count of the number of times
	that the two elements differ by 2 or less, but are not equal.
	matchUp([1, 2, 3], [2, 3, 10]) → 2
	matchUp([1, 2, 3], [2, 3, 5]) → 3
	matchUp([1, 2, 3], [2, 3, 3]) → 2
	 */
	public int matchUp(int[] nums1, int[] nums2) {
		int count = 0;
		for (int i = 0; i < nums1.length; i++) {
			int differ = Math.abs(nums1[i] - nums2[i]);
			if (differ > 0 && differ < 3) {
				count++;
			}
		}
		return count;
	}

	/*
	Given an array of ints, return true if
	the array contains two 7's next to each other,
	or there are two 7's separated by one element, such as with {7, 1, 7}.
	 */
	public boolean has77(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 7) {
				if (nums[i + 1] == 7) {
					return true;
				}
				if (i + 2 < nums.length && nums[i + 2] == 7) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	Given an array of ints, return true if there is a 1 in the array with a 2 somewhere later in the array.
	 */
	public boolean has12(int[] nums) {
		boolean contains1 = false;
		for (int num : nums) {
			if (num == 1) {
				contains1 = true;
			}
			if (contains1 && num == 2) {
				return true;
			}
		}
		return false;
	}

	/*
	Given an array of ints, return true if the array contains either 3 even or 3 odd values all next to each other.
	 */

	public boolean modThree(int[] nums) {
		int countEven = 0;
		int countOdd = 0;
		for (int num : nums) {
			if (num % 2 == 0) {
				countEven++;
				countOdd = 0;
			} else {
				countOdd++;
				countEven = 0;
			}

			if (countEven == 3 || countOdd == 3) {
				return true;
			}
		}

		return false;
	}

	/*
	Given an array of ints, return true if the
	value 3 appears in the array exactly 3 times, and no 3's are next to each other.
	 */
	public boolean haveThree(int[] nums) {
		boolean isPrev3 = false;
		int count3 = 0;
		for (int num : nums) {
			if (num == 3) {
				if (isPrev3) {
					return false;
				}
				isPrev3 = true;
				count3++;
			} else {
				isPrev3 = false;
			}
			if (count3 > 3) {
				return false;
			}
		}
		return count3 == 3;
	}

	/*
	Given an array of ints, return true if every 2 that appears in the array is next to another 2.
	 */
	public boolean twoTwo(int[] nums) {
		if (nums.length < 1) {
			return true;
		}
		if (nums.length == 1 && nums[0] == 2) {
			return false;
		}
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i] == 2 && nums[i - 1] != 2 && nums[i + 1] != 2) {
				return false;
			}
		}
		return !((nums[0] == 2 && nums[1] != 2) || (nums[nums.length - 1] == 2 && nums[nums.length - 2] != 2));
	}

	/*
	Return true if the group of N numbers at the start and end of the array are the same.
	For example, with {5, 6, 45, 99, 13, 5, 6}, the ends are the same for n=0 and n=2, and false for n=1 and n=3.
	You may assume that n is in the range 0..nums.length inclusive.
	sameEnds([5, 6, 45, 99, 13, 5, 6], 1) → false
	sameEnds([5, 6, 45, 99, 13, 5, 6], 2) → true
	sameEnds([5, 6, 45, 99, 13, 5, 6], 3) → false
	 */
	public boolean sameEnds(int[] nums, int len) {
		for (int i = 0; i < len; i++) {
			if (nums[i] != nums[nums.length - len + i]) {
				return false;
			}
		}
		return true;
	}

	/*
	Return true if the array contains, somewhere,
	three increasing adjacent numbers like .... 4, 5, 6, ... or 23, 24, 25.
	 */
	public boolean tripleUp(int[] nums) {
		for (int i = 1; i < nums.length - 1; i++) {
			if ((nums[i] - 1) == (nums[i - 1]) && ((nums[i] + 1) == (nums[i + 1]))) {
				return true;
			}
		}
		return false;
	}

	/*
	Given start and end numbers, return a new array containing the
	sequence of integers from start up to but not including end,
	so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end number will be greater or equal to the start number.
	Note that a length-0 array is valid. (See also: FizzBuzz Code)
	 */
	public int[] fizzArray3(int start, int end) {
		int[] resultArr = new int[end - start];
		for (int i = 0; i < end - start; i++) {
			resultArr[i] = start + i;
		}
		return resultArr;
	}

	/*
	int firstElem = nums[0];
		nums = Arrays.copyOfRange(nums, 1, nums.length + 1);
		nums[nums.length - 1] = firstElem;
		return nums;
	 */
	/*
	Return an array that is "left shifted" by one -- so {6, 2, 5, 3} returns {2, 5, 3, 6}.
	You may modify and return the given array, or return a new array.


	shiftLeft([6, 2, 5, 3]) → [2, 5, 3, 6]
	shiftLeft([1, 2]) → [2, 1]
	shiftLeft([1]) → [1]

	 */
	public int[] shiftLeft(int[] nums) {
		if (nums.length == 0) {
			return new int[0];
		}
		int firstElem = nums[0];
		nums = Arrays.copyOfRange(nums, 1, nums.length + 1);
		nums[nums.length - 1] = firstElem;
		return nums;
	}

	/*
	For each multiple of 10 in the given array, change all the values following it to be
	 that multiple of 10, until encountering another multiple of 10. So {2, 10, 3, 4, 20, 5}
	 yields {2, 10, 10, 10, 20, 20}.

	tenRun([2, 10, 3, 4, 20, 5]) → [2, 10, 10, 10, 20, 20]
	tenRun([10, 1, 20, 2]) → [10, 10, 20, 20]
	tenRun([10, 1, 9, 20]) → [10, 10, 10, 20]
	 */

	public int[] tenRun(int[] nums) {
		boolean isMultiple10Find = false;
		int multiple10Number = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 10 == 0) {
				multiple10Number = nums[i];
				isMultiple10Find = true;
			} else if (isMultiple10Find) {
				nums[i] = multiple10Number;
			}
		}
		return nums;
	}

	/*
	Given a non-empty array of ints, return a new array containing
	 the elements from the original array that come before the first 4 in the original array.
	The original array will contain at least one 4.
	Note that it is valid in java to create an array of length 0.
	 */
	public int[] pre4(int[] nums) {
		int indexOf4 = nums.length;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 4) {
				indexOf4 = i;
				break;
			}
		}
		return indexOf4 == nums.length ? nums : Arrays.copyOfRange(nums, 0, indexOf4);
	}

	/*
	Given a non-empty array of ints, return a new array containing
	the elements from the original array that come after the last 4 in the original array.
	The original array will contain at least one 4. Note that it is valid in java to create an array of length 0.
	 */

	public int[] post4(int[] nums) {
		int indexOf4 = -1;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 4) {
				indexOf4 = i;
			}
		}
		return indexOf4 == -1 ? nums : Arrays.copyOfRange(nums, indexOf4 + 1, nums.length);
	}

	/*
	We'll say that an element in an array is "alone"
	if there are values before and after it, and those values are different from it.
	Return a version of slsthe given array where every
	instance of the given value which is alone is replaced by whichever value to its left or right is larger.
	notAlone([1, 2, 3], 2) → [1, 3, 3]
	notAlone([1, 2, 3, 2, 5, 2], 2) → [1, 3, 3, 5, 5, 2]
	notAlone([3, 4], 3) → [3, 4]
	 */
	public int[] notAlone(int[] nums, int val) {
		for (int i = 1; i < nums.length - 1; i++) {
			if ((nums[i] == val) && (nums[i] != nums[i - 1]) && (nums[i + 1] != nums[i])) {
				nums[i] = Math.max(nums[i - 1], nums[i + 1]);
			}
		}
		return nums;
	}


	/**
	 * Return an array that contains the exact same numbers as the given array,
	 * but rearranged so that all the zeros are grouped at the start of the array.
	 * The order of the non-zero numbers does not matter.
	 * So {1, 0, 0, 1} becomes {0 ,0, 1, 1}. You may modify and return the given array or make a new array.
	 */
	public int[] zeroFront(int[] nums) {
		int indexLastZero = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				nums[i] = nums[indexLastZero + 1];
				nums[indexLastZero + 1] = 0;
				indexLastZero++;
			}
		}
		return nums;
	}

	/**
	 * Return a version of the given array where all the 10's have been removed.
	 * The remaining elements should shift left towards the start of the array as needed,
	 * and the empty spaces a the end of the array should be 0.
	 * So {1, 10, 10, 2} yields {1, 2, 0, 0}. You may modify and return the given array or make a new array.
	 */
	public int[] withoutTen(int[] nums) {
		int[] resultArray = new int[nums.length];
		int resultArrayIndex = 0;
		for (int num : nums) {
			if (num != 10) {
				resultArray[resultArrayIndex++] = num;
			}
		}
		return resultArray;
	}

	/**
	 * Return a version of the given array where each zero value in the array
	 * is replaced by the largest odd value to the right of the zero in the array.
	 * If there is no odd value to the right of the zero, leave the zero as a zero.
	 */
	public int[] zeroMax(int[] nums) {
		int maxOdd = Integer.MIN_VALUE;
		boolean isOddAtRight = false;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] % 2 == 1) {
				isOddAtRight = true;
				maxOdd = Math.max(nums[i], maxOdd);
			} else if (nums[i] == 0 && isOddAtRight) {
				nums[i] = maxOdd;
			}
		}
		return nums;
	}

	/**
	 * Return an array that contains the exact same numbers as the given array,
	 * but rearranged so that all the even numbers come before all the odd numbers.
	 * Other than that, the numbers can be in any order.
	 * You may modify and return the given array, or make a new array.
	 * <p>
	 * evenOdd([1, 0, 1, 0, 0, 1, 1]) → [0, 0, 0, 1, 1, 1, 1]
	 * evenOdd([3, 3, 2]) → [2, 3, 3]
	 * evenOdd([2, 2, 2]) → [2, 2, 2]
	 */
	public int[] evenOdd(int[] nums) {
		int[] evenOddArr = new int[nums.length];
		int indexEven = 0;
		int indexOdd = nums.length - 1;
		for (int num : nums) {
			if (num % 2 == 0) {
				evenOddArr[indexEven++] = num;
			} else {
				evenOddArr[indexOdd--] = num;
			}
		}
		return evenOddArr;
	}

	/**
	 * This is slightly more difficult version of the famous
	 * FizzBuzz problem which is sometimes given as a first problem
	 * for job interviews. (See also: FizzBuzz Code.)
	 * Consider the series of numbers beginning at start and running up to but not including end,
	 * so for example start=1 and end=5 gives the series 1, 2, 3, 4.
	 * Return a new String[] array containing the string form of these numbers,
	 * except for multiples of 3, use "Fizz" instead of the number, for multiples
	 * of 5 use "Buzz", and for multiples of both 3 and 5 use "FizzBuzz". In Java,
	 * String.valueOf(xxx) will make the String form of an int or other type.
	 * This version is a little more complicated than the usual version since you have
	 * to allocate and index into an array instead of just printing,
	 * and we vary the start/end instead of just always doing 1..100.
	 * <p>
	 * <p>
	 * fizzBuzz(1, 6) → ["1", "2", "Fizz", "4", "Buzz"]
	 * fizzBuzz(1, 8) → ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7"]
	 * fizzBuzz(1, 11) → ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"]
	 */
	public String[] fizzBuzz(int start, int end) {
		String[] fizzBuzzArr = new String[end - start];

		for (int i = start; i < end; i++) {
			if (i % 15 == 0) {
				fizzBuzzArr[i - start] = "FizzBuzz";
			} else if (i % 3 == 0) {
				fizzBuzzArr[i - start] = "Fizz";
			} else if (i % 5 == 0) {
				fizzBuzzArr[i - start] = "Buzz";
			} else {
				fizzBuzzArr[i - start] = Integer.toString(i);
			}
		}

		return fizzBuzzArr;
	}

	public static void main(String[] args) {
		Arrays2 arrays2 = new Arrays2();
//		System.out.println(arrays2.centeredAverage(new int[]{1, 2, 3, 4, 100}));
//		System.out.println(arrays2.centeredAverage(new int[]{-10, -4, -2, -4, -2, 0}));
//		System.out.println(arrays2.centeredAverage(new int[]{5, 3, 4, 6, 2}));
//		System.out.println(arrays2.sum28(new int[]{2, 3, 2, 2, 4, 2}));
//		System.out.println(arrays2.isEverywhere(new int[]{1, 2, 1, 3}, 1));
//		System.out.println(arrays2.isEverywhere(new int[]{1, 2, 1, 3, 4}, 1));
//		System.out.println(arrays2.twoTwo(new int[]{1, 2, 2, 3, 4}));
//		System.out.println(arrays2.sameEnds(new int[]{1, 2, 5, 2, 1}, 2));
//		System.out.println(Arrays.toString(arrays2.pre4(new int[]{4, 4})));
//		System.out.println(Arrays.toString(arrays2.post4(new int[]{2, 4, 1, 2})));
//		System.out.println(Arrays.toString(arrays2.zeroFront(new int[]{1, 0, 0, 1})));
//		System.out.println(Arrays.toString(arrays2.withoutTen(new int[]{1, 10, 10, 2})));
//		System.out.println(Arrays.toString(arrays2.fizzBuzz(1, 6)));

		Object[] objects = new Object[]{1, "aaa", 1L};
		System.out.println(Arrays.toString(objects));
	}
}