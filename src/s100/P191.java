package s100;

import java.util.Arrays;

public class P191 {
	static long f(int n, int a, int l, long[] F) {
		int k = l;
		k = 3 * k + a;
		k = 31 * k + n;
		if (F[k] >= 0) {
			return F[k];
		}

		if (n == 0) {
			return 1;
		}

		long ans = 0;
		if (l == 0) {
			ans += f(n - 1, 0, l + 1, F);
		}
		if (l < 2) {
			ans += f(n - 1, 0, l, F);
		}
		if (a < 2) {
			ans += f(n - 1, a + 1, l, F);
		}

		return F[k] = ans;
	}

	public static void main(String[] args) {
		long[] F = new long[31 * 3 * 2];
		Arrays.fill(F, -1);
		System.out.println(f(30, 0, 0, F));
	}
}
