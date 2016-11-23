package s300;

import core.NTLib;

public class P357 {
	static boolean f(int n, long[] P) {
		for (int d = 2; (long) d * d <= n; d++) {
			if (n % d > 0) {
				continue;
			}
			int t = d + n / d;
			if (!NTLib.isPrime(P, t)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		long ans = 1;
		int M = 100000000;
		long[] P = NTLib.bitSieve(M);
		for (int n = 2; n <= M; n += 2) {
			if (NTLib.isPrime(P, n + 1)) {
				if (f(n, P)) {
					ans += n;
				}
			}
		}
		System.out.println(ans);
	}
}
