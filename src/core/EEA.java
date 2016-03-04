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

	public static BigInteger crt(long m1, long r1, long m2, long r2) {
		if (r2 < r1) {
			return crt(m2, r2, m1, r1);
		}
		long g = MathLib.gcd64(m1, m2);
		if ((r2 - r1) % g > 0) {
			return BigInteger.ZERO;
		}
		long n1 = m1 / g;
		long n2 = m2 / g;
		long z = (r2 - r1) / g;
		BigInteger j = EEA.inv(n1, n2);
		BigInteger lcm = big(m1).multiply(big(m2)).divide(big(g));
		BigInteger ans = j.multiply(big(z)).multiply(big(m1)).add(big(r1));
		return ans.mod(lcm);
	}
}
