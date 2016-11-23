package s300;

import java.math.BigInteger;

public class P359 {
	static BigInteger _(long n) {
		return BigInteger.valueOf(n);
	}

	static long p(long r, long c) {
		r--;
		if (r != 0) {
			c--;
		}

		BigInteger f = _(2 * ((r + 1) / 2)).multiply(_((r / 2 + 1)));
		long a = r % 2 == 0 ? 1 : 2 * r + 3;
		long b = r % 2 == 0 ? 2 * r + 2 : 2;
		long p = (c + 1) / 2;
		long q = c / 2;
		BigInteger x = _(p).multiply(_(a + p - 1));
		BigInteger y = _(q).multiply(_(b + q - 1));
		return f.add(x).add(y).mod(_(100000000)).longValue();
	}

	public static void main(String[] args) {
		long M = 71328803586048L;
		long ans = 0;
		for (long i = 1; i * i <= M; i++) {
			if (M % i == 0) {
				long j = M / i;
				ans += p(i, j);
				if (i != j) {
					ans += p(j, i);
				}
			}
		}
		System.out.println(ans % 100000000);
	}
}
