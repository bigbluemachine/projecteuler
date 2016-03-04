package s200;

import java.util.HashMap;

public class P208 {
	static int MOVES, MAX;
	static HashMap<Integer, Long> M;
	static final int CCW = 0, CW = 1;

	static class State {
		int[] a;
		int last, remain;
		boolean ccw;

		State(int[] a, int last, int remain, boolean ccw) {
			this.a = new int[5];
			this.last = last;
			this.remain = remain;
			this.ccw = ccw;
			System.arraycopy(a, 0, this.a, 0, 5);
		}

		State copy() {
			return new State(a, last, remain, ccw);
		}

		State ccw() {
			State s = copy();
			if (ccw) {
				s.last = (last + 4) % 5;
			}
			s.remain--;
			s.ccw = true;
			return ++s.a[s.last] > MAX ? null : s;
		}

		State cw() {
			State s = copy();
			if (!ccw) {
				s.last = (last + 1) % 5;
			}
			s.remain--;
			s.ccw = false;
			return ++s.a[s.last] > MAX ? null : s;
		}

		int encode() {
			int x = 2 * (5 * remain + last) + (ccw ? 0 : 1);
			for (int i = 0; i < 5; i++) {
				x = (x << 4) + a[i];
			}
			return x;
		}
	}

	static long f(State s) {
		int k = s.encode();
		if (M.containsKey(k)) {
			return M.get(k);
		}

		long ans = 0;
		if (s.remain == 0) {
			ans = 1;
		} else {
			State ccw = s.ccw();
			State cw = s.cw();
			if (ccw != null) {
				ans += f(ccw);
			}
			if (cw != null) {
				ans += f(cw);
			}
		}

		M.put(k, ans);
		return ans;
	}

	static long run(int m) {
		MOVES = m;
		MAX = (MOVES - 1) / 5 + 1;
		M = new HashMap<Integer, Long>();

		return f(new State(new int[5], 3, MOVES, true));
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		long ans = run(70);
		System.out.printf("Answer: %d\n", ans);
		System.out.printf("Time: %d ms\n", System.currentTimeMillis() - t);
	}
}
