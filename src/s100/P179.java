package s100;

import core.NTLib;

public class P179 {
	public static void main(String[] args) {
		int[] d = NTLib.divisor(10000001, false);
		int ans = 0;
		for (int n = 2; n < 10000000; n++) {
			if (d[n] == d[n + 1]) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
