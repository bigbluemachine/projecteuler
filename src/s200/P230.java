package s200;

import core.MathLib;

public class P230 {
	static String A = "1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
	static String B = "8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196";

	static int digit(int i, long n) {
		if (i == 1) {
			return A.charAt((int) (n - 1)) - '0';
		}
		if (i == 2) {
			return B.charAt((int) (n - 1)) - '0';
		}
		long a = len(i - 2);
		if (n > a) {
			return digit(i - 1, n - a);
		}
		return digit(i - 2, n);
	}

	static long len(int i, long a, long b) {
		if (i == 1) {
			return a;
		}
		if (i == 2) {
			return b;
		}
		return len(i - 1, b, a + b);
	}

	static long len(int i) {
		return len(i, A.length(), B.length());
	}

	static int d(long n) {
		for (int i = 1;; i++) {
			if (n <= len(i)) {
				return digit(i, n);
			}
		}
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int n = 0; n <= 17; n++) {
			long z = MathLib.pow64(7, n);
			z *= (127 + 19 * n);
			long e = MathLib.pow64(10, n);
			ans += e * d(z);
		}
		System.out.println(ans);
	}
}
