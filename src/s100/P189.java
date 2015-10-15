package s100;

import java.util.HashMap;

public class P189 {
	static HashMap<Integer, Long> M = new HashMap<Integer, Long>();

	static void g(int[] v, int[] w, int i, long[] ans) {
		if (i == v.length) {
			ans[0] += f(v.clone());
			return;
		}
		for (int x = 1; x <= 3; x++) {
			if (i % 2 == 1 && x == w[i - 1]) {
				continue;
			}
			if (i > 0 && x == v[i - 1]) {
				continue;
			}
			v[i] = x;
			g(v, w, i + 1, ans);
		}
	}

	static long f(int[] v) {
		if (v.length == 15) {
			return 1;
		}

		int k = 0;
		for (int e : v) {
			k = 4 * k + e;
		}
		if (M.containsKey(k)) {
			return M.get(k);
		}

		long[] ans = new long[1];
		g(new int[v.length + 2], v, 0, ans);
		M.put(k, ans[0]);
		return ans[0];
	}

	public static void main(String[] args) {
		long t = f(new int[] { 1 });
		System.out.println(3 * t);
	}
}
