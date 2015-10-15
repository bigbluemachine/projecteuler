package s100;

import core.NTLib;

public class P133 {
	static int z(int n) {
		int x = 1;
		int j = 0;
		int ans = 0;
		do {
			j = (j + x) % n;
			x = (10 * x) % n;
			ans++;
		} while (j != 0);
		return ans;
	}

	static boolean factorOfTens(int n) {
		while (n % 2 == 0) {
			n /= 2;
		}
		while (n % 5 == 0) {
			n /= 5;
		}
		return n == 1;
	}

	public static void main(String[] args) {
		boolean[] P = NTLib.simpleSieve(100000);
		for (int i = 3; i < 100000; i += i % 10 == 3 ? 4 : 2) {
			if (!P[i]) {
				continue;
			}
			int z = z(i);
			if (factorOfTens(z)) {
				P[i] = false;
			}
		}

		long ans = 0;
		for (int i = 2; i < 100000; i++) {
			if (P[i]) {
				ans += i;
			}
		}
		System.out.println(ans);
	}
}
