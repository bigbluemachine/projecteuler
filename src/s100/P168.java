package s100;

import java.math.BigInteger;

public class P168 {
	public static void main(String[] args) {
		long ans = 0;
		for (int d = 1; d <= 9; d++) {
			for (int m = 1; m <= 9; m++) {
				for (int e = 1; e < 100; e++) {
					BigInteger r = BigInteger.TEN.pow(e).subtract(BigInteger.valueOf(m));
					r = r.multiply(BigInteger.valueOf(d));
					BigInteger l = BigInteger.valueOf(10 * m - 1);
					if (r.mod(l).equals(BigInteger.ZERO)) {
						BigInteger x = r.divide(l);
						if (x.toString().length() == e) {
							BigInteger n = BigInteger.TEN.multiply(x).add(BigInteger.valueOf(d));
							ans += n.mod(BigInteger.valueOf(100000)).longValue();
						}
					}
				}
			}
		}
		System.out.println(ans % 100000);
	}
}
