package s100;

import java.math.BigInteger;

import core.BigRatio;

public class P121 {
	static int ROUNDS = 15;
	static BigRatio[][][] F = new BigRatio[ROUNDS + 1][ROUNDS + 1][ROUNDS + 1];

	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static BigRatio f(int b, int r, int n) {
		if (F[b][r][n] != null) {
			return F[b][r][n];
		}
		BigRatio ans;
		if (n == 0) {
			if (b > r) {
				ans = new BigRatio(big(1), big(1));
			} else {
				ans = new BigRatio(big(0), big(1));
			}
		} else {
			int red = ROUNDS - n + 1;
			ans = new BigRatio(big(1), big(red + 1)).mul(f(b + 1, r, n - 1));
			ans = ans.add(new BigRatio(big(red), big(red + 1)).mul(f(b, r + 1, n - 1)));
		}
		return F[b][r][n] = ans;
	}

	public static void main(String[] args) {
		BigRatio m = f(0, 0, ROUNDS);
		System.out.println(m.d.divide(m.n));
	}
}
