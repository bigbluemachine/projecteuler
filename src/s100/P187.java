package s100;

import core.NTLib;

public class P187 {
	public static void main(String[] args) {
		long[] L = NTLib.bitSieve(100000000);
		int[] a = new int[5761455];
		a[0] = 2;
		for (int i = 3, j = 1; i < 100000000; i += 2) {
			if (NTLib.isPrime(L, i)) {
				a[j++] = i;
			}
		}

		int ans = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				if ((long) a[i] * a[j] >= 100000000) {
					break;
				}
				ans++;
			}
		}
		System.out.println(ans);
	}
}
