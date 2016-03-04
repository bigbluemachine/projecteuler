package s200;

import java.util.ArrayList;
import java.util.HashMap;

import core.NTLib;
import core.Selector;

public class P273 {
	static class P {
		long a, b;

		P(long a, long b) {
			if (a > b) {
				this.a = b;
				this.b = a;
			} else {
				this.a = a;
				this.b = b;
			}
		}
	}

	static class W {
		ArrayList<P> S = new ArrayList<P>();

		void add(P p) {
			S.add(p);
		}
	}

	static P f(int n, int v) {
		for (int a = 1;; a++) {
			int a2 = a * a;
			int b2 = n - a2;
			int b = (int) Math.sqrt(b2);
			if (b * b == b2) {
				return new P(a, b);
			}
		}
	}

	static void process(W w, W a, W b) {
		for (P p : a.S) {
			for (P q : b.S) {
				long paqa = p.a * q.a;
				long paqb = p.a * q.b;
				long pbqa = p.b * q.a;
				long pbqb = p.b * q.b;
				w.add(new P(Math.abs(paqb - pbqa), paqa + pbqb));
				w.add(new P(pbqb - paqa, paqb + pbqa));
			}
		}
	}

	static long sum(HashMap<Integer, W> M) {
		long ans = 0;
		for (int x : M.keySet()) {
			for (P p : M.get(x).S) {
				ans += p.a;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		ArrayList<Integer> L = new ArrayList<Integer>();
		boolean[] P = NTLib.simpleSieve(150);
		for (int i = 1; 4 * i + 1 < 150; i++) {
			int n = 4 * i + 1;
			if (P[n]) {
				L.add(n);
			}
		}

		HashMap<Integer, W> M = new HashMap<Integer, W>();
		for (int i = 0; i < 16; i++) {
			int k = 1 << i;
			W w = new W();
			w.add(f(L.get(i), k));
			M.put(k, w);
		}

		HashMap<Integer, W> N = M;
		long ans = sum(M);
		for (int i = 2; i <= 16; i++) {
			System.gc();
			HashMap<Integer, W> O = new HashMap<Integer, W>();
			for (int x : M.keySet()) {
				for (Selector s = new Selector(16, i - 1); s.hasNext();) {
					int y = (int) s.next();
					if ((x & y) == 0) {
						W w = new W();
						process(w, M.get(x), N.get(y));
						O.put(x | y, w);
					}
				}
			}
			ans += sum(O);
			N = O;
		}
		System.out.println(ans);
	}
}
