package s400;

import core.MathLib;

public class P455 {
	static long f(int n) {
		long e = 2;
		for (;;) {
			long t = MathLib.modExp(n, e, 1000000000);
			if (t == e) {
				return t;
			}
			if (t == 0) {
				return 0;
			}
			e = t;
		}
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int n = 2; n <= 1000000; n++) {
			ans += f(n);
		}
		System.out.println(ans);
	}
}
