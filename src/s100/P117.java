package s100;

import java.util.Arrays;

public class P117 {
	static long f(int n, long[] F) {
		if (n < 0) {
			return 1;
		}

		if (F[n] >= 0) {
			return F[n];
		}

		long ans = f(n - 1, F);
		for (int i = 2; i <= Math.min(n, 4); i++) {
			ans += f(n - i, F);
		}
		return F[n] = ans;
	}

	static long f(int n) {
		long[] F = new long[n + 1];
		Arrays.fill(F, -1);
		return f(n, F);
	}

	public static void main(String[] args) {
		System.out.println(f(50));
	}
}
