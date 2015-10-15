package core;

public class Selector {
	private int n;
	private long v, w;

	public Selector(int n, int k) {
		if (n > 62 || k > n || 1 > k) {
			throw new IllegalArgumentException("Constraint: 1 <= k <= n <= 62");
		}

		this.n = n;
		v = (1L << k) - 1;
		w = v << (n - k);
	}

	public long next() {
		long next = v;
		long x = (v | (v - 1)) + 1;
		v = x | ((((x & -x) / (v & -v)) >> 1) - 1);
		return next;
	}

	public boolean hasNext() {
		return v <= w;
	}

	public long inverse(long v) {
		return ((1L << n) - 1) & ~v;
	}
}
