package s200;

import java.math.BigInteger;

public class P239 {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	static BigInteger[][] T = new BigInteger[100][100];
	static BigInteger[] F = new BigInteger[101];

	static double f(int tot, int spc, int cor) {
		BigInteger n = F[spc].multiply(F[tot - spc]).multiply(T[spc - cor][tot - spc]);
		BigInteger d = F[cor].multiply(F[spc - cor]).multiply(F[tot]);
		return n.doubleValue() / d.doubleValue();
	}

	public static void main(String[] args) {
		F[0] = big(1);
		for (int i = 1; i <= 100; i++)
			F[i] = F[i - 1].multiply(big(i));

		T[0][0] = big(1);
		for (int c = 0; c < 100; c++)
			T[1][c] = big(c);

		for (int r = 2; r < 100; r++) {
			T[r][0] = big(r - 1).multiply(T[r - 1][0].add(T[r - 2][0]));
			for (int c = 1; c < 100; c++)
				T[r][c] = T[r][c - 1].add(big(r).multiply(T[r - 1][c]));
		}

		System.out.printf("%.13f\n", f(100, 25, 3));
	}
}
