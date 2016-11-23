package s100;

import core.MathLib;

public class P160 {
	static long MOD = 100000;

	static long mod1379(long n) {
		if (n <= MOD) {
			long ans = 1;
			for (int i = 1; i <= n; i += 2) {
				if (i % 5 > 0) {
					ans = (ans * i) % MOD;
				}
			}
			return ans;
		}
		return MathLib.modExp(mod1379(MOD), n / MOD, MOD) * mod1379(n % MOD);
	}

	static long odd(long n, long[] fives) {
		long ans = mod1379(n);
		if (n >= 5) {
			fives[0] += (n / 5 + 1) / 2;
			ans = (ans * odd(n / 5, fives)) % MOD;
		}
		return ans;
	}

	static long fac(long n) {
		if (n == 1) {
			return 1;
		}
		long ans = fac(n / 2);
		long[] fives = new long[1];
		ans = (ans * odd(n, fives)) % MOD;
		ans = (ans * MathLib.modExp(2, n / 2 - fives[0], MOD)) % MOD;
		return ans;
	}

	public static void main(String[] args) {
		// 100 : 16864
		// 1000 : 53472
		// 10000 : 79008
		// 20000 : 62496
		// 30000 : 27296
		// 40000 : 12544
		// 100000 : 62496
		// 1000000 : 12544
		// 2000000 : 94688
		// 3000000 : 20736
		// 4000000 : 54176

		// 16576
		System.out.println(fac(1000000000000L));
	}
}
