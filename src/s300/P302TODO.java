package s300;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import core.MathLib;
import core.NTLib;

public class P302TODO {
	static class MapWrapper {
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
	}

	static class Request {
		long n;
		int i;
		TreeMap<Integer, Integer> npf;
		int g;

		Request(long n, int i, TreeMap<Integer, Integer> npf, int g) {
			this.n = n;
			this.i = i;
			this.npf = npf;
			this.g = g;
		}
	}

	static TreeMap<Integer, Integer> makePF(long n) {
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		for (int i = 0; i < P.length; i++) {
			int p = P[i];
			if (n < p) {
				break;
			}
			if (n % p == 0) {
				int c = 0;
				do {
					c++;
				} while ((n /= p) % p == 0);
				m.put(i, c);
			}
		}
		return m;
	}

	static TreeMap<Integer, Integer> mul(TreeMap<Integer, Integer> pf, int i, int e) {
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		m.putAll(pf);
		if (pf.containsKey(i)) {
			m.put(i, pf.get(i) + e);
		} else {
			m.put(i, e);
		}
		return m;
	}

	static TreeMap<Integer, Integer> totient(TreeMap<Integer, Integer> pf) {
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();
		for (int i : pf.keySet()) {
			int e = pf.get(i);
			if (e > 1) {
				m.put(i, e - 1);
			} else {
				m.remove(i);
			}
			TreeMap<Integer, Integer> t = T[i].m;
			for (int k : t.keySet()) {
				if (m.containsKey(k)) {
					m.put(k, m.get(k) + t.get(k));
				} else {
					m.put(k, t.get(k));
				}
			}
		}
		return m;
	}

	static boolean achilles(TreeMap<Integer, Integer> pf) {
		if (pf.isEmpty()) {
			return false;
		}
		int g = 0;
		for (int e : pf.values()) {
			if (e < 2) {
				return false;
			}
			g = GCD[g][e];
		}
		return g == 1;
	}

	static void initPrimes(int n) {
		List<Integer> p = NTLib.primeList(n);
		P = p.toArray(new Integer[p.size()]);
		T = new MapWrapper[P.length];
		for (int i = 0; i < P.length; i++) {
			T[i] = new MapWrapper();
			T[i].m = makePF(P[i] - 1);
		}
	}

	static boolean overflow(long... ns) {
		long x = 1, prev;
		for (long i : ns) {
			prev = x;
			x *= i;
			if (x / i != prev) {
				return true;
			}
		}
		return false;
	}

	static void traverse(Request r) {
		long n = r.n;
		int i = r.i;
		TreeMap<Integer, Integer> npf = r.npf;
		int g = r.g;

		if (i >= 0) {
			R.add(new Request(n, i - 1, npf, g));
			if (overflow(n, P[i], P[i])) {
				return;
			}
			int k = 2;
			for (long m = n * P[i] * P[i]; m < X; m *= P[i], k++) {
				R.add(new Request(m, i - 1, mul(npf, i, k), GCD[g][k]));
				if (overflow(m, P[i])) {
					break;
				}
			}
		} else if (g == 1) {
			if (achilles(totient(npf))) {
				A++;
			}
		}
	}

	static int[][] GCD = new int[100][100];
	static Integer[] P;
	static MapWrapper[] T;
	static long X = MathLib.pow64(10, 18);
	static long A = 0;
	static LinkedList<Request> R = new LinkedList<Request>();

	public static void main(String[] args) {
		// TODO > 10 minutes!
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				GCD[i][j] = MathLib.gcd32(i, j);
			}
		}

		initPrimes((int) Math.cbrt(X));

		for (int i = P.length - 1; i > 0; i--) {
			int k = 3;
			for (long n = MathLib.pow64(P[i], 3); n < X; n *= P[i], k++) {
				R.add(new Request(n, i - 1, mul(new TreeMap<Integer, Integer>(), i, k), k));
				if (overflow(n, P[i])) {
					break;
				}
			}
		}

		while (!R.isEmpty()) {
			traverse(R.removeLast());
		}

		System.out.println(A);
	}
}
