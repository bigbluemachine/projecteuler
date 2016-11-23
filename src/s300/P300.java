package s300;

import java.util.ArrayList;
import java.util.HashSet;

public class P300 {
	static int N;
	static HashSet<A> L = new HashSet<A>();

	static class A {
		ArrayList<Integer> qs = new ArrayList<Integer>();

		A(int[] v) {
			for (int i = 0; i < N; i++) {
				int xi = v[i] >> 6;
				int yi = v[i] & 63;
				for (int j = i + 2; j < N; j++) {
					int xj = v[j] >> 6;
					int yj = v[j] & 63;
					int dx = Math.abs(xi - xj);
					int dy = Math.abs(yi - yj);
					if (dx == 0 && dy == 1 || dy == 0 && dx == 1) {
						qs.add(i << 6 | j);
					}
				}
			}
		}

		public boolean equals(Object other) {
			A a = (A) other;
			return qs.equals(a.qs);
		}

		public int hashCode() {
			return qs.hashCode();
		}
	}

	static void gen(int[] v, int x, int y, int i) {
		int z = encode(x, y);
		for (int j = 0; j < i; j++) {
			if (v[j] == z) {
				return;
			}
		}

		v[i] = z;
		if (i + 1 == N) {
			A a = new A(v);
			if (!a.qs.isEmpty()) {
				L.add(a);
			}
			return;
		}
		gen(v, x + 1, y, i + 1);
		gen(v, x - 1, y, i + 1);
		gen(v, x, y + 1, i + 1);
		gen(v, x, y - 1, i + 1);
	}

	static int encode(int x, int y) {
		return x << 6 | y;
	}

	static int count(int v, A a) {
		int ans = 0;
		int m = 3;
		for (int i = 1; i < N; i++) {
			if ((v & m) == m) {
				ans++;
			}
			m <<= 1;
		}
		for (int p : a.qs) {
			int i = p >> 6;
			int j = p & 63;
			if ((v & (1 << i)) > 0 && (v & (1 << j)) > 0) {
				ans++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		N = 15;
		int[] t = new int[N];
		t[0] = encode(16, 16);
		t[1] = encode(16, 17);
		gen(t, 17, 17, 2);
		gen(t, 15, 17, 2);
		gen(t, 16, 18, 2);

		int M = 1 << N;
		int[] max = new int[M];
		for (int v = 0; v < M; v++) {
			for (A a : L) {
				max[v] = Math.max(max[v], count(v, a));
			}
		}

		long ans = 0;
		for (int i = 0; i < M; i++) {
			ans += max[i];
		}
		System.out.println((double) ans / M);
	}
}
