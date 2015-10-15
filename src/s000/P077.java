package s000;

import core.NTLib;

public class P077 {
	public static void main(String[] args) {
		boolean[] P = NTLib.simpleSieve(10000);
		long[] T = new long[100000];
		T[0] = 1;
		for (int i = 2; i < 10000; i++) {
			if (!P[i]) {
				continue;
			}
			for (int j = i; j < 10000; j++) {
				T[j] += T[j - i];
			}
		}

		for (int i = 0; i < 10000; i++) {
			if (P[i]) {
				T[i]--;
			}
			if (T[i] > 5000) {
				System.out.println(i);
				break;
			}
		}
	}
}
