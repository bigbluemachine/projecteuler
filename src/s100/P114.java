package s100;

import java.util.Arrays;

public class P114 {
	static long f(int n, long[] F) {
		if (n < 3) {
			return 1;
		}

		if (F[n] >= 0) {
			return F[n];
		}

		long ans = f(n - 1, F);
		for (int i = 3; i <= n; i++) {
			ans += f(n - i - 1, F);
		}

		return F[n] = ans;
	}

	public static void main(String[] args) {
		long[] F = new long[51];
		Arrays.fill(F, -1);
		System.out.println(f(50, F));
	}
}
