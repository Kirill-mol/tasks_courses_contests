import java.util.Base64;

/**
 * @author Kirill Mololkin Kirill-mol 19.08.2021
 */
public class Main {

	public static void main(String[] args) {

		byte[] decoded = Base64.getDecoder().decode("SmF2YSDQvdC1INGC0L7RgNC80L7Qt9C40YIhCg==");
		System.out.println(new String(decoded));

	}

	public static String decode(String decodingStr, int diff) {
		StringBuilder sb = new StringBuilder();

		for (char c : decodingStr.toCharArray()) {
			sb.append((char)((int)c + diff));
		}

		return sb.toString();
	}
}
