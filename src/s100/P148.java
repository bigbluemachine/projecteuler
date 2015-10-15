package s100;

import core.MathLib;

public class P148 {
	static long f(long n) {
		if (n < 8)
			return (n * (n + 1)) / 2;
		long m = MathLib.pow64(7, (int) (Math.log(n - 1) / Math.log(7))), r = n % m;
		return r == 0 ? f(m) * f(n / m) : f(n - r) + (n + m - 1) / m * f(r);
	}

	public static void main(String[] args) {
		System.out.println(f(1000000000L));
	}
}
