package s200;

import java.util.Arrays;

import core.NTLib;

public class P216TODO {
	public static void main(String[] args) {
		// TODO ~3-4 minutes
		int n = 50000000;
		int g = 50000;

		boolean[] v = new boolean[n + 1];
		Arrays.fill(v, true);
		boolean[] p = NTLib.simpleSieve(g);
		for (int m = 3; m < g; m += 2) {
			if (!p[m]) {
				continue;
			}
			for (int i = 2; i < m; i++) {
				long q = 2 * i;
				q = q * i - 1;
				if ((q + m) % m == 0) {
					for (int j = q != m ? i : i + m; j <= n; j += m) {
						v[j] = false;
					}
				}
			}
		}

		int ans = 0;
		for (int i = 2; i <= n; i++) {
			if (v[i]) {
				long x = i;
				x = 2 * x * i - 1;
				if (NTLib.MillerRabin(x, 5)) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
