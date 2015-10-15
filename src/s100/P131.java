package s100;

import core.NTLib;

public class P131 {
	public static void main(String[] args) {
		int M = 1000000;

		int ans = 0;
		boolean[] P = NTLib.simpleSieve(M);
		for (int m = 1;; m++) {
			int t = 3 * m * (m - 1) + 1;
			if (t >= M) {
				break;
			}
			if (P[t]) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
