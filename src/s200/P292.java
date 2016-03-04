package s200;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import core.MathLib;

public class P292 {
	static class Segment implements Comparable<Segment> {
		int dx, dy, len;

		Segment(int x, int y, int l) {
			dx = x;
			dy = y;
			len = l;
		}

		public int compareTo(Segment s) {
			return Double.compare(Math.atan2(-dy, -dx), Math.atan2(-s.dy, -s.dx));
		}
	}

	static int U, H;
	static Segment[] S;

	static void init() {
		TreeSet<Segment> ss = new TreeSet<Segment>();
		ss.add(new Segment(0, 1, 1));
		ss.add(new Segment(-1, 0, 1));
		ss.add(new Segment(0, -1, 1));

		for (int m = 2; m < 8; m++) {
			for (int n = (m & 1) + 1; n < m; n += 2) {
				if (MathLib.gcd32(m, n) == 1) {
					int c = m * m + n * n;
					if (c < U / 2) {
						int a = m * m - n * n;
						int b = 2 * m * n;
						ss.add(new Segment(a, b, c));
						ss.add(new Segment(a, -b, c));
						ss.add(new Segment(-a, b, c));
						ss.add(new Segment(-a, -b, c));
						ss.add(new Segment(b, a, c));
						ss.add(new Segment(b, -a, c));
						ss.add(new Segment(-b, a, c));
						ss.add(new Segment(-b, -a, c));
					}
				}
			}
		}

		LinkedList<Segment> ts = new LinkedList<Segment>();
		ts.add(new Segment(1, 0, 1));

		for (Segment s : ss)
			ts.add(s);

		S = ts.toArray(new Segment[ts.size()]);
	}

	static HashMap<Integer, Long> M = new HashMap<Integer, Long>();

	static final boolean ccw(Segment s, Segment t) {
		return s.dx * t.dy > s.dy * t.dx;
	}

	static long f(int last, int curX, int curY, int left) {
		int key = ((last << 8 | (H + curX)) << 8 | curY) << 8 | left;
		if (M.containsKey(key))
			return M.get(key);

		long ans = 0;
		L: for (int i = last + 1; i < S.length; i++) {
			Segment next = S[i];
			if (!ccw(S[last], next))
				break;

			int dx = next.dx, dy = next.dy, len = next.len;
			if (curX * dy == dx * curY) {
				int p, q;
				if (dx == 0) {
					p = Math.abs(curY);
					q = Math.abs(dy);
				} else {
					p = Math.abs(curX);
					q = Math.abs(dx);
				}
				if (p % q == 0 && (p / q) * len <= left)
					ans++;
				break L;
			}

			Segment remain = new Segment(-curX - dx, -curY - dy, 0);
			if (!ccw(next, remain))
				break;

			for (int j = 1, l = len; l <= left; j++, l += len) {
				int yn = curY + j * dy;
				if (yn >= 0)
					ans += f(i, curX + j * dx, yn, left - l);
			}
		}

		M.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		U = 120;
		H = U / 2;
		init();

		long ans = 0;
		for (int i = 0; S[i].dx != -1; i++)
			for (int j = 1, l = S[i].len; l < H; j++, l += S[i].len)
				ans += f(i, j * S[i].dx, j * S[i].dy, U - l);

		System.out.println(ans);
	}
}
