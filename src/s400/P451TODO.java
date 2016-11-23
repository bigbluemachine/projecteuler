package s400;

import java.util.LinkedList;

import core.NTLib;

public class P451TODO {
	public static void main(String[] args) {
		// TODO ~2min
		int max = 20000000;
		int interval = 30000;

		int[] M = new int[max + 1];
		for (int i = 1; i < max; i += interval) {
			LinkedList<Integer>[] pf = NTLib.batchFactor(i, interval + 2);
			for (int j = 0; j < interval; j++) {
				int m = i + j + 1;
				for (int x : pf[j]) {
					for (int y : pf[j + 2]) {
						long t = (long) x * y;
						if (t > max) {
							break;
						}
						if (m < t - 1) {
							M[(int) t] = m;
						}
					}
				}
			}
		}

		long ans = 0;
		for (int i = 3; i <= max; i++) {
			if (M[i] == 0) {
				ans++;
			} else {
				ans += M[i];
			}
		}
		System.out.println(ans);
	}
}
