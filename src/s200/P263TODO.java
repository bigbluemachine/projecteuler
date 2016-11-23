package s200;

import java.util.List;

import core.MathLib;
import core.NTLib;
import core.PFac;

public class P263TODO {
	static List<Integer> P;

	static boolean p(long n) {
		return NTLib.MillerRabin(n, 3);
	}

	static boolean q(long n) {
		PFac pf = PFac.make(n, P);
		long t = (1L << (pf.twos + 1)) - 1;
		for (long p : pf.M.keySet()) {
			if (p > 1 + t) {
				return false;
			}
			long u = MathLib.pow64(p, pf.M.get(p) + 1) - 1;
			u /= (p - 1);
			t *= u;
		}
		return true;
	}

	static boolean[] filter(int m) {
		int[] t = new int[m];
		for (int i = 0; i < m; i++) {
			t[i] = (10 * i) % m;
		}
		int[] v = { 1, 7, 13, 19 };
		boolean[] ans = new boolean[m];
		for (int i = 0; i < m; i++) {
			int r = (m - t[i]) % m;
			for (int j : v) {
				if (r == j % m) {
					ans[i] = true;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		P = NTLib.primeList(1000000);

		// TODO ~90s
		boolean[] f3 = filter(3);
		boolean[] f7 = filter(7);
		boolean[] f11 = filter(11);
		boolean[] f13 = filter(13);
		boolean[] f19 = filter(19);

		long ans = 0;
		int count = 0;

		for (int i = 1;; i++) {
			if (f3[i % 3] || f7[i % 7] || f11[i % 11] || f13[i % 13] || f19[i % 19]) {
				continue;
			}

			long x = 10L * i;
			long a = x + 1;
			long b = x + 7;
			long c = x + 13;
			long d = x + 19;

			if (p(a) && p(b) && p(c) && p(d)) {
				if (!p(x + 3) && !p(x + 9) && !p(x + 11) && !p(x + 17)) {
					if (q(x + 2) && q(x + 6) && q(x + 10) && q(x + 14) && q(x + 18)) {
						ans += x + 10;
						if (++count == 4) {
							break;
						}
					}
				}
			}
		}

		System.out.println(ans);
	}
}
