package s100;

import java.math.BigInteger;

public class P113 {
	static BigInteger inc(int l, int s, BigInteger[][] F) {
		if (F[l][s] != null) {
			return F[l][s];
		}

		BigInteger ans;
		if (l == 1) {
			ans = BigInteger.ONE;
		} else {
			ans = BigInteger.ZERO;
			for (int d = s; d <= 9; d++) {
				ans = ans.add(inc(l - 1, d, F));
			}
		}

		return F[l][s] = ans;
	}

	public static void main(String[] args) {
		int L = 100;
		BigInteger[][] F = new BigInteger[L + 1][10];
		BigInteger ans = BigInteger.ZERO;
		for (int l = 1; l <= L; l++) {
			BigInteger t = BigInteger.ZERO;
			for (int s = 1; s <= 9; s++) {
				t = t.add(inc(l, s, F));
			}
			t = t.multiply(BigInteger.valueOf(2));
			ans = ans.add(inc(l, 0, F)).add(t);
			ans = ans.subtract(BigInteger.valueOf(10));
		}
		System.out.println(ans);
	}
}
