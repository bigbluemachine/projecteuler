package s000;

import core.Ratio;

public class P071 {
	public static void main(String[] args) {
		Ratio ans = new Ratio(0, 1);
		for (int d = 2; d <= 1000000; d++) {
			int t = 3 * d;
			int n = t % 7 > 0 ? t / 7 : t / 7 - 1;
			Ratio r = new Ratio(n, d);
			if (r.compareTo(ans) > 0) {
				ans = r;
			}
		}
		System.out.println(ans);
	}
}
