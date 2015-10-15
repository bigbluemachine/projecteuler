package s000;

import java.util.ArrayList;
import java.util.TreeSet;

public class P090 {
	static ArrayList<X> S = new ArrayList<X>();

	static class X {
		int[] v;

		public X(int[] v) {
			this.v = new int[6];
			System.arraycopy(v, 0, this.v, 0, 6);
		}
	}

	static void gen(int[] v, int i) {
		if (i == 0) {
			for (int d = 0; d <= 4; d++) {
				v[0] = d;
				gen(v, i + 1);
			}
		} else if (i == 6) {
			S.add(new X(v));
		} else {
			for (int d = v[i - 1] + 1; d <= 9; d++) {
				v[i] = d;
				gen(v, i + 1);
			}
		}
	}

	static boolean test(int[] a, int[] b) {
		TreeSet<Integer> T = new TreeSet<Integer>();
		for (int x : a) {
			for (int y : b) {
				T.add(10 * x + y);
				T.add(10 * y + x);
				if (x == 6 || x == 9) {
					x = 15 - x;
					T.add(10 * x + y);
					T.add(10 * y + x);
				}
				if (y == 6 || y == 9) {
					y = 15 - y;
					T.add(10 * x + y);
					T.add(10 * y + x);
				}
			}
		}
		for (int i = 1; i <= 9; i++) {
			if (!T.contains(i * i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		gen(new int[6], 0);
		int ans = 0;
		for (int i = 0; i < S.size(); i++) {
			for (int j = i; j < S.size(); j++) {
				if (test(S.get(i).v, S.get(j).v)) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
