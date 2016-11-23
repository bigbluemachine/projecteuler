package s300;

import core.MathLib;
import core.NTLib;

public class P381 {
	public static void main(String[] args) {
		boolean[] prime = NTLib.simpleSieve(100000000);
		long ans = 0;

		for (int p = 5; p < 100000000; p++) {
			if (prime[p]) {
				long a = p - 1;
				a = (a * (p - 2)) % p;
				a = (a * (p - 3)) % p;
				a = (a * (p - 4)) % p;
				ans += (15 * MathLib.modExp(a, p - 2, p) - 1) % p;
			}
		}

		System.out.println(ans);
	}
}
