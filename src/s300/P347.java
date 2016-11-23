package s300;

import java.util.List;

import core.NTLib;

public class P347 {
	static long f(long p, long q, long M) {
		long t = p * q;
		while (t * q <= M) {
			t *= q;
		}
		long ans = 0;
		while (t % q == 0) {
			while (t * p <= M) {
				t *= p;
			}
			if (t % q > 0) {
				break;
			}
			ans = Math.max(ans, t);
			t /= q;
		}
		return ans;
	}

	public static void main(String[] args) {
		int M = 10000000;
		List<Integer> P = NTLib.primeList(M / 2);
		long ans = 0;
		for (int i = 0; i < P.size(); i++) {
			int p = P.get(i);
			for (int j = i + 1; j < P.size(); j++) {
				int q = P.get(j);
				if ((long) p * q > M) {
					break;
				}
				ans += f(p, q, M);
			}
		}
		System.out.println(ans);
	}
}
