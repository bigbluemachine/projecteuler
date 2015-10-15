package s100;

import java.util.HashMap;

public class P161 {
	static int W, H;
	static HashMap<Long, Long> T;

	static int get(long r, int i) {
		return (int) ((r & (0xfL << (4 * i))) >> (4 * i));
	}

	static long inc(long r, int a, int i) {
		return r + ((long) a << (4 * i));
	}

	static long f(long r) {
		if (T.containsKey(r)) {
			return T.get(r);
		}

		int minH = H, minI = W;

		for (int c = W - 1; c >= 0; c--) {
			int h = get(r, c);
			if (h <= minH) {
				minH = h;
				minI = c;
			}
		}

		long ans = 0;

		if (minH + 3 <= H) {
			ans += f(inc(r, 3, minI));
		}

		if (minH + 2 <= H) {
			if (minI + 1 < W && get(r, minI + 1) == minH) {
				long rr = r;
				rr = inc(rr, 1, minI);
				rr = inc(rr, 1, minI + 1);
				ans += f(inc(rr, 1, minI));
				ans += f(inc(rr, 1, minI + 1));
			}

			if (minI - 1 >= 0 && get(r, minI - 1) == minH + 1) {
				long rr = r;
				rr = inc(rr, 1, minI - 1);
				ans += f(inc(rr, 2, minI));
			}

			if (minI + 1 < W && get(r, minI + 1) == minH + 1) {
				long rr = r;
				rr = inc(rr, 1, minI + 1);
				ans += f(inc(rr, 2, minI));
			}

			if (minI + 2 < W && get(r, minI + 1) == minH && get(r, minI + 2) == minH) {
				long rr = r;
				rr = inc(rr, 2, minI);
				rr = inc(rr, 2, minI + 1);
				rr = inc(rr, 2, minI + 2);
				ans += f(rr);
			}

			if (minI + 3 < W && get(r, minI + 1) == minH && get(r, minI + 2) == minH && get(r, minI + 3) == minH) {
				long rr = r;
				rr = inc(rr, 2, minI);
				rr = inc(rr, 2, minI + 1);
				rr = inc(rr, 1, minI + 2);
				rr = inc(rr, 1, minI + 3);
				ans += f(rr);
			}
		}

		if (minI + 2 < W && get(r, minI + 1) == minH && get(r, minI + 2) == minH) {
			long rr = r;
			rr = inc(rr, 1, minI);
			rr = inc(rr, 1, minI + 1);
			rr = inc(rr, 1, minI + 2);
			ans += f(rr);
		}

		T.put(r, ans);
		return ans;
	}

	static long run(int w, int h) {
		W = w;
		H = h;
		T = new HashMap<Long, Long>();

		long r = 0;
		for (int i = 0; i < W; i++) {
			r <<= 4;
			r += H;
		}
		T.put(r, 1L);

		return f(0);
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		long ans = run(9, 12);
		System.out.printf("Answer: %d\n", ans);
		System.out.printf("Time: %d ms\n", System.currentTimeMillis() - t);
	}
}
