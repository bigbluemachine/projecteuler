package s200;

import java.util.List;

import core.NTLib;
import core.PFac;

public class P243 {
	static List<Integer> ps = NTLib.primeList(50);
	static long best = 1000000000000000L;

	static void gen(PFac pf, int i) {
		long x = pf.longValue();
		if (x > best) {
			return;
		}

		double r = (double) pf.totient().longValue() / (x - 1);
		if (r < 15499.0 / 94744.0) {
			best = x;
		}

		for (int j = i; j < ps.size(); j++) {
			PFac pf2 = new PFac();
			pf2.twos = pf.twos;
			pf2.M.putAll(pf.M);
			pf2.multiply(ps.get(j), 1);
			gen(pf2, j);
		}
	}

	public static void main(String[] args) {
		gen(new PFac(), 0);
		System.out.println(best);
	}
}
