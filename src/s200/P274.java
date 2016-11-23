package s200;

import java.util.List;

import core.EEA;
import core.MathLib;
import core.NTLib;

public class P274 {
	public static void main(String[] args) {
		long ans = 0;
		List<Integer> ps = NTLib.primeList(10000000);
		for (int p : ps) {
			if (MathLib.gcd32(10, p) == 1) {
				ans += EEA.inv(10, p).longValue();
			}
		}
		System.out.println(ans);
	}
}
