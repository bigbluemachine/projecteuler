package s000;

import java.util.TreeSet;

import core.NTLib;

public class P095 {
	public static void main(String[] args) {
		int[] d = NTLib.divisor(1000001, true);
		int best = 0;
		int ans = -1;
		for (int i = 2; i <= 1000000; i++) {
			int x = i;
			TreeSet<Integer> S = new TreeSet<Integer>();
			while (true) {
				S.add(x);
				if (d[x] > 1000000) {
					break;
				}
				x = d[x];
				if (S.contains(x)) {
					int c = 1;
					int min = x;
					for (int y = d[x]; y != x; y = d[y]) {
						c++;
						min = Math.min(x, y);
					}
					if (c > best) {
						best = c;
						ans = min;
					}
					break;
				}
			}
		}
		System.out.println(ans);
	}
}
