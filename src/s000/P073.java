package s000;

import core.MathLib;

public class P073 {
	public static void main(String[] args) {
		int ans = 0;
		for (int d = 2; d <= 12000; d++) {
			int n = d / 2;
			if (d % 2 == 0) {
				n--;
			}
			while (3 * n > d) {
				if (MathLib.gcd32(n, d) == 1) {
					ans++;
				}
				n--;
			}
		}
		System.out.println(ans);
	}
}
