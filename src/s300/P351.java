package s300;

import java.math.BigInteger;

import core.MathLib;
import core.NTLib;

public class P351 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static int[] totient(int n) {
		int[] t = new int[n];
		boolean[] p = NTLib.simpleSieve((int) Math.sqrt(n) + 1);

		t[1] = 1;
		for (int i = 2; i < n; i++) {
			t[i] = i - 1;
		}

		for (int i = 2; i * i < n; i++) {
			if (p[i]) {
				for (int j = i, k = i * i; k < n; j++, k += i) {
					int l = MathLib.gcd32(i, j);
					// Overflow-B-Gone
					BigInteger temp = big((long) t[i] * t[j]);
					temp = temp.multiply(big(l));
					temp = temp.divide(big(t[l]));
					t[k] = temp.intValue();
				}
			}
		}

		return t;
	}

	public static void main(String[] args) {
		int n = 100000000;

		long total = 0;
		for (long i = n - 2; i >= 1; i -= 2) {
			total += i;
		}

		int[] t = totient((int) n + 1);
		long coprime = 0;
		for (int i = 3; i <= n; i++) {
			coprime += t[i] / 2;
		}

		long s = 12 * (total - coprime);
		s += 6 * (n / 2 - 1);
		s += 6 * (n - 1);
		System.out.println(s);
	}
}
