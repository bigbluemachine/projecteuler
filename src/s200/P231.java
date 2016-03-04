package s200;

import java.util.List;

import core.NTLib;

public class P231 {
	static long f(int n, int p) {
		long result = 0;
		for (long e = p; e <= n; e *= p)
			result += n / e;
		return result;
	}

	public static void main(String[] args) {
		List<Integer> primes = NTLib.primeList(20000000);
		long sum = 0;
		int n = 20000000, k = 15000000;
		for (Integer p : primes) {
			if (p > n)
				break;
			long x = f(n, p) - f(n - k, p) - f(k, p);
			sum += x * p;
		}
		System.out.println(sum);
	}
}