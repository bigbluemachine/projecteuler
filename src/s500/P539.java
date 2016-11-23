package s500;

import java.util.HashMap;

public class P539 {
	static long MOD = 987654321;
	static HashMap<Long, Long> M = new HashMap<Long, Long>();

	static long f(long n, boolean left) {
		if (n == 1) {
			return 1;
		}
		if (left) {
			return 2 * f(n / 2, false);
		} else {
			return 2 * f(n / 2, true) - ((n + 1) % 2);
		}
	}

	static long sf(long n, boolean left) {
		boolean cache = n < 1000000;
		long key = 2 * n + (left ? 1 : 0);
		if (cache && M.containsKey(key)) {
			return M.get(key);
		}

		long ans = 0;
		if (n == 1) {
			ans = 1;
		} else if (n == 2) {
			ans = 3;
		} else if (left) {
			if (n % 2 == 0) {
				ans = f(n, true) % MOD;
			}
			ans += (1 + 4 * sf((n - 1) / 2, false)) % MOD;
		} else {
			ans = 1 + 2 * sf((n - 1) / 2, true);
			ans = (ans + 2 * sf(n / 2, true) - n / 2) % MOD;
		}

		if (cache) {
			M.put(key, ans);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(sf(1000000000000000000L, true));
	}
}
