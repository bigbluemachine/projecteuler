package s500;

import core.NTLib;

public class P537TODO {
	static final long MOD = 1004535809L;

	static int[] gap(int n, int m) {
		int[] v = new int[m + 1];
		int i = 0, p = 1;
		for (int x : NTLib.primeList(n)) {
			v[i] = (x - p);
			if (++i > m) {
				break;
			}
			p = x;
		}
		return v;
	}

	public static void main(String[] args) {
		// TODO REDUCE COMPLEXITY CLASS!!
		// 3 : 19
		// 10 : 869985
		// 100 : 92713097
		// 1000 : 578270566

		int a = 20000;

		int[] G = gap(1000000, a);
		long[] P = new long[a + 1];
		P[0] = 1;
		for (int i = 1; i <= a; i++) {
			long[] Q = new long[a + 1];
			System.out.println(i);
			Q[0] = 1;
			for (int j = 1; j <= a; j++) {
				long ans = 0;
				for (int u = 0; u <= j; u++) {
					ans = (ans + G[u] * P[j - u]) % MOD;
				}
				Q[j] = ans;
			}
			P = Q;
			Q = null;
		}
		System.out.println(P[a]);
	}
}
