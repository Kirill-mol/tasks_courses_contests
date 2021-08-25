package codingbat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kirill Mololkin kir.mololkin@yandex.ru 27.07.2021
 */
public class Logic2 {

	/**
	 * We want to make a row of bricks that is goal inches long.
	 * We have a number of small bricks (1 inch each) and big bricks (5 inches each).
	 * Return true if it is possible to make the goal by choosing from the given bricks.
	 * This is a little harder than it looks and can be done without any loops.
	 */
	public boolean makeBricks(int small, int big, int goal) {
		int usedBigBricks = goal / 5;
		if (usedBigBricks > big) {
			usedBigBricks = big;
		}
		goal -= usedBigBricks * 5;
		return goal <= small;
	}

	/**
	 * Given 3 int values, a b c, return their sum.
	 * However, if one of the values is the same as another of the values, it does not count towards the sum.
	 */
	public int loneSum(int a, int b, int c) {
		if (a == b && a == c) {
			return 0;
		} else if (a == b) {
			return c;
		} else if (b == c) {
			return a;
		} else if (a == c) {
			return b;
		}
		return a + b + c;
	}

	/**
	 Given 3 int values, a b c, return their sum. However,
	 if one of the values is 13 then it does not count towards the sum and values to its right
	 do not count. So for example, if b is 13, then both b and c do not count.
	 */
	public int luckySum(int... nums) {
		int sum = 0;
		for (int num : nums) {
			if (num == 13) {
				return sum;
			}
			sum += num;
		}
		return sum;
	}

	/**
	 * Given 3 int values, a b c, return their sum. However,
	 * if any of the values is a teen -- in the range 13..19 inclusive --
	 * then that value counts as 0, except 15 and 16 do not count as a teens.
	 * Write a separate helper "public int fixTeen(int n)
	 * {"that takes in an int value and returns that value fixed for the teen rule.
	 * In this way, you avoid repeating the teen code 3 times (i.e. "decomposition").
	 * Define the helper below and at the same indent level as the main noTeenSum().
	 *
	 *
	 * noTeenSum(1, 2, 3) → 6
	 * noTeenSum(2, 13, 1) → 3
	 * noTeenSum(2, 1, 14) → 3
	 */

	public int noTeenSum(int a, int b, int c) {
		return (isTeen(a) ? 0 : a) + (isTeen(b) ? 0 : b) + (isTeen(c) ? 0 : c);
	}

	public boolean isTeen(int num) {
		return num > 12 && num < 20 && num != 15 && num != 16;
	}

	/**
	 *
	 * For this problem, we'll round an int value up to the next multiple
	 * of 10 if its rightmost digit is 5 or more, so 15 rounds up to 20.
	 * Alternately, round down to the previous multiple of 10 if
	 * its rightmost digit is less than 5, so 12 rounds down to 10.
	 * Given 3 ints, a b c, return the sum of their rounded values.
	 * To avoid code repetition, write a separate helper "public int round10(int num) {" and call it 3 times.
	 * Write the helper entirely below and at the same indent level as roundSum().
	 */
	public int roundSum(int a, int b, int c) {
		return round10(a) + round10(b) + round10(c);
	}

	public int round10(int num) {
		int i = num % 10;
		return i > 4 ? num + (10 - i) : num - i;
	}

	/**
	 * Given three ints, a b c, return true if one of b or c is
	 * "close" (differing from a by at most 1), while the other is "far",
	 * differing from both other values by 2 or more.
	 * Note: Math.abs(num) computes the absolute value of a number.
	 */
	public boolean closeFar(int a, int b, int c) {
		if (isClose(a, b)) {
			return isFar(c, a, b);
		}
		if (isClose(a, c))  {
			return isFar(b, a, c);
		}
		return false;
	}

	public boolean isClose(int a, int b) {
		return Math.abs(a - b) < 2;
	}

	public boolean isFar(int a, int b, int c) {
		return Math.abs(a - c) > 1 && Math.abs(a - b) > 1;
	}

	/**
	 * Given three ints, a b c, one of them is small, one is medium and one is large.
	 * Return true if the three values are evenly spaced,
	 * so the difference between small and medium is the same as the difference between medium and large.
	 *
	 *
	 * evenlySpaced(2, 4, 6) → true
	 * evenlySpaced(4, 6, 2) → true
	 * evenlySpaced(4, 6, 3) → false
	 */
	public boolean evenlySpaced(int... nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i] - nums[i - 1] != nums[i + 1] - nums[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * We want make a package of goal kilos of chocolate.
	 * We have small bars (1 kilo each) and big bars (5 kilos each). Return the number of
	 * small bars to use, assuming we always use big bars before small bars. Return -1 if it can't be done.
	 */
	public int makeChocolate(int small, int big, int goal) {
		int usedBigBricks = goal / 5;
		if (usedBigBricks > big) {
			usedBigBricks = big;
		}
		goal -= usedBigBricks * 5;
		return goal <= small ? goal : -1;
	}

	public static void main(String[] args) {
		Logic2 logic2 = new Logic2();
//		System.out.println(logic2.round10(5));
//		System.out.println(logic2.round10(6));
//		System.out.println(logic2.round10(7));
//		System.out.println(logic2.round10(8));
//		System.out.println(logic2.round10(9));
		System.out.println("result " + logic2.round10(11));
		System.out.println("result " + logic2.round10(12));
		System.out.println("result " + logic2.round10(13));
		System.out.println("result " + logic2.round10(14));
		System.out.println("result " + logic2.round10(15));
		System.out.println("result " + logic2.round10(16));
		System.out.println("result " + logic2.round10(17));
		System.out.println("result " + logic2.round10(18));
		System.out.println("result " + logic2.round10(19));
		System.out.println("result " + logic2.round10(20));
	}
}
