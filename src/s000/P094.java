package s000;

import core.MathLib;

public class P094 {
	public static void main(String[] args) {
		long U = 1000000000;
		long ans = 0;
		for (int n = 2;; n++) {
			if (2L * n * (n + 1) > U) {
				break;
			}
			for (int m = 1 + n % 2; m < n && 2L * n * (n + m) <= U; m += 2) {
				if (MathLib.gcd32(m, n) != 1) {
					continue;
				}
				int a = n * n - m * m;
				int b = 2 * n * m;
				int c = n * n + m * m;
				if (Math.abs(c - a - a) == 1) {
					ans += c + a;
				}
				if (Math.abs(c - b - b) == 1) {
					ans += c + b;
				}
			}
		}
		System.out.println(2 * ans);
	}
}
