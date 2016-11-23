package s400;

import java.util.HashSet;
import java.util.List;

import core.NTLib;

public class P443 {
	static List<Integer> ps;

	public static HashSet<Long> factorize(long L) {
		HashSet<Long> S = new HashSet<Long>();
		while ((L & 1) == 0) {
			L >>= 1;
			S.add(2L);
		}
		for (int p : ps) {
			if (p > L)
				break;
			int e = 0;
			while (L % p == 0) {
				L /= p;
				e++;
			}
			if (e > 0) {
				S.add((long) p);
			}
		}
		if (L > 1) {
			S.add(L);
		}
		return S;
	}

	public static long gcd64(long a, long b) {
		while (a != 0) {
			a ^= b;
			b ^= a;
			a = (a ^ b) % b;
		}
		return b;
	}

	// Smallest number x >= n which is a multiple of d
	static long g(long n, long d) {
		return n % d == 0 ? n : n + d - (n % d);
	}

	public static void main(String[] args) {
		long m = 1000000000000000L;
		ps = NTLib.primeList((int) Math.sqrt(m) + 1);

		long n = 4, f = 13;
		long prevN = 0, prevF = 0;
		do {
			prevN = n;
			prevF = f;
			long d = f - (n + 1);
			long best = n + 1 + d;
			for (long s : factorize(d)) {
				best = Math.min(best, g(n + 1, s));
			}
			n = best;
			f = n + d + gcd64(n, d);
		} while (n < m);

		long ans = n == m ? f : prevF - prevN + m;
		System.out.printf("Answer = %d\n", ans);
	}
}
