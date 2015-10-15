package s100;

import core.MathLib;

public class P101 {
	static long u(int n) {
		long ans = MathLib.pow64(n, 10) - MathLib.pow64(n, 9);
		ans += MathLib.pow64(n, 8) - MathLib.pow64(n, 7);
		ans += MathLib.pow64(n, 6) - MathLib.pow64(n, 5);
		ans += MathLib.pow64(n, 4) - MathLib.pow64(n, 3);
		ans += MathLib.pow64(n, 2) - n;
		return ans + 1;
	}

	static void cancel(long[][] m, int src, int dst, int col) {
		long y = m[dst][col];
		long x = m[src][col];
		long t = y / x;
		for (int i = 0; i < m[0].length; i++) {
			m[dst][i] -= t * m[col][i];
		}
	}

	static void normalize(long[][] m, int src, int col) {
		long x = m[src][col];
		for (int i = 0; i < m[0].length; i++) {
			m[src][i] /= x;
		}
	}

	static void solve(long[][] m) {
		// Triangulate
		for (int i = 0; i < m.length; i++) {
			for (int j = i + 1; j < m.length; j++) {
				cancel(m, i, j, i);
			}
		}

		// Normalize and cancel
		for (int i = m.length - 1; i >= 0; i--) {
			normalize(m, i, i);
			for (int j = i - 1; j >= 0; j--) {
				cancel(m, i, j, i);
			}
		}
	}

	static long apply(long[][] m, int n) {
		long ans = 0;
		for (int i = 0; i < m.length; i++) {
			ans += m[i][m[0].length - 1] * MathLib.pow64(n, i);
		}
		return ans;
	}

	static long[][] gen(int n) {
		long[][] m = new long[n][n + 1];
		for (int i = 1; i <= n; i++) {
			m[i - 1][n] = u(i);
			for (int j = 1; j <= n; j++) {
				m[j - 1][i - 1] = MathLib.pow64(j, i - 1);
			}
		}
		return m;
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int i = 1; i <= 10; i++) {
			long[][] m = gen(i);
			solve(m);
			ans += apply(m, i + 1);
		}
		System.out.println(ans);
	}
}
