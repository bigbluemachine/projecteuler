package s200;

import java.util.Comparator;
import java.util.PriorityQueue;

public class P247 {
	static class R {
		double x, y, s;
		int p, q;

		R(double x, double y, int p, int q) {
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
			s = x + y;
			s = 0.5 * (Math.sqrt(s * s - 4.0 * (x * y - 1.0)) - s);
		}

		R u() {
			return new R(x, y + s, p, q + 1);
		}

		R r() {
			return new R(x + s, y, p + 1, q);
		}
	}

	public static void main(String[] args) {
		PriorityQueue<R> Q = new PriorityQueue<R>(new Comparator<R>() {
			@Override
			public int compare(R a, R b) {
				return Double.compare(b.s, a.s);
			}
		});
		Q.add(new R(1, 0, 0, 0));

		int k = 3;
		int count = 1;
		for (int i = 1;; i++) {
			R r = Q.remove();
			if (r.p <= k && r.q <= k) {
				count--;
			}
			if (count == 0 && i > 1) {
				System.out.println(i);
				break;
			}

			R u = r.u();
			R v = r.r();
			Q.add(u);
			Q.add(v);
			if (u.p <= k && u.q <= k) {
				count++;
			}
			if (v.p <= k && v.q <= k) {
				count++;
			}
		}
	}
}
