package s300;

import java.util.HashMap;
import java.util.TreeSet;

import core.NTLib;

public class P378 {
	static long MOD = 1000000000000000000L;

	static long process(long[] O, long[] T, int x) {
		for (int i = x; i < O.length; i++) {
			O[i]++;
		}
		if (x == 0) {
			return 0;
		}
		if (O[x - 1] != 0) {
			for (int i = x; i < T.length; i++) {
				T[i] += O[x - 1];
			}
		}
		return T[x - 1];
	}

	public static void main(String[] args) {
		int n = 60000000;

		int[] d = NTLib.divisor(n + 2, false);
		int[] D = new int[n + 1];
		TreeSet<Integer> U = new TreeSet<Integer>();
		for (int i = 3; i <= n; i++) {
			int d1;
			int d2;
			if (i % 2 == 0) {
				d1 = d[i / 2];
				d2 = d[i + 1];
			} else {
				d1 = d[i];
				d2 = d[(i + 1) / 2];
			}
			D[i] = (d1 + 1) * (d2 + 1);
			U.add(D[i]);
		}

		Integer[] V = U.toArray(new Integer[0]);
		HashMap<Integer, Integer> M = new HashMap<Integer, Integer>();
		for (int i = 0; i < V.length; i++) {
			M.put(V[i], i);
		}
		for (int i = 3; i <= n; i++) {
			D[i] = M.get(D[i]);
		}

		long[] O = new long[V.length];
		long[] T = new long[V.length];
		long ans = 0;
		for (int i = n; i >= 3; i--) {
			ans = (ans + process(O, T, D[i])) % MOD;
		}
		System.out.println(ans);
	}
}
