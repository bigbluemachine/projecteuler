package s400;

import core.Matrix;

public class P435 {
	static long[] mvmul(Matrix m, long[] v, long mod) {
		int s = m.getSize();
		long[][] a = m.getData();
		long[] w = new long[s];

		for (int i = 0; i < s; i++) {
			long x = 0;
			for (int j = 0; j < s; j++) {
				x = Matrix.term(a[i][j], v[j], x, mod);
			}
			w[i] = x;
		}

		return w;
	}

	static long F(long n, int x, long m) {
		Matrix t = new Matrix(new long[][] { { 0, 1, 0 }, { x * x, x, x }, { 0, 0, 1 } });
		long[] v = new long[] { 0, x, 1 };
		t = t.modexp(n, m);
		v = mvmul(t, v, m);
		return v[0];
	}

	public static void main(String[] args) {
		long n = 1000000000000000L;
		long mod = 1307674368000L;

		long ans = 0;
		for (int x = 0; x <= 100; x++) {
			long f = F(n, x, mod);
			ans = (ans + f) % mod;
		}

		System.out.println(ans);
	}
}
