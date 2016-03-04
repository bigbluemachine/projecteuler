package s200;

import core.MathLib;
import core.Ratio;

public class P202TODO {
	static final Ratio ONE = new Ratio(1, 1);
	static int COUNT, EDGE;

	// Simulation; kept for posterity
	static void f(int p, int q) {
		Ratio a = ONE;
		Ratio b = new Ratio(p, q);
		int e = 0;
		int d = 2 * p > q ? 1 : -1;
		int n = 0;
		for (;;) {
			n++;
			Ratio b2 = ONE.sub(b);
			Ratio c = a.mul(b2).div(b);
			if (c.compareTo(ONE) > 0) {
				c = b.mul(a).div(a.sub(b));
				b2 = b;
				d = -d;
			}
			a = b2;
			b = c;
			if (b.compareTo(ONE) == 0) {
				break;
			}
			e = (e + d + 3) % 3;
		}
		COUNT = n;
		EDGE = e;
	}

	public static void main(String[] args) {
		// TODO ~5 minutes
		long N = 12017639147L;
		long q = (N + 3) / 2;
		// q = 5^2*11^1*17^1*23^1*29^1*41^1*47^1

		long ans = 0;
		for (long p = 3 - (q % 3); p + p < q; p += 3) {
			if (p % 5 == 0 || p % 11 == 0 || p % 17 == 0 || p % 23 == 0) {
				continue;
			}
			if (MathLib.gcd64(p, q) == 1) {
				ans++;
			}
		}
		System.out.println(2 * ans);
	}
}
