package s100;

import java.math.BigInteger;

public class P164 {
	public static void main(String[] args) {
		BigInteger ans = BigInteger.ZERO;
		for (int a = 1; a <= 9; a++) {
			for (int b = 0; a + b <= 9; b++) {
				ans = ans.add(f(a, b, 18));
			}
		}
		System.out.println(ans);
	}

	static BigInteger[][][] T = new BigInteger[10][10][19];

	static BigInteger f(int a, int b, int c) {
		if (T[a][b][c] == null) {
			if (c == 0) {
				T[a][b][c] = BigInteger.ONE;
			} else {
				BigInteger ans = BigInteger.ZERO;
				for (int d = 0; a + b + d <= 9; d++) {
					ans = ans.add(f(b, d, c - 1));
				}
				T[a][b][c] = ans;
			}
		}
		return T[a][b][c];
	}
}
