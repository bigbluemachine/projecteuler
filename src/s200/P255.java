package s200;

import core.MathLib;

public class P255 {
	static int f(long n) {
		long x = X;
		for (int i = 1;; i++) {
			long y = (x + ((n + x - 1) / x)) >> 1;
			if (y == x)
				return i;
			x = y;
		}
	}

	static long lower(long s) {
		double t = (double) s - 0.5;
		return (long) Math.ceil(t * t);
	}

	static long upper(long s) {
		double t = (double) s + 0.5;
		return (long) Math.floor(t * t);
	}

	static long search(long a, long b, double d) {
		if (a == b)
			return f(a) > d ? a - 1 : a;
		if (a + 1 == b)
			return f(a) > d ? a - 1 : f(b) > d ? a : b;
		long m = (a + b) / 2;
		return f(m) > d ? search(a, m - 1, d) : search(m + 1, b, d);
	}

	static int D;
	static long X;

	public static void main(String[] args) throws Exception {
		// 5 digits = 3.2102888889
		// 14 digits = 4.4474011180

		D = 14;
		X = D % 2 == 0 ? 7 * MathLib.pow64(10, (D - 2) / 2) : 2 * MathLib.pow64(10, (D - 1) / 2);

		long min = MathLib.pow64(10, D - 1);
		long max = MathLib.pow64(10, D) - 1;

		long minS = (long) Math.round(Math.sqrt(min));
		long maxS = (long) Math.round(Math.sqrt(max));

		long total = 0;
		for (long i = minS; i <= maxS; i++) {
			long a = Math.max(min, lower(i));
			long b = Math.min(max, upper(i));

			int x = f(a);
			int y = f(b);

			if (x == y) {
				total += (b - a + 1) * x;
			} else if (x + 1 == y) {
				long j = search(a, b, x + 0.5);
				total += (j - a + 1) * x;
				total += (b - j) * (x + 1);
			} else {
				long j = search(a, b, x + 0.5);
				long k = search(j, b, x + 1.5);
				total += (j - a + 1) * x;
				total += (k - j) * (x + 1);
				total += (b - k) * (x + 2);
			}
		}

		double ans = (double) total / (max - min + 1);
		System.out.println(ans);
	}
}
