package s100;

import java.util.List;

import core.EEA;
import core.NTLib;

public class P134 {
	static long f(int p1, int p2) {
		long m = 10;
		while (m < p1) {
			m *= 10;
		}
		long v = EEA.inv(m, p2).longValue();
		long q = (v * (p2 - p1)) % p2;
		return m * q + p1;
	}

	public static void main(String[] args) {
		List<Integer> P = NTLib.primeList(1010000);
		long ans = 0;
		for (int i = 0; i < P.size(); i++) {
			int p1 = P.get(i);
			if (p1 < 5) {
				continue;
			}
			if (p1 > 1000000) {
				break;
			}
			int p2 = P.get(i + 1);
			ans += f(p1, p2);
		}
		System.out.println(ans);
	}
}
