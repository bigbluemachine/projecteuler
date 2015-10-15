package s100;

import core.MathLib;

public class P188 {
	static long f(long b, long m) {
		long ans = 0;
		long x = b % m;
		do {
			ans++;
			x = (x * b) % m;
		} while (x != b % m);
		return ans;
	}

	static long t(long b, long k, long m) {
		if (k == 1) {
			return b % m;
		}
		long p = f(b, m);
		long r = t(b, k - 1, p);
		return MathLib.modExp(b, r, m);
	}

	public static void main(String[] args) {
		System.out.println(t(1777, 1855, 100000000));
	}
}
