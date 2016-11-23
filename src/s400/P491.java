package s400;

import java.math.BigInteger;
import java.util.Arrays;

public class P491 {
	public static void main(String[] args) {
		int[] v = new int[10];
		Arrays.fill(v, 2);
		BigInteger ans = BigInteger.ZERO;
		for (int i = 1; i < 10; i++) {
			v[i]--;
			ans = ans.add(f(v, i, 1));
			v[i]++;
		}
		System.out.println(ans);
	}

	static BigInteger[][][] F = new BigInteger[59049][11][2];

	static BigInteger f(int[] v, int m, int s) {
		int e = encode(v);
		if (F[e][m][s] != null) {
			return F[e][m][s];
		}
		BigInteger ans;
		if (e == 0) {
			if (m == 0) {
				ans = BigInteger.ONE;
			} else {
				ans = BigInteger.ZERO;
			}
		} else {
			ans = BigInteger.ZERO;
			for (int i = 0; i < 10; i++) {
				if (v[i] == 0) {
					continue;
				}
				v[i]--;
				int n = s == 0 ? m + i : m - i;
				ans = ans.add(f(v, (n + 11) % 11, 1 - s));
				v[i]++;
			}
		}
		return F[e][m][s] = ans;
	}

	static int encode(int[] v) {
		int x = 0;
		for (int i : v) {
			x = 3 * x + i;
		}
		return x;
	}
}
