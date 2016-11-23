package s300;

import java.util.TreeSet;

public class P348 {
	static TreeSet<Long> S = new TreeSet<Long>();

	static void process(long x) {
		int t = 0;
		for (long c = 1; c * c * c < x; c++) {
			long c3 = c * c * c;
			long s2 = x - c3;
			long s = (long) Math.sqrt(s2);
			if (s * s == s2) {
				t++;
			}
		}
		if (t == 4) {
			S.add(x);
		}
	}

	static void search(String s, int n) {
		if (s.length() > 0 && s.charAt(0) != '0') {
			process(Long.parseLong(s));
		}
		if (s.length() + 2 <= n) {
			for (int d = 0; d <= 9; d++) {
				search(d + s + d, n);
			}
		}
	}

	public static void main(String[] args) {
		for (int n = 1; S.size() < 5; n++) {
			search("", n);
			for (int d = 0; d <= 9; d++) {
				search("" + d, n);
			}
		}

		long ans = 0;
		int i = 0;
		for (long x : S) {
			ans += x;
			if (++i == 5) {
				break;
			}
		}
		System.out.println(ans);
	}
}
