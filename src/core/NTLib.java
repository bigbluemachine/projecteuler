package core;

import java.util.Arrays;
import java.util.LinkedList;

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

	// Prime list generated from sieve. n >= 2.
	public static LinkedList<Integer> simpleList(int n) {
		boolean[] p = simpleSieve(n);
		LinkedList<Integer> a = new LinkedList<Integer>();

		for (int i = 2; i < n; i++) {
			if (p[i]) {
				a.add(i);
			}
		}

		return a;
	}

	// Prime sieve with offset. Supply enough primes to produce answer.
	// m >= 0, n >= 0.
	// p[i] === m + i is prime
	public static boolean[] offsetSieve(long m, int n, LinkedList<Integer> ps) {
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
}
