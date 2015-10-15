package core;

import java.math.BigInteger;

// Extended Euclidean algorithm.
public class EEA {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	// Returns [inverse, gcd].
	public static BigInteger[] eea(long N, long M) {
		BigInteger inv;
		BigInteger y, px, py, q, t1, t2, n, m;

		n = big(N);
		m = big(M);
		y = px = BigInteger.ONE;
		py = inv = BigInteger.ZERO;

		while (!m.equals(BigInteger.ZERO)) {
			q = n.divide(m);

			t1 = n.mod(m);
			n = m;
			m = t1;

			t1 = inv;
			t2 = q.multiply(inv);
			inv = px.subtract(t2);

			px = t1;

			t1 = y;
			t2 = q.multiply(y);
			y = py.subtract(t2);

			py = t1;
		}

		return new BigInteger[] { px, n };
	}

	// Only well-defined if GCD = 1.
	public static BigInteger inv(long N, long M) {
		BigInteger[] v = eea(N, M);
		if (v[1].equals(BigInteger.ONE)) {
			if (v[0].signum() < 0) {
				v[0] = v[0].add(big(M));
			}
			return v[0];
		}
		return BigInteger.ZERO;
	}

	// Chinese remaineder theorem.
	public static BigInteger crt(long a1, long m1, long a2, long m2) {
		BigInteger b1 = inv(m2, m1);
		BigInteger b2 = inv(m1, m2);
		BigInteger M = big(m1).multiply(big(m2));
		BigInteger N = big(a1).multiply(b1.multiply(big(m2)));
		BigInteger O = big(a2).multiply(b2.multiply(big(m1)));
		return N.add(O).mod(M);
	}
}
