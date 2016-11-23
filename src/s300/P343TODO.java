package s300;

import java.math.BigInteger;
import java.util.List;

import core.NTLib;

public class P343TODO {
	static List<Integer> P = NTLib.primeList(2000000);

	static long lpf(long n) {
		long ans = 1;
		for (int p : P) {
			if ((long) p * p > n) {
				break;
			}
			while (n % p == 0) {
				ans = p;
				n /= p;
			}
		}
		return Math.max(ans, n);
	}

	// (largest prime factor of k^3 + 1) - 1
	static long g(long k) {
		return Math.max(lpf(k + 1), lpf(k * k - k + 1)) - 1;
	}

	public static void main(String[] args) {
		// TODO SLOW!
		BigInteger ans = BigInteger.ONE;
		for (long k = 2000000; k >= 2; k--) {
			ans = ans.add(BigInteger.valueOf(g(k)));
		}
		System.out.println(ans);
	}
}
