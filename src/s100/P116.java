package s100;

import java.util.Arrays;

public class P116 {
	static long f(int n, int m, long[] F) {
		if (n < m) {
			return 1;
		}

		if (F[n] >= 0) {
			return F[n];
		}

		long ans = f(n - 1, m, F);
		ans += f(n - m, m, F);
		return F[n] = ans;
	}

	static long f(int m, int n) {
		long[] F = new long[n + 1];
		Arrays.fill(F, -1);
		return f(n, m, F) - 1;
	}

	public static void main(String[] args) {
		System.out.println(f(2, 50) + f(3, 50) + f(4, 50));
	}
}
