package s200;

import java.math.BigInteger;

import core.BigRatio;
import core.MathLib;
import core.NTLib;

public class P281 {
	static int[] T;

	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	// A208183
	static Long f(int n, int k) {
		BigRatio r = new BigRatio(big(0), big(1));
		for (int d = 1; d <= n; d++) {
			if (n % d == 0) {
				BigInteger t = big(T[n / d]);
				BigInteger u = MathLib.fac(k * d);
				BigInteger v = MathLib.fac(d).pow(k);
				BigInteger w = big(k * n);
				t = t.multiply(u);
				v = v.multiply(w);
				r = r.add(new BigRatio(t, v));
			}
		}
		BigInteger ans = r.n;
		if (ans.compareTo(big(1000000000000000L)) > 0) {
			return null;
		}
		return ans.longValue();
	}

	public static void main(String[] args) {
		T = NTLib.totient(1000);
		long ans = 0;
		for (int n = 1;; n++) {
			boolean hasTerm = false;
			for (int m = 2;; m++) {
				Long f = f(n, m);
				if (f == null) {
					break;
				}
				hasTerm = true;
				ans += f;
			}
			if (!hasTerm) {
				break;
			}
		}
		System.out.println(ans);
	}
}
