package s400;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import core.NTLib;

public class P425 {
	static class Edges {
		TreeSet<Integer> S;

		public Edges() {
			S = new TreeSet<Integer>();
		}

		public void add(int n) {
			S.add(n);
		}
	}

	static int U;
	static boolean[] P;
	static Edges[] E;
	static int[] M;

	static int numDigits(int n) {
		return n < 10 ? 1 : n < 100 ? 2 : n < 1000 ? 3 : n < 10000 ? 4 : n < 100000 ? 5 : n < 1000000 ? 6 : 7;
	}

	static int p(int e) {
		int x = 1;
		while (e-- > 0)
			x *= 10;
		return x;
	}

	static int getDigit(int n, int e) {
		return (n / p(e)) % 10;
	}

	static int setDigit(int n, int e, int d) {
		int p = p(e), head = n / p;
		return p * ((head - (head % 10)) + d) + (n % p);
	}

	static void getNeighbors(int n) {
		Set<Integer> S = new TreeSet<Integer>();
		int l = numDigits(n), m;

		for (int i = 0; i < l; i++)
			for (int d = 1; d <= 9; d++) {
				m = setDigit(n, i, d);
				if (m < n && P[m])
					S.add(m);
			}

		for (int i = 0; i < l - 1; i++)
			if (P[m = setDigit(n, i, 0)])
				S.add(m);

		if (getDigit(n, l - 2) != 0)
			if (P[m = setDigit(n, l - 1, 0)])
				S.add(m);

		S.remove(n);
		for (int i : S) {
			if (E[i] == null)
				E[i] = new Edges();
			if (E[n] == null)
				E[n] = new Edges();
			E[i].add(n);
			E[n].add(i);
		}
	}

	static void cascade(int n, int x) {
		M[n] = x;
		Set<Integer> S = E[n].S;
		for (int i : S)
			if (M[i] == 0)
				cascade(i, x);
	}

	static void compute(int n) {
		Set<Integer> S = E[n].S.subSet(0, n);

		boolean found = false;
		for (int i : S) {
			if (M[i] > 0) {
				M[n] = n;
				found = true;
				break;
			}
		}

		if (found) {
			for (int i : S)
				if (M[i] == 0)
					cascade(i, n);
		} else
			M[n] = 0;
	}

	public static void main(String[] args) {
		U = 10000000;

		P = NTLib.simpleSieve(U);
		E = new Edges[U];
		M = new int[U];

		Arrays.fill(M, -1);
		M[2] = 2;

		for (int i = 2; i < U; i++)
			if (P[i])
				getNeighbors(i);

		for (int i = 3; i < U; i++)
			if (P[i] && E[i] != null)
				compute(i);

		long ans = 0;
		for (int i = 2; i < U; i++)
			if (P[i])
				if (M[i] < 2 || M[i] > i)
					ans += i;

		System.out.println(ans);
	}
}
