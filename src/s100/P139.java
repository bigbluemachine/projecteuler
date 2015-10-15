package s100;

import core.MathLib;

public class P139 {
	public static void main(String[] args) {
		int M = 100000000;
		long ans = 0;
		for (int n = 2;; n++) {
			if (2 * n * (n + 1) >= M) {
				break;
			}
			for (int m = (n & 1) + 1; m < n; m += 2) {
				if (MathLib.gcd32(m, n) != 1) {
					continue;
				}
				int a = n * n - m * m;
				int b = 2 * m * n;
				int c = n * n + m * m;
				int p = a + b + c;
				if (p >= M) {
					break;
				}
				int q = Math.abs(a - b);
				if (c % q == 0) {
					ans += (M - 1) / p;
				}
			}
		}
		System.out.println(ans);
	}
}
