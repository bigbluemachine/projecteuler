package s300;

import java.util.List;

import core.NTLib;

public class P313 {
	// f(a, b):
	// 5 + 8(a - 2) if a == b
	// 2a + 6b - 13 if a < b
	public static void main(String[] args) {
		List<Integer> ps = NTLib.primeList(1000000);
		ps.remove(0);
		long ans = 0;
		for (int p : ps) {
			long k = (long) p * p + 13;
			long minB = k / 8 + 1;
			long maxB = (k - 4) / 6;
			ans += maxB - minB + 1;
		}
		System.out.println(2 * ans);
	}
}
