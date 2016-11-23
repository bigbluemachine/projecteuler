package s300;

import java.math.BigInteger;

import core.NTLib;

public class P329 {
	static String S = "PPPPNNPPPNPPNPN";
	static boolean[] P = NTLib.simpleSieve(501);
	static BigInteger[][] N = new BigInteger[501][16];
	static BigInteger[][] D = new BigInteger[501][16];

	static void f(int n, int i) {
		if (N[n][i] != null) {
			return;
		}

		if (i == S.length()) {
			N[n][i] = BigInteger.ONE;
			D[n][i] = BigInteger.ONE;
			return;
		}

		BigInteger u = BigInteger.ZERO;
		BigInteger v = BigInteger.ONE;
		BigInteger x;
		BigInteger y;

		int d = 0;
		if (n > 1) {
			d++;
			f(n - 1, i + 1);
			x = N[n - 1][i + 1];
			y = D[n - 1][i + 1];
			u = u.multiply(y).add(x.multiply(v));
			v = v.multiply(y);
		}
		if (n < 500) {
			d++;
			f(n + 1, i + 1);
			x = N[n + 1][i + 1];
			y = D[n + 1][i + 1];
			u = u.multiply(y).add(x.multiply(v));
			v = v.multiply(y);
		}

		v = v.multiply(BigInteger.valueOf(d));
		if (P[n] == (S.charAt(i) == 'P')) {
			u = u.multiply(BigInteger.valueOf(2));
		}
		v = v.multiply(BigInteger.valueOf(3));

		BigInteger g = u.gcd(v);
		N[n][i] = u.divide(g);
		D[n][i] = v.divide(g);
	}

	public static void main(String[] args) {
		BigInteger u = BigInteger.valueOf(0);
		BigInteger v = BigInteger.valueOf(1);
		BigInteger x;
		BigInteger y;

		for (int i = 1; i <= 500; i++) {
			f(i, 0);
			x = N[i][0];
			y = D[i][0];
			u = u.multiply(y).add(x.multiply(v));
			v = v.multiply(y);
		}

		v = v.multiply(BigInteger.valueOf(500));
		BigInteger g = u.gcd(v);
		u = u.divide(g);
		v = v.divide(g);

		System.out.printf("%s/%s\n", u, v);
	}
}
