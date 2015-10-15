package s100;

import core.MathLib;

public class P183 {
	static int f(int n) {
		// Derivative = e^(k * (ln(n) - ln(k))) * (ln(n) - ln(k) - 1)
		double k = n / Math.exp(1);
		int a = (int) Math.floor(k);
		int b = (int) Math.ceil(k);
		double la = a * (Math.log(n) - Math.log(a));
		double lb = b * (Math.log(n) - Math.log(b));
		int x = la > lb ? a : b;

		x /= MathLib.gcd32(n, x);
		while (x % 2 == 0) {
			x /= 2;
		}
		while (x % 5 == 0) {
			x /= 5;
		}
		return x == 1 ? -n : n;
	}

	public static void main(String[] args) {
		int ans = 0;
		for (int i = 5; i <= 10000; i++) {
			ans += f(i);
		}
		System.out.println(ans);
	}
}
