package s100;

import java.util.Arrays;

public class P115 {
	static long f(int n, int m, long[] F) {
		if (n < m) {
			return 1;
		}

		if (F[n] >= 0) {
			return F[n];
		}

		long ans = f(n - 1, m, F);
		for (int i = m; i <= n; i++) {
			ans += f(n - i - 1, m, F);
		}

		return F[n] = ans;
	}

	static long f(int m, int n) {
		long[] F = new long[n + 1];
		Arrays.fill(F, -1);
		return f(n, m, F);
	}

	public static void main(String[] args) {
		for (int i = 51;; i++) {
			long t = f(50, i);
			if (t > 1000000) {
				System.out.println(i);
				break;
			}
		}
	}
}
