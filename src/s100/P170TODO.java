package s100;

import java.util.Arrays;
import java.util.LinkedList;

public class P170TODO {
	static LinkedList<Integer> S = new LinkedList<Integer>();
	static long ANS = 0;

	static boolean[] cancel(boolean[] v, int n) {
		if (n == 0) {
			if (!v[0]) {
				return null;
			}
			boolean[] ans = v.clone();
			ans[0] = false;
			return ans;
		}

		boolean[] ans = v.clone();
		while (n > 0) {
			int d = n % 10;
			if (!ans[d]) {
				return null;
			}
			ans[d] = false;
			n /= 10;
		}
		return ans;
	}

	static void test() {
		if (S.size() < 3) {
			return;
		}
		int a = S.get(0);
		int[] b = new int[S.size() - 1];
		for (int i = 1; i < S.size(); i++) {
			b[i - 1] = a * S.get(i);
		}
		Arrays.sort(b);
		String t = "";
		for (int k : b) {
			t = k + t;
		}
		long test = Long.parseLong(t);
		if (test > ANS) {
			ANS = test;
			System.out.println(S + " " + ANS);
		}
	}

	static void gen(int l, boolean[] left, boolean[] right, int first) {
		int leftCount = 0;
		int rightCount = 0;
		for (int i = 0; i < 10; i++) {
			if (left[i]) {
				leftCount++;
			}
			if (right[i]) {
				rightCount++;
			}
		}

		if (leftCount == 0 && rightCount == 0) {
			test();
			return;
		}

		for (int n = first; n <= 98765; n++) {
			int p = l * n;
			String s = Integer.toString(p);
			if (s.length() > rightCount) {
				break;
			}

			boolean[] w = cancel(right, p);
			if (w == null) {
				continue;
			}

			boolean[] v = cancel(left, n);
			if (v == null) {
				continue;
			}

			S.addLast(n);
			gen(l, v, w, n + 1);
			S.removeLast();
		}
	}

	public static void main(String[] args) {
		Z: for (int i = 2; i <= 9876; i++) {
			if (i % 10 == 0) {
				continue;
			}
			boolean[] v = new boolean[10];
			int t = i;
			while (t > 0) {
				int d = t % 10;
				if (v[d]) {
					continue Z;
				}
				v[d] = true;
				t /= 10;
			}
			for (int j = 0; j < 10; j++) {
				v[j] = !v[j];
			}
			S.addLast(i);
			boolean[] w = new boolean[10];
			Arrays.fill(w, true);
			gen(i, v, w, 1);
			S.removeLast();
		}

		// TODO slow
		System.out.println(ANS);
	}
}
