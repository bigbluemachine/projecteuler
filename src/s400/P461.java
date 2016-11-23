package s400;

public class P461 {
	static int M;
	static double[] F;
	public static int[] SRC;
	public static int[] DST;
	public static int START;
	public static int LEN;

	static double g(int p) {
		return F[p & 0xffff] + F[p >> 16 & 0xffff];
	}

	public static void msort() {
		int i, j, m, x, y, z, t;
		DST = new int[LEN];
		for (m = 1; m < LEN; m += m, System.arraycopy(DST, 0, SRC, 0, LEN)) {
			for (x = START, y = START + m; y < LEN; x += m + m, y += m + m) {
				z = Math.min(y + m, LEN);
				t = x;
				if (SRC[y - 1] > SRC[y]) {
					for (i = y, j = x; x < y && i < z;) {
						DST[j++] = g(SRC[i]) < g(SRC[x]) ? SRC[i++] : SRC[x++];
					}
					z = x == y ? z - (x = i) : y - x;
				} else {
					z = z - (j = x);
				}
				System.arraycopy(SRC, x, DST, j, z);
				x = t;
			}
		}
	}

	public static void main(String[] args) {
		int n = 10000;

		M = (int) (n * Math.log(Math.PI + 1.0));
		F = new double[M + 1];
		for (int k = 1; k <= M; k++) {
			double p = (double) k / n;
			F[k] = Math.exp(p) - 1.0;
		}

		LEN = M * (M + 1) / 2;
		SRC = new int[LEN];
		for (int i = 1, k = 0; i <= M; i++) {
			for (int j = i; j <= M; j++) {
				SRC[k++] = j << 16 | i;
			}
		}
		msort();

		double best = 1.0;
		int best1 = 0;
		int best2 = 0;

		for (int l = 0, r = DST.length - 1; l < r;) {
			double test = g(DST[l]) + g(DST[r]);
			double e = Math.abs(test - Math.PI);
			if (e < best) {
				best = e;
				best1 = DST[l];
				best2 = DST[r];
			}

			if (test < Math.PI) {
				l++;
			} else {
				r--;
			}
		}

		int x1 = best1 & 0xffff;
		int y1 = best1 >> 16 & 0xffff;
		int x2 = best2 & 0xffff;
		int y2 = best2 >> 16 & 0xffff;
		int ans = x1 * x1 + y1 * y1 + x2 * x2 + y2 * y2;

		System.out.printf("%d %d %d %d\n", x1, y1, x2, y2);
		System.out.println(ans);
	}
}
