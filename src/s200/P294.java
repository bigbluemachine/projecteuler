package s200;

import java.util.HashMap;

public class P294 {
	static long MOD = 1000000000L;
	static int[][][] F1 = new int[22][23][24];
	static HashMap<Long, Long> T = new HashMap<Long, Long>();

	static {
		for (int o = 0, x = 1; o < 22; o++) {
			for (int m = 0; m < 23; m++) {
				for (int s = 0; s < 24; s++) {
					F1[o][m][s] = s <= 9 && (s * x) % 23 == m ? 1 : 0;
				}
			}

			x = (x * 10) % 23;
		}
	}

	static long G(long d, int o, int m, int s) {
		long key = d << 15 | o << 10 | m << 5 | s;
		if (T.containsKey(key)) {
			return T.get(key);
		}

		if (d == 1) {
			return F1[o][m][s];
		}

		long l = d / 2;
		long r = d - l;
		int ol = (int) ((o + r) % 22);

		long ans = 0;
		for (int ml = 0; ml < 23; ml++) {
			for (int sl = 0; sl <= s; sl++) {
				long gl = G(l, ol, ml, sl);
				long gr = G(r, o, (23 + m - ml) % 23, s - sl);
				ans += (gl * gr) % MOD;
			}
		}

		ans %= MOD;
		T.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		long n = 3138428376721L;
		System.out.printf("F(%d) = %d\n", n, G(n, 0, 0, 23));
		System.out.printf("Map size = %d\n", T.size());
		System.out.printf("Time = %d ms\n", System.currentTimeMillis() - t);
	}
}
