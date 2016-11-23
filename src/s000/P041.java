package s000;

import core.NTLib;
import core.Perm;

public class P041 {
	static boolean[] P = NTLib.simpleSieve(7654322);

	public static void main(String[] args) {
		int ans = 0;
		for (int n = 2; n <= 7; n++) {
			Perm p = new Perm(n);
			while (p.hasNext()) {
				int[] v = p.next();
				int x = 0;
				for (int j = 0; j < n; j++) {
					x = 10 * x + (v[j] + 1);
				}
				if (P[x]) {
					ans = Math.max(ans, x);
				}
			}
		}
		System.out.println(ans);
	}
}
