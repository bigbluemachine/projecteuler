package s100;

import java.util.TreeSet;

import core.ArrayLib;
import core.Selector;

public class P103 {
	static int best;
	static int len;

	static boolean test(int[] a, long v, long w) {
		int sa = 0, sb = 0, ca = 0, cb = 0;
		for (int i = 0; i < a.length; i++) {
			if ((v & (1 << i)) > 0) {
				sa += a[i];
				ca++;
			} else {
				sb += a[i];
				cb++;
			}
		}
		return ca > cb ? sa > sb : ca < cb ? sa < sb : sa != sb;
	}

	static boolean test2(int[] a) {
		TreeSet<Long> V = new TreeSet<Long>();

		for (int i = 1; i + i <= a.length; i++) {
			Selector s = new Selector(a.length, i);
			while (s.hasNext()) {
				long v = s.next();
				long w = s.inverse(v);
				if (V.contains(v) || V.contains(w)) {
					continue;
				}
				V.add(v);

				if (!test(a, v, w)) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean test(int[] a) {
		for (int i = 2; i <= a.length; i++) {
			Selector s = new Selector(a.length, i);
			while (s.hasNext()) {
				long v = s.next();

				int[] b = new int[i];
				for (int j = 0, k = 0; j < a.length; j++) {
					if ((v & (1 << j)) > 0) {
						b[k++] = j;
					}
				}

				if (!test2(ArrayLib.map(a, b))) {
					return false;
				}
			}
		}

		return true;
	}

	static void search(int[] v, int min, int max) {
		if (v.length == len) {
			int sum = 0;
			for (int i : v) {
				sum += i;
			}
			if (sum < best) {
				best = sum;
				String ans = "";
				for (int i : v) {
					ans += i;
				}
				System.out.println(ans);
			}
			return;
		}

		for (int i = min; i <= max; i++) {
			int[] w = ArrayLib.concat(v, new int[] { i });
			if (test(w)) {
				search(w, i + 1, max);
			}
		}
	}

	public static void main(String[] args) {
		best = 100000000;
		len = 7;

		for (int q = 11; q <= 40; q++) {
			for (int p = 10; p < q; p++) {
				int test = p + q;
				for (int i = 2; i < len; i++) {
					test += q + i - 1;
				}
				if (test >= best) {
					break;
				}
				search(new int[] { p, q }, q + 1, p + q);
			}
		}
	}
}
