package s200;

import java.util.HashMap;

import core.ArrayLib;

public class P297 {
	static long[] F;
	static HashMap<Long, Long> M = new HashMap<Long, Long>();

	static int[] z(long n) {
		int[] ans = new int[0];
		while (n > 0) {
			for (int i = F.length - 1; i >= 0; i--) {
				if (F[i] <= n) {
					n -= F[i];
					ans = ArrayLib.concat(new int[] { i }, ans);
					break;
				}
			}
		}
		return ans;
	}

	static long s(int[] v) {
		long key = 0;
		for (int i : v) {
			key += F[i];
		}

		if (M.containsKey(key)) {
			return M.get(key);
		}

		long ans;
		if (v.length == 1) {
			if (v[0] == 0) {
				ans = 0;
			} else if (v[0] == 1) {
				ans = 1;
			} else {
				int[] w = new int[0];
				int k = 0;
				for (int i = v[0] - 1; i >= 0; i -= 2) {
					w = ArrayLib.concat(new int[] { i }, w);
					k++;
				}
				ans = k + s(w);
			}
		} else {
			if (v[0] == 0) {
				ans = v.length - 1 + s(ArrayLib.slice(v, 1, v.length));
			} else {
				ans = 0;
				for (int i = 0; i < v.length; i++) {
					ans += s(new int[] { v[i] }) + F[v[i]] * (v.length - i - 1);
				}
			}
		}

		M.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		F = new long[100];
		F[0] = 1;
		F[1] = 2;
		for (int i = 2;; i++) {
			F[i] = F[i - 1] + F[i - 2];
			if (F[i] >= 100000000000000000L) {
				long[] G = new long[i];
				System.arraycopy(F, 0, G, 0, i);
				F = G;
				break;
			}
		}

		System.out.println(s(z(100000000000000000L)));
	}
}
