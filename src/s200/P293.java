package s200;

import java.util.TreeSet;

import core.NTLib;

public class P293 {
	static TreeSet<Integer> S = new TreeSet<Integer>();
	static boolean[] P = NTLib.simpleSieve(10000000);

	static int g(int n) {
		for (int i = n + 3; i < P.length; i += 2) {
			if (P[i]) {
				return i - n;
			}
		}
		for (int i = 3;; i += 2) {
			if (NTLib.MillerRabin(n + i, 5)) {
				return i;
			}
		}
	}

	static void f(long n, int[] ps, int i) {
		if (n >= 1000000000) {
			return;
		}

		S.add(g((int) n));

		f(n * ps[i], ps, i);
		if (i + 1 < ps.length) {
			f(n * ps[i + 1], ps, i + 1);
		}
	}

	public static void main(String[] args) {
		int[] ps = { 2, 3, 5, 7, 11, 13, 17, 19, 23 };
		f(2, ps, 0);

		long ans = 0;
		for (int i : S) {
			ans += i;
		}
		System.out.println(ans);
	}
}
