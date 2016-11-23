package s300;

import java.util.Arrays;

public class P307 {
	static int N, D;

	static double f(int o, int t, double[] F, double[] G) {
		if (F[o] >= 0) {
			return F[o];
		}
		if (o + 2 * t == D) {
			return 1;
		}
		int z = N - o - t;
		double ans = z * f(o + 1, t, F, G);
		if (o > 0) {
			ans += o * G[o - 1];
		}
		ans /= N;
		return F[o] = ans;
	}

	public static void main(String[] args) {
		N = 1000000;
		D = 20000;

		double[] G = new double[D + 1];
		Arrays.fill(G, 1);
		for (int t = D / 2; t >= 0; t--) {
			double[] F = new double[D + 1];
			Arrays.fill(F, -1);
			for (int o = 0; o + 2 * t <= D; o++) {
				F[o] = f(o, t, F, G);
			}
			G = F;
		}
		System.out.printf("%.10f\n", 1.0 - G[0]);
	}
}
