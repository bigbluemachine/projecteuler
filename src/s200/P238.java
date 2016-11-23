package s200;

import java.math.BigInteger;

public class P238 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	public static void main(String[] args) {
		StringBuilder str = new StringBuilder();
		long x = 14025256;
		do {
			str.append(x);
		} while ((x = (x * x) % 20300713) != 14025256);

		int[] w = new int[str.length()];
		int sum = 0;
		for (int i = 0; i < w.length; i++) {
			w[i] = str.charAt(i) - '0';
			sum += w[i];
		}

		int[] p = new int[sum + 1];
		int count = 0;
		for (int i = 0;; i++) {
			int s = 0;
			for (int j = 0; j < w.length; j++) {
				s += w[(i + j) % w.length];
				if (p[s] == 0) {
					p[s] = i + 1;
					count++;
				}
			}
			if (count == p.length) {
				break;
			}
		}

		long max = 2000000000000000L;
		BigInteger ans = BigInteger.ZERO;
		for (int k = 1; k < p.length; k++) {
			long freq = (max - k) / sum + 1;
			ans = ans.add(big(freq).multiply(big(p[k])));
		}
		System.out.println(ans);
	}
}
