package s100;

import java.util.LinkedList;

public class P149 {
	static int f(int[] n) {
		int ans = 0;
		int t = 0;
		for (int i : n) {
			t = Math.max(0, t + i);
			ans = Math.max(ans, t);
		}
		return ans;
	}

	static int f(int[][] v, int n) {
		int ans = 0;
		int a, b;

		for (int i = 0; i < n; i++) {
			a = 0;
			b = 0;
			for (int j = 0; j < n; j++) {
				b = Math.max(0, b + v[i][j]);
				a = Math.max(a, b);
			}
			ans = Math.max(ans, a);
		}

		for (int j = 0; j < n; j++) {
			a = 0;
			b = 0;
			for (int i = 0; i < n; i++) {
				b = Math.max(0, b + v[i][j]);
				a = Math.max(a, b);
			}
			ans = Math.max(ans, a);
		}

		for (int k = 0; k < 2 * n - 1; k++) {
			int i = k - (n - 1);
			int j = 0;

			if (i < 0) {
				j += -i;
				i += -i;
			}

			a = 0;
			b = 0;
			for (; i < n && j < n; i++, j++) {
				b = Math.max(0, b + v[i][j]);
				a = Math.max(a, b);
			}
			ans = Math.max(ans, a);
		}

		for (int k = 0; k < 2 * n - 1; k++) {
			int i = k;
			int j = 0;

			if (i >= n) {
				j += i - n + 1;
				i = n - 1;
			}

			a = 0;
			b = 0;
			for (; i >= 0 && j < n; i--, j++) {
				b = Math.max(0, b + v[i][j]);
				a = Math.max(a, b);
			}
			ans = Math.max(ans, a);
		}

		return ans;
	}

	public static void main(String[] args) {
		LinkedList<Long> L = new LinkedList<Long>();
		for (int k = 1; k <= 55; k++) {
			long x = 100003L - 200003L * k + 300007L * k * k * k;
			x %= 1000000;
			x -= 500000;
			L.add(x);
		}

		int[][] a = new int[2000][2000];
		for (int i = 0; i < 2000; i++) {
			for (int j = 0; j < 2000; j++) {
				long y = L.get(31);
				long x = L.removeFirst();
				L.add((y + x + 1000000) % 1000000 - 500000);
				a[i][j] = (int) x;
			}
		}

		System.out.println(f(a, 2000));
	}
}
