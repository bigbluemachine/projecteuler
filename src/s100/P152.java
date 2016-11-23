package s100;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import core.BigRatio;
import core.NTLib;

public class P152 {
	static List<Integer> P = NTLib.primeList(80);
	static BigRatio HALF = new BigRatio(BigInteger.ONE, BigInteger.valueOf(2));
	static LinkedList<Q> S = new LinkedList<Q>();
	static int ANS = 0;

	static class Q {
		BigRatio r;
		LinkedList<Integer> L;
		int lpd;

		public Q() {
			r = BigRatio.ZERO;
			L = new LinkedList<Integer>();
			lpd = -1;
		}

		public Q copy() {
			Q q = new Q();
			q.r = r;
			q.L = new LinkedList<Integer>();
			q.L.addAll(L);
			return q;
		}

		public void add(int n) {
			r = r.add(new BigRatio(BigInteger.ONE, BigInteger.valueOf(n * n)));
			L.add(n);
		}

		public int getLPD() {
			return lpd == -1 ? (lpd = lpd(r.d)) : lpd;
		}
	}

	public static int lpd(BigInteger n) {
		for (int i = P.size() - 1; i >= 0; i--) {
			if (n.mod(BigInteger.valueOf(P.get(i))).compareTo(BigInteger.ZERO) == 0) {
				return P.get(i);
			}
		}
		throw new Error("!");
	}

	static void add(LinkedList<Q> qs, int i) {
		int n = qs.size();
		while (n-- > 0) {
			Q q = qs.removeFirst();
			Q r = q.copy();
			r.add(i);
			qs.add(q);
			if (r.getLPD() <= i) {
				qs.add(r);
			}
		}
	}

	static void search(BigRatio r, TreeMap<Integer, LinkedList<Q>> M, TreeSet<Integer> D) {
		if (D.isEmpty()) {
			return;
		}

		int i = D.last();
		TreeSet<Integer> E = new TreeSet<Integer>();
		E.addAll(D);
		E.remove(i);
		search(r, M, E);

		for (Q q : M.get(i)) {
			BigRatio s = r.add(q.r);
			if (s.compareTo(HALF) == 0) {
				TreeSet<Integer> A = new TreeSet<Integer>();
				for (Q v : S) {
					A.addAll(v.L);
				}
				ANS++;
				System.out.println(A);
				return;
			} else if (s.compareTo(HALF) < 0 && lpd(s.d) < i) {
				S.addLast(q);
				search(s, M, E);
				S.removeLast();
			}
		}
	}

	public static void main(String[] args) {
		TreeMap<Integer, LinkedList<Q>> M = new TreeMap<Integer, LinkedList<Q>>();
		for (int p : P) {
			M.put(p, new LinkedList<Q>());
			M.get(p).add(new Q());
		}
		for (int i = 2; i <= 80; i++) {
			add(M.get(lpd(BigInteger.valueOf(i))), i);
		}

		// Filter out large primes
		TreeSet<Integer> D = new TreeSet<Integer>();
		for (int i : M.keySet()) {
			if (M.get(i).size() == 2) {
				D.add(i);
			}
			M.get(i).removeFirst();
		}
		for (int i : D) {
			M.remove(i);
		}

		// Find candidate primes
		D.clear();
		D.add(2);
		for (int i : M.keySet()) {
			for (Q q : M.get(i)) {
				if (q.getLPD() < i) {
					D.add(i);
				}
			}
		}

		search(BigRatio.ZERO, M, D);
		System.out.println(ANS);
	}
}
