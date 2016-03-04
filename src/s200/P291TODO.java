package s200;

import core.NTLib;

public class P291TODO {
	public static void main(String[] args) {
		// TODO SLOW!
		// Primes of the form 2a^2 + 2a + 1
		long ans = 0;
		long n = 5;
		for (int a = 2;; a++) {
			if (n >= 5000000000000000L) {
				break;
			}
			if (NTLib.MillerRabin(n, 5)) {
				ans++;
			}
			n += 4 * a;
		}
		System.out.println(ans);
	}
}
