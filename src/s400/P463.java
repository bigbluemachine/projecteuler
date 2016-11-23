package s400;

import java.math.BigInteger;

import core.MathLib;

public class P463 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static BigInteger s(int e, long t) {
		if (t == 1L << e) {
			return big(2).pow(2 * e);
		}
		long h = 1L << (e - 1);
		if (t <= h) {
			return big(2).multiply(s(e - 1, t)).subtract(big(t));
		}
		return s(e, h).add(s(e, t - h)).add(big(2).multiply(big(t - h)));
	}

	static BigInteger s(long n) {
		BigInteger ans = BigInteger.ZERO;
		int k = 0;
		long t = 1;
		while (t < n) {
			ans = ans.add(s(k, 1L << k));
			n -= t;
			t *= 2;
			k++;
		}
		ans = ans.add(s(k, n));
		return ans;
	}

	public static void main(String[] args) {
		long n = MathLib.pow64(3, 37);
		System.out.println(s(n).mod(big(1000000000)));
	}
}
