package s100;

import java.math.BigInteger;

public class P104 {
	static boolean pandigital(String s) {
		int v = 1;
		for (char c : s.toCharArray()) {
			int d = c - '0';
			v |= 1 << d;
		}
		return v == 1023;
	}

	public static void main(String[] args) {
		BigInteger M = BigInteger.valueOf(1000000000L);
		BigInteger A = BigInteger.ONE;
		BigInteger B = BigInteger.ONE;
		int k = 1;

		while (true) {
			k++;
			String t = B.mod(M).toString();
			if (pandigital(t)) {
				String b = B.toString();
				if (pandigital(b.substring(0, 9))) {
					System.out.println("Answer: " + k);
					System.out.println("Length: " + b.length());
					break;
				}
			}
			BigInteger C = B;
			B = B.add(A);
			A = C;
		}
	}
}
