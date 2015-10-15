package s000;

import java.math.BigInteger;

public class P066 {
	public static void main(String[] args) {
		BigInteger best = BigInteger.ZERO;
		int ans = -1;
		for (int d = 1; d <= 1000; d++) {
			int s = (int) Math.sqrt(d);
			if (s * s == d) {
				continue;
			}
			BigInteger test = magic(d);
			if (test.compareTo(best) > 0) {
				best = test;
				ans = d;
			}
		}
		System.out.println(ans);
		System.out.println(best);
	}

	static BigInteger magic(int C) {
		BigInteger c = BigInteger.valueOf(C);
		BigInteger x = BigInteger.ONE;
		BigInteger y = BigInteger.valueOf((long) Math.sqrt(C));
		BigInteger k = y.pow(2).subtract(c);
		BigInteger M, X, K, Y, T;
		while (k.compareTo(BigInteger.ONE) != 0) {
			M = BigInteger.valueOf((long) Math.ceil(Math.sqrt(C)));
			while ((M.multiply(x).add(y)).mod(k.abs()).compareTo(BigInteger.ZERO) > 0) {
				M = M.add(BigInteger.ONE);
			}
			T = M;
			while (M.compareTo(BigInteger.ZERO) > 0) {
				M = M.subtract(k.abs());
				if (M.compareTo(BigInteger.ZERO) < 0) {
					break;
				}
				BigInteger cm = c.subtract(M.pow(2)).abs();
				BigInteger ct = c.subtract(T.pow(2)).abs();
				if (cm.compareTo(ct) < 0) {
					T = M;
				}
			}
			M = T;
			X = M.multiply(x).add(y).divide(k);
			K = M.pow(2).subtract(c).divide(k);
			Y = M.multiply(y).add(c.multiply(x)).divide(k);
			x = X.abs();
			k = K;
			y = Y.abs();
		}
		return y;
	}
}
