package s000;

import core.NTLib;

public class P069 {
	public static void main(String[] args) {
		double best = 0;
		int ans = 0;
		int[] T = NTLib.totient(1000001);
		for (int i = 2; i <= 1000000; i++) {
			double t = (double) i / T[i];
			if (t > best) {
				best = t;
				ans = i;
			}
		}
		System.out.println(ans);
	}
}
