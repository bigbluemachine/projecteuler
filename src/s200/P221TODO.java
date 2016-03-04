package s200;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.TreeSet;

public class P221TODO {
	static class S<T> {
		TreeSet<T> S = new TreeSet<T>();
		int capacity;

		S(int c) {
			capacity = c;
		}

		void add(T t) {
			S.add(t);
			if (S.size() > capacity) {
				S.remove(S.last());
			}
		}
	}

	static class Z implements Comparable<Z> {
		long p, q, r;

		Z(long a, long b, long c) {
			long[] n = new long[] { a, b, c };
			Arrays.sort(n);
			p = n[0];
			q = n[1];
			r = n[2];
		}

		@Override
		public int compareTo(Z z) {
			double a = Math.log(p) + Math.log(q) + Math.log(r);
			double b = Math.log(z.p) + Math.log(z.q) + Math.log(z.r);
			return Double.compare(a, b);
		}

		public String toString() {
			return String.format("%d * %d * %d", p, q, r);
		}
	}

	static void gen(int m, int k, S<Z> S) {
		for (int r = k;; r += m) {
			long p = r + m;
			long q = (1 + p * r) / m;
			Z z = new Z(p, q, r);
			if (z.compareTo(S.S.last()) > 0) {
				break;
			}
			S.add(z);
		}
	}

	public static void main(String[] args) {
		// TODO ~2m
		int n = 150000;

		S<Z> S = new S<Z>(n);
		for (long p = 2; p <= n + 1; p++) {
			S.add(new Z(p, p * p - p + 1, p - 1));
		}

		for (int m = 2; m <= n; m++) {
			for (int x = (int) Math.sqrt(m); x + x <= m; x++) {
				if (((long) x * x + 1) % m == 0) {
					gen(m, x, S);
					gen(m, m - x, S);
				}
			}
		}

		Z z = S.S.last();
		BigInteger ans = BigInteger.ONE;
		ans = ans.multiply(BigInteger.valueOf(z.p));
		ans = ans.multiply(BigInteger.valueOf(z.q));
		ans = ans.multiply(BigInteger.valueOf(z.r));
		System.out.println(ans);
		System.out.println(z);
	}
}
