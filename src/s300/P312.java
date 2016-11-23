package s300;

import core.MathLib;

public class P312 {
	static long f(long n, long m) {
		long t = MathLib.modExp(12, 3, m), ans = t;
		for (int i = 2; i <= n; i++)
			ans = (ans * (t = MathLib.modExp(t, 3, m))) % m;
		return (8 * ans) % m;
	}

	static long g(long m) {
		long t = MathLib.modExp(12, 3, m), b = t, o = t, ans = 0;
		do
			ans++;
		while ((b = (b * (t = MathLib.modExp(t, 3, m))) % m) != o);
		return ans;
	}

	public static void main(String[] args) {
		long M0 = MathLib.pow64(13, 8);
		long M1 = g(M0);
		long M2 = g(M1);

		long A = f(10000 - 3, M2) - 3;
		long B = f(A, M1) - 3;
		long C = f(B, M0);

		System.out.println(C);
	}
}
