package s000;

import java.util.TreeSet;

// Ugly.
public class P045 {
	public static void main(String[] args) {
		long t = 2;
		long p = 2;
		long h = 2;
		TreeSet<Long> T = new TreeSet<Long>();
		TreeSet<Long> P = new TreeSet<Long>();
		TreeSet<Long> H = new TreeSet<Long>();
		T.add(1L);
		P.add(1L);
		H.add(1L);
		long ans = 0;
		L: for (long i = 1000000;; i += 1000000) {
			while (T.last() < i) {
				T.add((t * (t + 1)) / 2);
				t++;
			}
			while (P.last() < i) {
				P.add((p * (3 * p - 1)) / 2);
				p++;
			}
			while (H.last() < i) {
				long x = h * (2 * h - 1);
				if (T.contains(x) && P.contains(x)) {
					ans = x;
					if (ans > 40755) {
						break L;
					}
				}
				H.add(x);
				h++;
			}
		}
		System.out.println(ans);
	}
}
