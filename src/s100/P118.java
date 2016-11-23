package s100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import core.MillerRabin;
import core.NTLib;
import core.Perm;

public class P118 {
	static class A {
		int[] a;

		public A(int[] a) {
			this.a = a;
		}

		public boolean equals(Object other) {
			return Arrays.equals(a, ((A) other).a);
		}

		public int hashCode() {
			return Arrays.hashCode(a);
		}
	}

	static int[] S = new int[9];
	static int count = 0;
	static ArrayList<int[]> P = new ArrayList<int[]>();
	static long[] Q = NTLib.bitSieve(100000000);

	static void f(int left, int last) {
		if (left == 0) {
			int[] p = new int[count];
			System.arraycopy(S, 0, p, 0, count);
			P.add(p);
			return;
		}

		for (int i = Math.min(last, left); i >= 1; i--) {
			S[count++] = i;
			f(left - i, i);
			count--;
		}
	}

	static boolean isPrime(int z) {
		if (z < 100000000) {
			return NTLib.isPrime(Q, z);
		}
		return MillerRabin.MR32(z);
	}

	static A process(int[] w, int[] p) {
		int j = 0;
		int[] v = new int[p.length];
		for (int k = 0; k < p.length; k++) {
			int z = 0;
			for (int i = 0; i < p[k]; i++) {
				z = 10 * z + (w[j] + 1);
				j++;
			}
			if (!isPrime(z)) {
				return null;
			}
			v[k] = z;
		}
		Arrays.sort(v);
		return new A(v);
	}

	public static void main(String[] args) {
		for (int last = 8; last >= 1; last--) {
			S[0] = last;
			count = 1;
			f(9 - last, last);
		}
		HashSet<A> T = new HashSet<A>();
		Perm q = new Perm(9);
		while (q.hasNext()) {
			int[] v = q.next();
			for (int[] p : P) {
				A a = process(v, p);
				if (a != null) {
					T.add(a);
				}
			}
		}
		System.out.println(T.size());
	}
}
