package s100;

import java.util.TreeSet;

import core.Ratio;

public class P165 {
	static class P {
		int x, y;

		P(int a, int b) {
			x = a;
			y = b;
		}
	}

	static class Q implements Comparable<Q> {
		double x, y;

		Q(double a, double b) {
			x = a;
			y = b;
		}

		@Override
		public int compareTo(Q q) {
			return x == q.x ? Double.compare(y, q.y) : Double.compare(x, q.x);
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
		return new Q((double) x.n / x.d, (double) y.n / y.d);
	}

	public static void main(String[] args) {
		long s = 290797;
		P[] P1 = new P[5000];
		P[] P2 = new P[5000];
		for (int i = 0; i < 5000; i++) {
			int x1 = (int) (s = (s * s) % 50515093) % 500;
			int y1 = (int) (s = (s * s) % 50515093) % 500;
			int x2 = (int) (s = (s * s) % 50515093) % 500;
			int y2 = (int) (s = (s * s) % 50515093) % 500;
			P1[i] = new P(x1, y1);
			P2[i] = new P(x2, y2);
		}

		TreeSet<Q> S = new TreeSet<Q>();
		for (int i = 0; i < 5000; i++) {
			for (int j = i + 1; j < 5000; j++) {
				if (i(P1[i], P2[i], P1[j], P2[j])) {
					S.add(f(P1[i], P2[i], P1[j], P2[j]));
				}
			}
		}
		System.out.println(S.size());
	}
}
