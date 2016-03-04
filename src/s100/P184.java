package s100;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import core.MathLib;

public class P184 {
	static class P implements Comparable<P> {
		int x, y, n, q;

		static final P Z = new P(0, 0);

		P(int x, int y) {
			this.x = x;
			this.y = y;
			this.q = quadrant();
		}

		P inv() {
			return new P(-x, -y);
		}

		int quadrant() {
			if (x > 0) {
				return y >= 0 ? 1 : 4;
			}
			if (x < 0) {
				return y <= 0 ? 3 : 2;
			}
			return y > 0 ? 2 : 4;
		}

		int times(int r) {
			int xx = x;
			int yy = y;
			int ans = 0;
			do {
				ans++;
				xx += x;
				yy += y;
			} while (xx * xx + yy * yy < r * r);
			return ans;
		}

		@Override
		public int compareTo(P p) {
			int s = this.q - p.q;
			if (s != 0) {
				return s;
			}
			int t = ccw(P.Z, this, p);
			return t == 0 ? Math.abs(this.x) - Math.abs(p.x) : -t;
		}
	}

	static int ccw(P a, P b, P c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	static int quad(int x, int y) {
		if (x > 0) {
			return y >= 0 ? 1 : 4;
		}
		if (x < 0) {
			return y <= 0 ? 3 : 2;
		}
		return y > 0 ? 2 : 4;
	}

	static TreeSet<P> pts(int r) {
		TreeSet<P> ans = new TreeSet<P>();
		for (int x = -r + 1; x < r; x++) {
			for (int y = -r + 1; y < r; y++) {
				if (x == 0 || y == 0) {
					continue;
				}
				if (MathLib.gcd32(Math.abs(x), Math.abs(y)) > 1) {
					continue;
				}
				if (x * x + y * y < r * r) {
					ans.add(new P(x, y));
				}
			}
		}
		ans.add(new P(0, 1));
		ans.add(new P(0, -1));
		ans.add(new P(1, 0));
		ans.add(new P(-1, 0));
		for (P p : ans) {
			p.n = p.times(r);
		}
		return ans;
	}

	static int sum(int[] v, int a, int b) {
		return a > b ? 0 : a == 0 ? v[b] : v[b] - v[a - 1];
	}

	public static void main(String[] args) {
		int r = 105;

		TreeSet<P> S = pts(r);
		TreeMap<P, Integer> I = new TreeMap<P, Integer>();
		int[] v = new int[S.size()];
		int i = 0, s = 0;
		for (P p : S) {
			I.put(p, i);
			v[i++] = (s += p.n);
		}

		long ans = 0;
		for (P a : S) {
			P aa = a.inv();
			Set<P> T;
			if (aa.compareTo(a) > 0) {
				T = S.subSet(S.first(), true, a, false);
			} else {
				T = S.subSet(aa, false, a, false);
			}
			for (P b : T) {
				P bb = b.inv();
				if (bb.compareTo(b) < 0) {
					ans += a.n * b.n * sum(v, I.get(bb) + 1, I.get(aa) - 1);
				} else if (bb.compareTo(aa) > 0) {
					ans += a.n * b.n * sum(v, 0, I.get(aa) - 1);
				}
			}
		}
		System.out.println(ans);
	}
}
