package s100;

import java.util.ArrayList;

import core.Ratio;

public class P163 {
	static int N;

	static class P {
		int x, y;

		P(int a, int b) {
			x = a;
			y = b;
		}
	}

	static class Q implements Comparable<Q> {
		Ratio x, y;

		Q(Ratio a, Ratio b) {
			x = a;
			y = b;
		}

		@Override
		public int compareTo(Q q) {
			int a = x.compareTo(q.x);
			return a == 0 ? y.compareTo(q.y) : a;
		}
	}

	static class L {
		P p, q;

		L(int a, int b, int c, int d) {
			p = new P(a, b);
			q = new P(c, d);
		}
	}

	static int ccw(P a, P b, P c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	static boolean i(P a, P b, P c, P d) {
		int e = ccw(a, c, b);
		int f = ccw(c, b, d);
		int g = ccw(b, d, a);
		int h = ccw(d, a, c);
		if (e == 0 || f == 0 || g == 0 || h == 0) {
			return false;
		}
		if (e > 0) {
			return f > 0 && g > 0 && h > 0;
		} else {
			return f < 0 && g < 0 && h < 0;
		}
	}

	static boolean i(L a, L b) {
		return i(a.p, a.q, b.p, b.q);
	}

	static Q f(P a, P b, P c, P d) {
		int dxcx = d.x - c.x;
		int aycy = a.y - c.y;
		int dycy = d.y - c.y;
		int axcx = a.x - c.x;
		int bxax = b.x - a.x;
		int byay = b.y - a.y;
		int p = dxcx * aycy - dycy * axcx;
		int q = dycy * bxax - dxcx * byay;
		Ratio u = new Ratio(p, q);
		Ratio x = new Ratio(a.x, 1).add(u.mul(new Ratio(bxax, 1)));
		Ratio y = new Ratio(a.y, 1).add(u.mul(new Ratio(byay, 1)));
		return new Q(x, y);
	}

	static Q f(L a, L b) {
		return f(a.p, a.q, b.p, b.q);
	}

	static boolean isParallel(L a, L b) {
		int dxa = a.p.x - a.q.x;
		int dya = a.p.y - a.q.y;
		int dxb = b.p.x - b.q.x;
		int dyb = b.p.y - b.q.y;
		return dxa * dyb == dya * dxb;
	}

	static boolean isSane(Q q) {
		if (q.x.compareTo(new Ratio(0, 1)) < 0) {
			return false;
		}
		if (q.y.compareTo(new Ratio(0, 1)) < 0) {
			return false;
		}
		return q.x.add(q.y).compareTo(new Ratio(2 * N, 1)) <= 0;
	}

	static boolean f(L a, L b, L c) {
		Q i = f(a, b);
		Q j = f(a, c);
		Q k = f(b, c);
		if (i.compareTo(j) == 0 || i.compareTo(k) == 0 || j.compareTo(k) == 0) {
			return false;
		}
		return isSane(i) && isSane(j) && isSane(k);
	}

	public static void main(String[] args) {
		N = 36;

		ArrayList<L> ls = new ArrayList<L>();
		for (int i = 0; i < N; i++) {
			ls.add(new L(0, 2 * i, 2 * (N - i), 2 * i));
			ls.add(new L(2 * i, 0, 2 * i, 2 * (N - i)));
			ls.add(new L(0, 2 * (N - i), 2 * (N - i), 0));
		}
		ls.add(new L(0, 0, N, N));
		ls.add(new L(0, 2 * N, N, 0));
		ls.add(new L(0, N, 2 * N, 0));
		for (int i = 1; i < N; i++) {
			ls.add(new L(2 * i, 0, 2 * i + (N - i), (N - i)));
			ls.add(new L(0, 2 * i, (N - i), 2 * i + (N - i)));
			ls.add(new L(0, 2 * i, i, 0));
			ls.add(new L(2 * (N - i), 2 * i, 2 * (N - i) + i, 0));
			ls.add(new L(0, i, 2 * i, 0));
			ls.add(new L(0, 2 * N - i, 2 * i, 2 * N - 2 * i));
		}

		int ans = 0;
		for (int i = 0; i < ls.size(); i++) {
			L a = ls.get(i);
			for (int j = i + 1; j < ls.size(); j++) {
				L b = ls.get(j);
				if (isParallel(a, b)) {
					continue;
				}
				for (int k = j + 1; k < ls.size(); k++) {
					L c = ls.get(k);
					if (isParallel(a, c) || isParallel(b, c)) {
						continue;
					}
					if (f(a, b, c)) {
						ans++;
					}
				}
			}
		}

		System.out.println(ans);
	}
}
