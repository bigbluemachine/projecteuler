package s500;

import java.util.ArrayList;

import core.MathLib;
import core.NTLib;
import core.PFac;

public class P555 {
	public static void main(String[] args) {
		int p = 1000000;
		int m = 1000000;

		ArrayList<Integer> P = NTLib.primeList((int) Math.sqrt(p) + 1);
		long ans = 0;
		for (int s = 1; s <= p; s++) {
			PFac pf = PFac.make(s, P);
			for (long q : pf.divisors()) {
				long k = q + s;
				if (k > p) {
					break;
				}
				ans += MathLib.tri64(q - 1) + q * (m + 1 - s);
			}
		}
		System.out.println(ans);
	}
}
