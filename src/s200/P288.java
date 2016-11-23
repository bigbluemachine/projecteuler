package s200;

import java.math.BigInteger;

import core.MathLib;

public class P288 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	public static void main(String[] args) {
		int p = 61;
		int q = 10000000;
		long M = MathLib.pow64(61, 10);

		int[] T = new int[q + 1];
		long s = 290797;
		for (int i = 0; i <= q; i++) {
			T[i] = (int) (s % p);
			s = s * s % (50515093);
		}

		long x = 0;
		long[] U = new long[q + 1];
		for (int i = q; i >= 0; i--) {
			BigInteger t = big(p).multiply(big(x));
			t = t.add(big(T[i]));
			x = t.mod(big(M)).longValue();
			U[i] = x;
		}

		long ans = 0;
		for (int i = 1; i <= q; i++) {
			ans = (ans + U[i]) % M;
		}
		System.out.println(ans);
	}
}
