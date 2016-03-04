package s200;

public class P206 {
	public static void main(String[] args) {
		// 1_2_3_4_5_6_7_8_9_0
		long min = 1010101030;
		long max = 1389026570;
		L: for (long n = min; n <= max; n += (n % 100 == 30 ? 40 : 60)) {
			String s = Long.toString(n * n);
			for (int i = 0; i < 10; i++) {
				if (s.charAt(2 * i) - '0' != (i + 1) % 10) {
					continue L;
				}
			}
			System.out.println(s);
			System.out.println(n);
			break;
		}
	}
}
