package s100;

import core.NTLib;

public class P146TODO {
	static int ITER = 5;

	// TODO slow
	public static void main(String[] args) {
		int[] a = { 1, 3, 7, 9, 13, 27 };
		int[] b = { 11, 17, 19, 21, 23 };

		long ans = 0;
		L: for (int n = 10; n < 150000000; n += 10) {
			long n2 = (long) n * n;
			for (int x : a) {
				if (!NTLib.MillerRabin(n2 + x, ITER)) {
					continue L;
				}
			}
			for (int x : b) {
				if (NTLib.MillerRabin(n2 + x, ITER)) {
					continue L;
				}
			}
			ans += n;
		}

		System.out.println(ans);
	}
}
