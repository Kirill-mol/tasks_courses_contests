import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kirill Mololkin Kirill-mol 19.08.2021
 */
public class Main {

	public static void main(String[] args) {

//		byte[] decoded = Base64.getDecoder().decode("SmF2YSDQvdC1INGC0L7RgNC80L7Qt9C40YIhCg==");
//		System.out.println(new String(decoded));

//		System.out.println(getResult(new ArrayList<>(List.of(57, 45, 54, 48, 23, 46, 89, 67, 25, 78, 59, 71)), 611));

		List<Object> list = new ArrayList<>();

		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
	}

	public static String decode(String decodingStr, int diff) {
		StringBuilder sb = new StringBuilder();

		for (char c : decodingStr.toCharArray()) {
			sb.append((char)((int)c + diff));
		}

		return sb.toString();
	}

	public static boolean isValid(String s) {
		// Write your code here...
		int counterDots = 0;
		int counterNumbers = 0;

		if (s.length() < 6) {
			return false;
		}

		for (char c : s.toCharArray()) {
			if(c == '.') {
				counterDots++;
			} else if (Character.isDigit(c)) {
				counterNumbers++;
			}
		}

		return (counterDots == 3) && (counterDots + counterNumbers == s.length());
	}

	public static int getResult(List<Integer> cash, int s) {
		// Write your code here...
		while(s > 2) {
			int indexMin = searchMin(cash);
			cash.set(indexMin, cash.get(indexMin) + 3);
			s -= 3;
		}

		int indexMin = searchMin(cash);
		cash.set(indexMin, cash.get(indexMin) + s);

		int max = Integer.MIN_VALUE;
		int countMax = 0;

		for (Integer integer : cash) {
			if (integer > max) {
				max = integer;
				countMax = 1;
			} else if (integer == max) {
				countMax++;
			}
		}

		return cash.size() - countMax;
	}

	public static int searchMin(List<Integer> cash) {
		int min = cash.get(0);
		int indexMin = 0;
		for (int i = 1; i < cash.size(); i++) {
			if (min > cash.get(i)) {
				min = cash.get(i);
				indexMin = i;
			}
		}

		return indexMin;
	}
}
