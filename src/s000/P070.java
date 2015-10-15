package s000;

import core.NTLib;

public class P070 {
	public static void main(String[] args) {
		int m = 10000000;
		int[] t = NTLib.totient(m + 1);
		int n = m, d = 1;
		for (int i = 2; i < m; i++) {
			if (isPerm(i, t[i])) {
				long a = (long) i * d;
				long b = (long) n * t[i];
				if (a < b) {
					n = i;
					d = t[i];
				}
			}
		}
		System.out.println(n);
	}

	static boolean isPerm(int a, int b) {
		int[] v = new int[10];
		while (a > 0) {
			v[a % 10]++;
			a /= 10;
		}
		while (b > 0) {
			v[b % 10]--;
			b /= 10;
		}
		for (int i : v) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
