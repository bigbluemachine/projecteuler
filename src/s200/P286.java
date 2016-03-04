package s200;

import java.util.Arrays;

public class P286 {
	static double f(int n, int x, double q, double[][] T) {
		if (T[n][x] >= 0) {
			return T[n][x];
		}
		if (n + x > 51) {
			return 0;
		}
		double p = 1.0 - (double) x / q;
		double ans = 0;
		if (x == 50) {
			ans = n == 1 ? p : 1.0 - p;
		} else {
			if (n > 0) {
				ans += p * f(n - 1, x + 1, q, T);
			}
			ans += (1.0 - p) * f(n, x + 1, q, T);
		}
		return T[n][x] = ans;
	}

	public static void main(String[] args) {
		double lo = 50.0;
		double hi = 100.0;

		for (;;) {
			double[][] T = new double[21][51];
			for (double[] U : T) {
				Arrays.fill(U, -1);
			}

			double mid = 0.5 * (lo + hi);
			double t = f(20, 1, mid, T);
			double e = Math.abs(0.02 - t);
			if (e < 1.0e-16) {
				System.out.println(mid);
				break;
			}

			if (t < 0.02) {
				hi = mid;
			} else {
				lo = mid;
			}
		}
	}
}
