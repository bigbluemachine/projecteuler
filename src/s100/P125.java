package s100;

import java.util.TreeSet;

public class P125 {
	static boolean pal(long n) {
		String s = Long.toString(n);
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	static long P(long n) {
		return (n * (n + 1) * (2 * n + 1)) / 6;
	}

	public static void main(String[] args) {
		TreeSet<Long> S = new TreeSet<Long>();
		for (int b = 10000; b >= 2; b--) {
			for (int a = b - 2; a >= 0; a--) {
				long t = P(b) - P(a);
				if (t < 100000000 && pal(t)) {
					S.add(t);
				}
			}
		}

		long ans = 0;
		for (long s : S) {
			ans += s;
		}
		System.out.println(ans);
	}
}
