package core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NTLib {
	// Prime sieve; simple (slow) method. n >= 2.
	// p[i] === i is prime
	public static boolean[] simpleSieve(int n) {
		boolean[] p = new boolean[n];
		Arrays.fill(p, true);
		p[0] = p[1] = false;

		for (int i = 2; i * i < n; i++) {
			if (!p[i]) {
				continue;
			}
			for (int j = i * i; j < n; j += i) {
				p[j] = false;
			}
		}

		return p;
	}

	// Prime sieve; simple (slow) method. n >= 2.
	// Packed bits.
	public static long[] bitSieve(int n) {
		int count = n / 63 + 1;
		long[] p = new long[count];

		p[0] = 0xaaaaaaaaaaaaaaacL;
		for (int i = 1; i < count; i++) {
			p[i] = 0xaaaaaaaaaaaaaaaaL >> (i & 1);
		}

		for (int i = 3; i * i < n; i += 2) {
			if ((p[i / 63] & (1L << (i % 63))) == 0) {
				continue;
			}
			for (int j = i * i; j < n; j += i) {
				p[j / 63] &= ~(1L << (j % 63));
			}
		}

		return p;
	}

	// Convenience for bit sieve.
	public static boolean isPrime(long[] p, int n) {
		return (p[n / 63] & (1L << (n % 63))) > 0;
	}

	// Prime list generated from sieve. n >= 3.
	public static ArrayList<Integer> primeList(int n) {
		boolean[] p = simpleSieve(n);
		ArrayList<Integer> a = new ArrayList<Integer>();

		a.add(2);
		for (int i = 3; i < n; i += 2) {
			if (p[i]) {
				a.add(i);
			}
		}

		return a;
	}

	// Prime sieve with offset. Supply enough primes to produce answer.
	// m >= 0, n >= 0.
	// p[i] === m + i is prime
	public static boolean[] offsetSieve(long m, int n, List<Integer> ps) {
		boolean[] p = new boolean[n];
		Arrays.fill(p, true);

		for (int a : ps) {
			if ((long) a * a >= m + n) {
				break;
			}

			int x = (int) (m % a);
			int i = x == 0 ? 0 : a - x;
			if (m + i == a) {
				i += a;
			}

			for (; i < n; i += a) {
				p[i] = false;
			}
		}

		return p;
	}

	// Divisor function; simple (slow) method. n >= 1.
	// s[i] = number of divisors of i or sum of divisors of i
	public static int[] divisor(int n, boolean sum) {
		int[] s = new int[n];

		if (sum) {
			for (int i = 1; i + i < n; i++) {
				for (int j = i + i; j < n; j += i) {
					s[j] += i;
				}
			}
		} else {
			for (int i = 1; i + i < n; i++) {
				for (int j = i + i; j < n; j += i) {
					s[j]++;
				}
			}
		}

		return s;
	}

	// Euler phi (totient) function. n >= 2.
	// *** Overflow warning! ***
	// t[i] = totient function of i
	public static int[] totient(int n) {
		int[] t = new int[n];
		boolean[] p = simpleSieve((int) Math.sqrt(n) + 1);

		t[1] = 1;
		for (int i = 2; i < n; i++) {
			t[i] = i - 1;
		}

		for (int i = 2; i * i < n; i++) {
			if (p[i]) {
				for (int j = i, k = i * i; k < n; j++, k += i) {
					int l = MathLib.gcd32(i, j);
					t[k] = (int) (((long) t[i] * t[j] * l) / t[l]);
				}
			}
		}

		return t;
	}

	// Miller-Rabin test. (Needs fixing!)
	// Makes k passes. n must be odd.
	public static boolean MillerRabin(long n, int k) {
		long d = n - 1;
		int s = 1;
		while (((1L << s) & d) == 0) {
			s++;
		}
		d >>= s;
		L: while (k-- > 0) {
			long a = (long) (Math.random() * (n - 3)) + 2;
			long x = BigInteger.valueOf(a).modPow(BigInteger.valueOf(d), BigInteger.valueOf(n)).longValue();
			if (x == 1 || x == n - 1) {
				continue;
			}
			for (int r = 1; r < s; r++) {
				x = BigInteger.valueOf(x).modPow(BigInteger.valueOf(2), BigInteger.valueOf(n)).longValue();
				if (x == 1) {
					return false;
				}
				if (x == n - 1) {
					continue L;
				}
			}
			return false;
		}
		return true;
	}

	public static LinkedList<Integer>[] batchFactor(int start, int count) {
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] a = new LinkedList[count];
		for (int i = 0; i < count; i++) {
			a[i] = new LinkedList<Integer>();
		}

		int max = start + count - 1;
		for (int d = 1; d <= max; d++) {
			int p = start - (start % d);
			if (p < start) {
				p += d;
			}

			for (; p <= max; p += d) {
				a[p - start].add(d);
			}
		}

		return a;
	}
}
