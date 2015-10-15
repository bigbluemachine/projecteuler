package core;

import java.math.BigInteger;

public class BigRatio implements Comparable<BigRatio> {
	public static boolean AUTO_REDUCE = true;
	public BigInteger n, d;
	public static BigRatio ZERO = new BigRatio(BigInteger.ZERO, BigInteger.ONE);
	public static BigRatio ONE = new BigRatio(BigInteger.ONE, BigInteger.ONE);

	public BigRatio(BigInteger n, BigInteger d) {
		if (d.compareTo(BigInteger.ZERO) < 0) {
			n = n.negate();
			d = d.negate();
		}
		if (AUTO_REDUCE) {
			BigInteger g = n.gcd(d);
			n = n.divide(g);
			d = d.divide(g);
		}
		this.n = n;
		this.d = d;
	}

	public BigRatio add(BigRatio r) {
		return new BigRatio(n.multiply(r.d).add(d.multiply(r.n)), d.multiply(r.d));
	}

	public BigRatio sub(BigRatio r) {
		return new BigRatio(n.multiply(r.d).subtract(d.multiply(r.n)), d.multiply(r.d));
	}

	public BigRatio mul(BigRatio r) {
		return new BigRatio(n.multiply(r.n), d.multiply(r.d));
	}

	public BigRatio div(BigRatio r) {
		return new BigRatio(n.multiply(r.d), d.multiply(r.n));
	}

	public BigRatio inv() {
		return new BigRatio(d, n);
	}

	public int compareTo(BigRatio r) {
		return n.multiply(r.d).compareTo(d.multiply(r.n));
	}

	public String toString() {
		return String.format("%s/%s", n, d);
	}
}
