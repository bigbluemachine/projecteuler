package s100;

import java.util.Arrays;

import core.MathLib;

public class P172 {
	static long f(int v, int n, long[] F) {
		if (F[v] >= 0) {
			return F[v];
		}
		if (n == 0) {
			return 1;
		}
		long ans = 0;
		for (int d = 0; d <= 9; d++) {
			int m = (v >> (2 * d)) & 3;
			if (m < 3) {
				ans += f(v + (1 << (2 * d)), n - 1, F);
			}
		}
		return F[v] = ans;
	}

	public static void main(String[] args) {
		int l = 18;
		long[] F = new long[4 << l];
		Arrays.fill(F, -1);
		long ans = 0;
		for (int d = 1; d <= 9; d++) {
			ans += f(MathLib.pow32(4, d), l - 1, F);
		}
		System.out.println(ans);
	}
}
