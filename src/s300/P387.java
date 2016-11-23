package s300;

import java.util.TreeSet;

import core.MathLib;
import core.NTLib;

public class P387 {
	static void search(long x, TreeSet<Long> H, long M) {
		if (x >= M) {
			return;
		}
		H.add(x);
		for (int d = 0; d <= 9; d++) {
			long y = 10 * x + d;
			if (y % MathLib.digitSum(y) == 0) {
				search(y, H, M);
			}
		}
	}

	public static void main(String[] args) {
		long M = 10000000000000L;
		TreeSet<Long> H = new TreeSet<Long>();
		for (int d = 1; d <= 9; d++) {
			search(d, H, M);
		}

		TreeSet<Long> S = new TreeSet<Long>();
		for (long h : H) {
			long t = h / MathLib.digitSum(h);
			if (t > 3 && NTLib.MillerRabin(t, 10)) {
				S.add(h);
			} else if (t == 2 || t == 3) {
				S.add(h);
			}
		}

		long ans = 0;
		for (long s : S) {
			for (int d = 1; d <= 9; d += 2) {
				long t = 10 * s + d;
				if (NTLib.MillerRabin(t, 10)) {
					ans += t;
				}
			}
		}
		System.out.println(ans);
	}
}
