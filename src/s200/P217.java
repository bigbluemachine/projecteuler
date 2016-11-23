package s200;

import java.math.BigInteger;

public class P217 {
	static BigInteger[][][] T = new BigInteger[24][208][4];

	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static BigInteger f(int n, int s, boolean count, boolean leading0) {
		int x = count ? 1 : 0;
		int y = leading0 ? 1 : 0;
		int z = 2 * x + y;
		if (T[n][s][z] != null) {
			return T[n][s][z];
		}

		if (s == 0) {
			return T[n][s][z] = (count ? big(1) : big(0));
		}
		if (n == 1) {
			return T[n][s][z] = (count ? big(1) : big(s));
		}

		BigInteger ans = big(0);
		int minD = Math.max(leading0 ? 0 : 1, s - 9 * (n - 1));
		int maxD = Math.min(9, s);
		if (count) {
			for (int d = minD; d <= maxD; d++) {
				ans = ans.add(f(n - 1, s - d, count, true));
			}
		} else {
			BigInteger p = big(10).pow(n - 1);
			for (int d = minD; d <= maxD; d++) {
				BigInteger rs = f(n - 1, s - d, false, true);
				BigInteger rc = f(n - 1, s - d, true, true);
				ans = ans.add(p.multiply(big(d)).multiply(rc).add(rs));
			}
		}
		return T[n][s][z] = ans;
	}

	static BigInteger g(int d) {
		if (d == 1) {
			return big(45);
		}

		int h = d / 2;
		BigInteger p = big(10).pow(h);
		BigInteger ans = big(0);
		for (int s = 1; s <= 9 * h; s++) {
			BigInteger ls = f(h, s, false, false);
			BigInteger rs = f(h, s, false, true);
			BigInteger lc = f(h, s, true, false);
			BigInteger rc = f(h, s, true, true);
			BigInteger lcrs = lc.multiply(rs);
			if (d % 2 == 0) {
				ans = ans.add(rc.multiply(ls).multiply(p).add(lcrs));
			} else {
				for (int m = 0; m <= 9; m++) {
					ans = ans.add(rc.multiply(ls.multiply(big(10)).add(lc.multiply(big(m)))).multiply(p).add(lcrs));
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		BigInteger ans = big(0);
		for (int i = 1; i <= 47; i++) {
			ans = ans.add(g(i));
		}
		System.out.println(ans.mod(big(3).pow(15)));
	}
}
