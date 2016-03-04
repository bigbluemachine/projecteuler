package s100;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class P194 {
	static P A = new G(0, 1, 0, 2, 0, 5, 1, 2, 1, 6, 2, 3, 3, 4, 4, 5, 4, 6, 5, 6).chr();
	static P B = new G(0, 1, 0, 2, 0, 5, 1, 2, 1, 6, 2, 3, 3, 4, 4, 5, 4, 6).chr();
	static final BigInteger MOD = big(100000000);

	static class G {
		HashMap<Integer, HashSet<Integer>> E = new HashMap<Integer, HashSet<Integer>>();

		G(int... e) {
			for (int i = 0; i < e.length; i += 2) {
				int u = e[i];
				int v = e[i + 1];
				if (!E.containsKey(u)) {
					E.put(u, new HashSet<Integer>());
				}
				E.get(u).add(v);
				if (!E.containsKey(v)) {
					E.put(v, new HashSet<Integer>());
				}
				E.get(v).add(u);
			}
		}

		G(HashMap<Integer, HashSet<Integer>> e) {
			for (int k : e.keySet()) {
				E.put(k, new HashSet<Integer>());
				E.get(k).addAll(e.get(k));
			}
		}

		G remove(int u, int v) {
			G g = new G(E);
			HashMap<Integer, HashSet<Integer>> e = g.E;
			e.get(u).remove(v);
			e.get(v).remove(u);
			return g;
		}

		G contract(int u, int v) {
			G g = remove(u, v);
			HashMap<Integer, HashSet<Integer>> e = g.E;
			if (e.containsKey(v)) {
				e.get(u).addAll(e.get(v));
				e.remove(v);
			}
			for (int k : e.keySet()) {
				if (e.get(k).contains(v)) {
					e.get(k).add(u);
					e.get(k).remove(v);
				}
			}
			return g;
		}

		P chr() {
			for (int k : E.keySet()) {
				if (!E.get(k).isEmpty()) {
					int v = E.get(k).toArray(new Integer[0])[0];
					G a = remove(k, v);
					G b = contract(k, v);
					return a.chr().sub(b.chr());
				}
			}
			P p = new P();
			p.add(E.size(), 1);
			return p;
		}
	}

	static class P {
		HashMap<Integer, Integer> c = new HashMap<Integer, Integer>();

		P() {
		}

		void add(int p, int k) {
			if (c.containsKey(p)) {
				c.put(p, c.get(p) + k);
			} else {
				c.put(p, k);
			}
		}

		P sub(P o) {
			P p = new P();
			p.c.putAll(c);
			for (int k : o.c.keySet()) {
				p.add(k, -o.c.get(k));
			}
			return p;
		}

		BigInteger eval(long x) {
			BigInteger ans = BigInteger.ZERO;
			for (int k : c.keySet()) {
				ans = ans.add(big(x).pow(k).multiply(big(c.get(k))));
			}
			return ans;
		}
	}

	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static long f(int a, int b, long k, long[][] T) {
		if (a == 0 && b == 0) {
			return T[a][b] = 1;
		}
		if (T[a][b] >= 0) {
			return T[a][b];
		}
		long ans = 0;
		BigInteger f = big(k * (k - 1));
		if (a > 0) {
			ans += (A.eval(k).divide(f).mod(MOD).longValue()) * f(a - 1, b, k, T);
		}
		if (b > 0) {
			ans += (B.eval(k).divide(f).mod(MOD).longValue()) * f(a, b - 1, k, T);
		}
		return T[a][b] = ans % MOD.longValue();
	}

	static long f(int a, int b, int k) {
		long[][] T = new long[a + 1][b + 1];
		for (long[] U : T) {
			Arrays.fill(U, -1);
		}
		long ans = 0;
		if (a > 0) {
			ans += A.eval(k).mod(MOD).longValue() * f(a - 1, b, k, T);
		}
		if (b > 0) {
			ans += B.eval(k).mod(MOD).longValue() * f(a, b - 1, k, T);
		}
		return ans % MOD.longValue();
	}

	public static void main(String[] args) {
		System.out.println(f(25, 75, 1984));
	}
}
