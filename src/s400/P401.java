package s400;

import java.math.BigInteger;

public class P401 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static long M = 1000000000L;

	static long s2(long n) {
		int s = (int) Math.sqrt(n);
		int k = (int) (n / (s + 1));

		BigInteger ans = big(0);

		for (int i = k; i >= 1; i--) {
			long p = n / (i + 1);
			long q = n / i;
			ans = ans.add(big(i).multiply(f(p + 1, q))).mod(big(M));
		}

		for (int i = s; i >= 1; i--)
			ans = ans.add(big(n / i).multiply(big(i).pow(2))).mod(big(M));

		return ans.longValue();
	}

	// Sum squares 1 to n
	static BigInteger f(long n) {
		BigInteger ans = big(n);
		ans = ans.multiply(big(n + 1));
		ans = ans.multiply(big(n + n + 1));
		return ans = ans.divide(big(6));
	}

	// Sum squares m to n
	static BigInteger f(long m, long n) {
		return f(n).subtract(f(m - 1));
	}

	public static void main(String[] args) {
		// Please optimize...
		System.out.println(s2(1000000000000000L));
	}
}
