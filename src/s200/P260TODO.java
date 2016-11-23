package s200;

import java.util.Arrays;
import java.util.HashSet;

public class P260TODO {
	static int enc(int x, int y, int z) {
		return x | y << 10 | z << 20;
	}

	static int sEnc(int x, int y, int z) {
		int[] v = { x, y, z };
		Arrays.sort(v);
		return v[0] | v[1] << 10 | v[2] << 20;
	}

	static HashSet<Integer> next(int v, int s, int m) {
		int x = v & 1023;
		int y = (v >> 10) & 1023;
		int z = (v >> 20) & 1023;
		int d = s - (x + y + z);

		HashSet<Integer> S = new HashSet<Integer>();
		if (x + d <= m) {
			S.add(sEnc(x + d, y, z));
			if (y + d <= m) {
				S.add(sEnc(x, y + d, z));
				if (z + d <= m) {
					S.add(enc(x, y, z + d));
				}
			}
		}
		if (d % 2 == 0) {
			int e = d / 2;
			if (y + e <= m) {
				S.add(sEnc(x + e, y + e, z));
				if (z + e <= m) {
					S.add(sEnc(x + e, y, z + e));
					S.add(enc(x, y + e, z + e));
				}
			}
		}
		if (d % 3 == 0) {
			int e = d / 3;
			if (z + e <= m) {
				S.add(enc(x + e, y + e, z + e));
			}
		}
		return S;
	}

	public static void main(String[] args) {
		int n = 1000;

		HashSet<Integer> lose = new HashSet<Integer>();
		lose.add(0);

		for (int s = 1; s <= 3 * n; s++) {
			HashSet<Integer> win = new HashSet<Integer>();
			for (int v : lose) {
				win.addAll(next(v, s, n));
			}

			// (x, y, z) s.t. x <= y <= z <= n, x + y + z = s
			for (int z = Math.min(n, s); z >= 0; z--) {
				for (int y = Math.min(z, s - z); y >= 0; y--) {
					int x = s - (y + z);
					if (0 <= x && x <= y) {
						int v = enc(x, y, z);
						if (!win.contains(v)) {
							lose.add(v);
						}
					}
				}
			}
		}

		long ans = 0;
		for (int v : lose) {
			int x = v & 1023;
			int y = (v >> 10) & 1023;
			int z = (v >> 20) & 1023;
			ans += x + y + z;
		}

		// TODO ~2m
		System.out.println(ans);
	}
}
