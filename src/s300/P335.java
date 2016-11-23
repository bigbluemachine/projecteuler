package s300;

import core.MathLib;

public class P335 {
	static long M = MathLib.pow64(7, 9);
	static long ord3M = 34588806;
	static long ord4M = 17294403;

	static long sumModExp(long a, long e, long m) {
		long ans = 0, t = 1;
		for (long i = 1; i <= e; i++)
			ans = (ans + (t = (t * a) % m)) % m;
		return ans;
	}

	static long geoMod(long a, long e, long p) {
		long ans = sumModExp(a, e % p, M), times = e / p;
		return times > 0 ? (ans + times * sumModExp(a, p, M)) % M : ans;
	}

	static long S(long k) {
		return -2L + MathLib.modExp(2, k + 3, M) + geoMod(4, k + 1, ord4M) - geoMod(3, k + 1, ord3M);
	}

	public static void main(String[] args) {
		long ans = S(1000000000000000000L - 1);
		System.out.println((ans + M) % M);
	}
}
