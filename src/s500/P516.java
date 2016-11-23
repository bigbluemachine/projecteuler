package s500;

import java.util.TreeSet;

import core.NTLib;

public class P516 {
	public static void main(String[] args) {
		long max = 1000000000000L;

		TreeSet<Long> S = new TreeSet<Long>();
		TreeSet<Long> T = new TreeSet<Long>();
		for (long a = 1; a <= max; a *= 2) {
			for (long b = 1; b <= max; b *= 3) {
				long p = a * b;
				if (p > max) {
					break;
				}
				for (long c = 1; c <= max; c *= 5) {
					long q = p * c;
					if (q > max) {
						break;
					}
					S.add(q);
					if (5 < q && q + 1 <= max && q % 2 == 0 && NTLib.MillerRabin(q + 1, 10)) {
						T.add(q + 1);
					}
				}
			}
		}

		TreeSet<Long> U = new TreeSet<Long>();
		for (long t : T) {
			TreeSet<Long> V = new TreeSet<Long>();
			for (long u : U) {
				if (t * u > max) {
					break;
				}
				V.add(t * u);
			}
			U.addAll(V);
			U.add(t);
		}

		long ans = 0;
		for (long u : U) {
			for (long s : S.subSet(1L, true, max / u, true)) {
				ans = (ans + s * u) % (1L << 32);
			}
		}
		for (long s : S) {
			ans = (ans + s) % (1L << 32);
		}
		System.out.println(ans);
	}
}
