package s300;

import java.util.Arrays;

import core.MathLib;

public class P323 {
	static long c(int n, int k) {
		return MathLib.fac(n).divide(MathLib.fac(k)).divide(MathLib.fac(n - k)).longValue();
	}

	static long w(int n, int z, int y) {
		return c(z, z - y) * (1L << (n - z));
	}

	static double f(int n, int z, double[][] T) {
		if (T[n][z] >= 0) {
			return T[n][z];
		}
		if (z == 0) {
			return T[n][z] = 0;
		}
		double s = 0;
		double d = Math.pow(2.0, n);
		for (int y = 0; y < z; y++) {
			s += w(n, z, y) * f(n, y, T);
		}
		return T[n][z] = (1.0 + s / d) / (1.0 - w(n, z, z) / d);
	}

	public static void main(String[] args) {
		int n = 32;
		double[][] T = new double[n + 1][n + 1];
		for (double[] TT : T) {
			Arrays.fill(TT, -1);
		}
		System.out.printf("%.10f\n", f(n, n, T));
	}
}
