package s500;

import java.math.BigInteger;

import core.MathLib;
import core.NTLib;

public class P512TODO {
	// Totient of odd numbers only, with BigInteger
	static int[] oddTotient(int n) {
		int[] t = new int[n / 2];
		boolean[] p = NTLib.simpleSieve((int) Math.sqrt(n) + 1);
		t[1 / 2] = 1;
		for (int i = 2; i < n; i++) {
			t[i / 2] = i - 1;
		}
		for (int i = 3; i * i < n; i += 2) {
			if (!p[i]) {
				continue;
			}
			for (int j = i, k = i * i; k < n; j += 2, k += 2 * i) {
				int l = MathLib.gcd32(i, j);
				long u = t[i / 2] * t[j / 2];
				BigInteger v = BigInteger.valueOf(u).multiply(BigInteger.valueOf(l));
				v = v.divide(BigInteger.valueOf(t[l / 2]));
				t[k / 2] = v.intValue();
			}
		}
		return t;
	}

	public static void main(String[] args) {
		// TODO SLOW!!
		// g(100) = 2007
		// g(1000) = 202661
		// g(10000) = 20263333
		// g(100000) = 2026413875
		// g(1000000) = 202642133233
		// g(10000000) = 20264235150261
		// g(100000000) = 2026423657126435

		int[] z = oddTotient(10000000);
		long ans = 1;
		for (int i = 3; i <= 10000000; i += 2) {
			ans += z[i / 2];
		}
		System.out.println(ans);

	}
}
