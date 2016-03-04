package s200;

import java.math.BigInteger;

public class P284 {
	static long gen(int b, int d) {
		BigInteger B = BigInteger.valueOf(b);
		BigInteger N = BigInteger.valueOf(d);
		BigInteger P = BigInteger.valueOf(b);
		int m = (2 * d) % b;

		long ans = d;
		long temp = d;
		for (int i = 1; i < 10000; i++) {
			int r = N.multiply(N).divide(P).mod(B).intValue();
			int x = 0;
			for (; x < b; x++) {
				if ((m * x + r) % b == x) {
					break;
				}
			}
			N = N.add(P.multiply(BigInteger.valueOf(x)));
			if (x != 0) {
				temp += x;
				ans += temp;
			}
			P = P.multiply(BigInteger.valueOf(b));
		}
		return ans;
	}

	static long gen(int b) {
		long ans = 0;
		for (int d = 1; d < b; d++) {
			if ((d * d) % b == d) {
				ans += gen(b, d);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		long ans = gen(14);
		String s = "";
		while (ans > 0) {
			int d = (int) (ans % 14);
			ans /= 14;
			if (d < 10) {
				s = d + s;
			} else {
				s = (char) ('a' + d - 10) + s;
			}
		}
		System.out.println(s);
	}
}
