package s100;

import core.NTLib;

public class P128 {
	static boolean[] P = NTLib.simpleSieve(10000000);

	static int process(long[] D) {
		for (int j = 0; j < 3; j++) {
			if (!P[(int) D[j]]) {
				return 0;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		int T = 2000;
		int k = 2;
		long n;
		for (long r = 2;; r++) {
			n = 2 + 3 * r * (r - 1);
			k += process(new long[] { 6 * r - 1, 6 * r + 1, 12 * r + 5 });
			if (k == T) {
				System.out.println(n);
				return;
			}
			n = 2 + 3 * r * (r + 1) - 1;
			k += process(new long[] { 6 * r - 1, 6 * r + 5, 12 * r - 7 });
			if (k == T) {
				System.out.println(n);
				return;
			}
		}
	}
}
