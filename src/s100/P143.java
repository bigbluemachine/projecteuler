package s100;

import java.util.HashSet;
import java.util.TreeMap;

import core.MathLib;

public class P143 {
	static void gen_120(TreeMap<Integer, HashSet<Integer>> v, int p) {
		int maxN = (int) Math.sqrt(p / 2);
		for (int n = 2; n <= maxN; n++) {
			for (int m = 1; m < n; m++) {
				int a = n * n - m * m;
				int b = 2 * n * m + m * m;
				int c = n * n + m * m + n * m;
				if (a + b + c > p) {
					break;
				}
				if ((m - n) % 3 != 0 && MathLib.gcd32(m, n) == 1) {
					int x = Math.min(a, b);
					int y = Math.max(a, b);
					int xx = x;
					int yy = y;
					int cc = c;
					while (xx + yy + cc <= p) {
						if (!v.containsKey(xx)) {
							v.put(xx, new HashSet<Integer>());
						}
						v.get(xx).add(yy);
						xx += x;
						yy += y;
						cc += c;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		TreeMap<Integer, HashSet<Integer>> v = new TreeMap<Integer, HashSet<Integer>>();
		gen_120(v, 240000);

		HashSet<Integer> S = new HashSet<Integer>();
		for (int p : v.keySet()) {
			for (int q : v.get(p)) {
				if (!v.containsKey(q)) {
					continue;
				}
				for (int r : v.get(q)) {
					if (v.get(p).contains(r)) {
						int s = p + q + r;
						if (s <= 120000) {
							S.add(s);
						}
					}
				}
			}
		}

		long ans = 0;
		for (int s : S) {
			ans += s;
		}
		System.out.println(ans);
	}
}
