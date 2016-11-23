package s100;

import core.NTLib;

public class P146TODO {
	static int ITER = 5;

	// TODO ~2m
	public static void main(String[] args) {
		int[] a = { 1, 3, 7, 9, 13, 27 };
		// 676333270
		long ans = 0;
		L: for (int n = 10; n < 150000000; n += 10) {
			if (n % 3 == 0 || n % 7 == 0 || n % 13 == 0) {
				continue;
			}
			long n2 = (long) n * n;
			for (int x : a) {
				if (!NTLib.MillerRabin(n2 + x, ITER)) {
					continue L;
				}
			}
			if (NTLib.MillerRabin(n2 + 19, ITER)) {
				continue;
			}
			if (NTLib.MillerRabin(n2 + 21, ITER)) {
				continue;
			}
			ans += n;
		}
		System.out.println(ans);
	}
}
