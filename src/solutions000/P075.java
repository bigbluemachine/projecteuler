package s000;

import core.MathLib;

public class P075 {
	public static void main(String[] args) {
		int[] v = new int[1500001];
		for (int m = 2;; m++) {
			if (2L * m * m > 1500000) {
				break;
			}
			for (int n = 1 + (m & 1); n < m; n += 2) {
				long p = 2L * m * (m + n);
				if (p > 1500000) {
					break;
				}
				if (MathLib.gcd32(m, n) != 1) {
					continue;
				}
				for (long k = p; k <= 1500000; k += p) {
					v[(int) k]++;
				}
			}
		}

		int ans = 0;
		for (int i = 12; i <= 1500000; i++) {
			if (v[i] == 1) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
