package s300;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import core.NTLib;

public class P304 {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	static long M = 1234567891011L;
	static HashMap<Long, Long> F = new HashMap<Long, Long>();

	static long f(long n) {
		if (F.containsKey(n))
			return F.get(n);

		long n_ = (n + 1) >> 1;
		BigInteger fn = big(f(n_));
		BigInteger fn1 = big(f(n_ - 1));
		long result;
		if ((n & 1) > 0) {
			BigInteger fn2 = fn.pow(2);
			BigInteger fn12 = fn1.pow(2);
			result = fn2.add(fn12).mod(big(M)).longValue();
		} else {
			BigInteger a = fn1.add(fn1).add(fn);
			result = a.multiply(fn).mod(big(M)).longValue();
		}

		F.put(n, result);
		return result;
	}

	public static void main(String[] args) {
		int N = 10000000;
		long L = 100000000000000L;

		List<Integer> A = NTLib.primeList((int) Math.sqrt(L + N));
		boolean[] P = new boolean[N];
		Arrays.fill(P, true);
		P[0] = false;
		for (int a : A)
			for (int i = a - (int) (L % a); i < N; i += a)
				P[i] = false;

		F.put(0L, 0L);
		F.put(1L, 1L);
		long a = f(L - 1);
		long b = f(L);

		long sum = 0;
		int n = 1;
		for (int i = 0; n <= 100000; i++) {
			if (P[i]) {
				sum = (sum + b) % M;
				n++;
			}
			long temp = (a + b) % M;
			a = b;
			b = temp;
		}

		System.out.println(sum);
	}
}
