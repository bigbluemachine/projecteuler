package s200;

import java.util.HashMap;

public class P298 {
	static HashMap<Integer, Double> T = new HashMap<Integer, Double>();

	static class State {
		int[] L, R;
		int c;

		public State() {
			L = new int[5];
			R = new int[5];
			c = 0;
		}

		public State(State s) {
			L = new int[5];
			R = new int[5];
			System.arraycopy(s.L, 0, L, 0, 5);
			System.arraycopy(s.R, 0, R, 0, 5);
			c = s.c;
		}

		public int update(int n) {
			boolean l = update(L, n, true);
			boolean r = update(R, n, false);
			if (c != 5 && !l) {
				c++;
			}
			return l ? (r ? 0 : 1) : (r ? -1 : 0);
		}

		private boolean update(int[] a, int n, boolean swap) {
			int i = indexOf(a, n, c);
			if (i == -1) {
				if (c < 5) {
					a[c] = n;
				} else {
					pushRotate(a, n, 5);
				}
				return false;
			}
			if (swap) {
				swapRotate(a, i, c);
			}
			return true;
		}

		public void compress() {
			int[] m = new int[11];
			int cur = 1;
			for (int i = 0; i < c; i++) {
				m[L[i]] = cur++;
			}
			for (int i = 0; i < c; i++) {
				if (m[R[i]] == 0) {
					m[R[i]] = cur++;
				}
			}
			for (int i = 0; i < c; i++) {
				L[i] = m[L[i]];
			}
			for (int i = 0; i < c; i++) {
				R[i] = m[R[i]];
			}
		}

		public int encode() {
			int x = 0;
			for (int i = 0; i < c; i++) {
				x = 10 * x + (R[i] - 1);
			}
			return x;
		}
	}

	static int indexOf(int[] a, int n, int count) {
		for (int i = 0; i < count; i++) {
			if (a[i] == n) {
				return i;
			}
		}
		return -1;
	}

	static void pushRotate(int[] a, int n, int count) {
		for (int i = 1; i < count; i++) {
			a[i - 1] = a[i];
		}
		a[count - 1] = n;
	}

	static void swapRotate(int[] a, int i, int count) {
		int t = a[i];
		for (int j = i + 1; j < count; j++) {
			a[j - 1] = a[j];
		}
		a[count - 1] = t;
	}

	static double f(State s, int turns, int diff) {
		int key = s.encode();
		key = (key << 6) | turns;
		key = (key << 7) | (diff + 64);

		if (T.containsKey(key)) {
			return T.get(key);
		}

		double ans;
		if (turns == 0) {
			ans = diff < 0 ? -diff : diff;
		} else {
			ans = 0;
			for (int i = 1; i <= 10; i++) {
				State t = new State(s);
				int d = t.update(i);
				t.compress();
				ans += f(t, turns - 1, diff + d);
			}
			ans *= 0.1;
		}

		T.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		System.out.println(f(new State(), 50, 0));
		System.out.printf("Size = %d\n", T.size());
		System.out.printf("%d ms\n", System.currentTimeMillis() - t);
	}
}
