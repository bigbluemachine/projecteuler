package s400;

import java.util.LinkedList;

import core.NTLib;

public class P407 {
	public static void main(String[] args) {
		int max = 10000000;
		int interval = 30000;

		int[] M = new int[max + 1];
		LinkedList<Integer> prev = new LinkedList<Integer>();
		for (int i = 1; i < max; i += interval) {
			LinkedList<Integer>[] pf = NTLib.batchFactor(i, Math.min(interval, max - i + 1));
			for (int j = 0; j < pf.length; j++) {
				int a = i + j;
				for (int x : prev) {
					for (int y : pf[j]) {
						long t = (long) x * y;
						if (t > max) {
							break;
						}
						if (a < t) {
							M[(int) t] = a;
						}
					}
				}
				prev = pf[j];
			}
		}

		long ans = 0;
		for (int i = 2; i <= max; i++) {
			if (M[i] == 0) {
				ans++;
			} else {
				ans += M[i];
			}
		}
		System.out.println(ans);
	}
}
