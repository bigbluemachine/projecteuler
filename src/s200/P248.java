package s200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import core.NTLib;

public class P248 {
	static class L {
		LinkedList<Long> l = new LinkedList<Long>();

		L(long... is) {
			for (long i : is) {
				l.add(i);
			}
		}

		void mul(long m) {
			for (int i = 0; i < l.size(); i++) {
				l.set(i, l.get(i) * m);
			}
		}

		void join(L other) {
			l.addAll(other.l);
		}

		void mulOdd() {
			LinkedList<Long> a = new LinkedList<Long>();
			for (long i : l) {
				if (i % 2 > 0) {
					a.add(i * 2);
				}
			}
			l.addAll(a);
		}
	}

	static class PF {
		TreeMap<Integer, Integer> m = new TreeMap<Integer, Integer>();

		PF(TreeMap<Integer, Integer> M) {
			m = M;
		}

		PF(int... a) {
			for (int i = 0; i < a.length; i += 2) {
				m.put(a[i], a[i + 1]);
			}
		}

		PF mul(PF o) {
			TreeMap<Integer, Integer> n = new TreeMap<Integer, Integer>();
			n.putAll(m);

			for (int b : o.m.keySet()) {
				if (n.containsKey(b)) {
					n.put(b, n.get(b) + o.m.get(b));
				} else {
					n.put(b, o.m.get(b));
				}
			}

			return new PF(n);
		}

		PF divTest(PF o) {
			TreeMap<Integer, Integer> n = new TreeMap<Integer, Integer>();
			n.putAll(m);

			for (int b : o.m.keySet()) {
				if (n.containsKey(b) && n.get(b) >= o.m.get(b)) {
					int c = n.get(b) - o.m.get(b);
					if (c > 0) {
						n.put(b, c);
					} else {
						n.remove(b);
					}
				} else {
					return null;
				}
			}

			return new PF(n);
		}

		int largest() {
			return m.lastKey();
		}

		LinkedList<Long> candidates() {
			ArrayList<Integer> d = new ArrayList<Integer>();
			LinkedList<Integer> s = new LinkedList<Integer>();
			for (int k : m.keySet()) {
				d.add(k);
				s.add(m.get(k));
			}

			int L = d.size();
			TreeSet<Long> ans = new TreeSet<Long>(R);
			Z: do {
				if (s.getFirst() < 0) {
					int index = 0;
					do {
						index++;
						if (index == L) {
							break Z;
						}
					} while (s.get(index) == 0);
					s.set(index, s.get(index) - 1);
					for (int i = 0; i < index; i++) {
						s.set(i, m.get(d.get(i)));
					}
				}

				long x = 1;
				for (int i = 0; i < L; i++) {
					int p = d.get(i), t = 1;
					for (int j = 0; j < s.get(i); j++) {
						t *= p;
					}
					x *= t;
				}

				if (NTLib.MillerRabin(x + 1, 10)) {
					ans.add(x + 1);
				}

				s.set(0, s.getFirst() - 1);
			} while (true);

			ans.add(2L);

			LinkedList<Long> ret = new LinkedList<Long>();
			ret.addAll(ans);
			return ret;
		}
	}

	static Comparator<Long> R = new Comparator<Long>() {
		@Override
		public int compare(Long arg0, Long arg1) {
			return arg1.compareTo(arg0);
		}
	};
	static PF pf;
	static LinkedList<Long> cand;

	static L f(long T, int I) {
		if (T == 1) {
			return new L(1);
		}
		if (I == cand.size()) {
			return new L();
		}

		// if P < largest prime factor in T, return new L()
		long P = cand.get(I);
		L ans = new L();

		for (long e = P, t = P - 1;; e *= P, t *= P) {
			if (T % t > 0) {
				break;
			}
			L l = f(T / t, I + 1);
			l.mul(e);
			ans.join(l);
		}

		ans.join(f(T, I + 1));
		return ans;
	}

	static L f(long n) {
		L ans = f(n, 0);
		ans.mulOdd();
		return ans;
	}

	public static void main(String[] args) {
		pf = new PF(2, 10, 3, 5, 5, 2, 7, 1, 11, 1, 13, 1);
		cand = pf.candidates();
		LinkedList<Long> X = f(6227020800L).l;
		Collections.sort(X);
		System.out.println(X.get(149999));
		System.out.println(X.size());
	}
}
