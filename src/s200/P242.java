package s200;

import java.util.HashMap;

public class P242 {
	static HashMap<Long, Long> M = new HashMap<Long, Long>();

	static long floorP2(long n) {
		int ans = 0;
		while (n > 0) {
			n >>= 1;
			ans++;
		}
		return 1L << (ans - 1);
	}

	static long f(long n) {
		if (M.containsKey(n)) {
			return M.get(n);
		}
		long z = floorP2(n);
		long ans = f(z - 1) + (f(n - z) << 1);
		M.put(n, ans);
		return ans;
	}

	public static void main(String[] args) {
		M.put(0L, 1L);

		// f(n, k) > 0 iff n = 4x + 1.
		// Number of odd f(n, k): 1, 2, 2, 4, 2, 4, 4, 8, ...
		long n = 1000000000000L;
		System.out.println(f((n + 3) / 4 - 1));
	}
}
