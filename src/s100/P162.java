package s100;

import java.math.BigInteger;

public class P162 {
	static BigInteger[] T;

	static BigInteger f(int n, int z, int o, int a) {
		int k = n;
		k = k << 1 | a;
		k = k << 1 | o;
		k = k << 1 | z;
		if (T[k] != null) {
			return T[k];
		}
		if (n == 0) {
			return (z == 1 && o == 1 && a == 1) ? BigInteger.ONE : BigInteger.ZERO;
		}
		BigInteger ans = BigInteger.valueOf(13).multiply(f(n - 1, z, o, a));
		ans = ans.add(f(n - 1, 1, o, a));
		ans = ans.add(f(n - 1, z, 1, a));
		ans = ans.add(f(n - 1, z, o, 1));
		return T[k] = ans;
	}

	public static void main(String[] args) {
		T = new BigInteger[256];
		BigInteger ans = BigInteger.ZERO;
		for (int d = 1; d < 16; d++) {
			for (int m = 3; m <= 16; m++) {
				if (d == 1) {
					ans = ans.add(f(m - 1, 0, 1, 0));
				} else if (d == 0xa) {
					ans = ans.add(f(m - 1, 0, 0, 1));
				} else {
					ans = ans.add(f(m - 1, 0, 0, 0));
				}
			}
		}
		System.out.printf("%X\n", ans);
	}
}
