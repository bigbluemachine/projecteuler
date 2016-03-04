package s200;

import java.util.Arrays;

public class P232 {
	static double f(int a, int b, double[][] T) {
		if (T[a][b] >= 0) {
			return T[a][b];
		}
		if (a == 100) {
			return T[a][b] = 0;
		}
		if (b == 100) {
			return T[a][b] = 1;
		}

		double R = 0;
		for (int e = 1; e <= 8; e++) {
			int d = 1 << e;
			double p = 1.0 / d;
			int bb = Math.min(100, b + d / 2);
			double test = (1.0 - p) * f(a + 1, b, T) + p * f(a + 1, bb, T);
			R = Math.max(test, R);
			if (b + d / 2 >= 100) {
				break;
			}
		}
		R *= 0.5;

		double ans = 0;
		for (int e = 1; e <= 8; e++) {
			int d = 1 << e;
			double p = 1.0 / d;
			int bb = Math.min(100, b + d / 2);
			double test = 0.5 * p * f(a, bb, T) + R;
			test /= (1.0 - 0.5 * (1.0 - p));
			ans = Math.max(ans, test);
			if (b + d / 2 >= 100) {
				break;
			}
		}

		return T[a][b] = ans;
	}

	public static void main(String[] args) {
		double[][] T = new double[101][101];
		for (double[] U : T) {
			Arrays.fill(U, -1);
		}

		for (int a = 100; a >= 0; a--) {
			for (int b = 100; b >= 0; b--) {
				f(a, b, T);
			}
		}

		System.out.printf("%.8f\n", T[0][0]);
	}
}
