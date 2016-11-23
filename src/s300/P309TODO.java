package s300;

import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import core.MathLib;
import core.NTLib;

public class P309TODO {
	static int MAX;
	static TreeMap<Integer, TreeSet<Integer>> M;
	static LinkedList<Integer>[] Q;

	static class T implements Comparable<T> {
		int x;
		int y;
		int h;

		T(int a, int b, int c) {
			x = Math.min(a, b);
			y = Math.max(a, b);
			h = c;
		}

		@Override
		public int compareTo(T t) {
			if (x != t.x) {
				return x - t.x;
			}
			if (y != t.y) {
				return y - t.y;
			}
			return h - t.h;
		}
	}

	static int hyp(long a, long b) {
		return (int) Math.sqrt(a * a + b * b);
	}

	static TreeSet<Integer> comb(LinkedList<Integer> a, LinkedList<Integer> b) {
		TreeSet<Integer> c = new TreeSet<Integer>();
		for (int i : a) {
			for (int j : b) {
				c.add(i * j);
			}
		}
		return c;
	}

	static TreeSet<T> f(int i, int a, int b, int c) {
		TreeSet<T> S = new TreeSet<T>();
		int w = i * a;
		int p = i * b;
		int x = i * c;
		TreeSet<Integer> da = comb(Q[a - 1], Q[i - 1]);
		for (int d : da) {
			if (!M.containsKey(d)) {
				continue;
			}
			int j = w / d;
			for (int e : M.get(d)) {
				int q = j * e;
				if (q == p) {
					continue;
				}
				int y = j * hyp(d, e);
				if (y >= MAX) {
					continue;
				}
				long pq = (long) p * q;
				if (pq % (p + q) == 0) {
					int h = (int) (pq / (p + q));
					S.add(new T(x, y, h));
				}
			}
		}
		return S;
	}

	public static void main(String[] args) {
		// TODO ~2m
		MAX = 1000000;

		M = new TreeMap<Integer, TreeSet<Integer>>();
		Q = NTLib.batchFactor(1, MAX);

		for (int n = 2; n * n < MAX; n++) {
			for (int m = (n % 2) + 1; m < n && n * n + m * m < MAX; m += 2) {
				if (MathLib.gcd32(n, m) != 1) {
					continue;
				}
				int a = n * n - m * m;
				int b = 2 * n * m;
				if (!M.containsKey(a)) {
					M.put(a, new TreeSet<Integer>());
				}
				M.get(a).add(b);
				if (!M.containsKey(b)) {
					M.put(b, new TreeSet<Integer>());
				}
				M.get(b).add(a);
			}
		}

		TreeSet<T> S = new TreeSet<T>();
		for (int a : M.keySet()) {
			for (int b : M.get(a)) {
				int c = hyp(a, b);
				for (int i = 1; i * c < MAX; i++) {
					S.addAll(f(i, a, b, c));
				}
			}
		}
		System.out.println(S.size());
	}
}
