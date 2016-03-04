package s100;

import java.util.TreeMap;
import java.util.TreeSet;

public class P142 {
	static int hyp(long a, long b) {
		return (int) Math.sqrt(a * a + b * b);
	}

	static TreeMap<Integer, TreeSet<Integer>> genPyth(int h) {
		TreeMap<Integer, TreeSet<Integer>> ans = new TreeMap<Integer, TreeSet<Integer>>();
		for (int n = 2; n * n < h; n++) {
			for (int m = (n & 1) + 1; m < n; m += 2) {
				int c = n * n + m * m;
				if (c > h) {
					break;
				}
				int b = 2 * n * m;
				int a = n * n - m * m;
				int aa = a;
				int bb = b;
				int cc = c;
				while (cc <= h) {
					if (!ans.containsKey(aa)) {
						ans.put(aa, new TreeSet<Integer>());
					}
					ans.get(aa).add(bb);
					aa += a;
					bb += b;
					cc += c;
				}
			}
		}
		return ans;
	}

	static boolean shares(TreeMap<Integer, TreeSet<Integer>> pyth, int a, int b) {
		if (pyth.containsKey(a) && pyth.get(a).contains(b)) {
			return true;
		}
		if (pyth.containsKey(b) && pyth.get(b).contains(a)) {
			return true;
		}
		return false;
	}

	static long search(TreeMap<Integer, TreeSet<Integer>> pyth) {
		// B + E = C
		// B + F = D
		// C + F = A
		// D + E = A
		// 1. Find B such that E != F (E > F)
		// 2. Confirm C + F = A for some A
		// 3. Confirm D + E = A
		long best = Long.MAX_VALUE;
		for (int b : pyth.keySet()) {
			int l = pyth.get(b).size();
			if (l < 2) {
				continue;
			}
			Integer[] v = pyth.get(b).toArray(new Integer[0]);
			for (int i = 0; i < l; i++) {
				int e = v[i];
				for (int j = 0; j < i; j++) {
					int f = v[j];
					int c = hyp(b, e);
					int d = hyp(b, f);
					int a;
					if (shares(pyth, c, f)) {
						a = hyp(c, f);
					} else {
						continue;
					}
					int aa;
					if (shares(pyth, d, e)) {
						aa = hyp(d, e);
					} else {
						continue;
					}
					if (a == aa) {
						long xpy = (long) a * a;
						long xmy = (long) b * b;
						long ypz = (long) e * e;
						long x = (xpy + xmy) / 2;
						long xyz = x + ypz;
						best = Math.min(best, xyz);
					}
				}
			}
		}
		return best;
	}

	public static void main(String[] args) {
		System.out.println(search(genPyth(10000)));
	}
}
