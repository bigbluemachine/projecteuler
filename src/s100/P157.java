package s100;

import java.util.TreeSet;

import core.MathLib;

public class P157 {
	static TreeSet<Long> gen(int t, int f) {
		TreeSet<Long> ans = new TreeSet<Long>();
		for (int i = 0; i <= t; i++) {
			for (int j = 0; j <= f; j++) {
				ans.add(MathLib.pow64(2, i) * MathLib.pow64(5, j));
			}
		}
		return ans;
	}

	static int div(long n) {
		int ans = 0;
		int i = 1;
		for (; i * i < n; i++) {
			if (n % i == 0) {
				ans += 2;
			}
		}
		if (i * i == n) {
			ans++;
		}
		return ans;
	}

	static long f(int e) {
		TreeSet<Long> S = gen(e, e);
		long x = MathLib.pow32(10, e);
		long ans = 0;
		for (long p : S) {
			for (long q : S) {
				if (p <= q && MathLib.gcd64(p, q) == 1) {
					long t = (long) p * q;
					if (x % t == 0) {
						ans += div((x * (p + q)) / (p * q));
					}
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int e = 1; e <= 9; e++) {
			ans += f(e);
		}
		System.out.println(ans);
	}
}
