package s300;

import java.util.List;
import java.util.TreeSet;

import core.MathLib;
import core.NTLib;
import core.PFac;

public class P358 {
	public static void main(String[] args) {
		long M = 99999999999L;
		long a = (long) Math.floor(M / 138.0);
		long b = (long) Math.ceil(M / 137.0);

		List<Integer> ps = NTLib.primeList((int) Math.sqrt(b) + 1);
		L: for (long P = a; P <= b; P++) {
			if ((P * 56789) % 100000 == 99999) {
				long T = P - 1;
				if (MathLib.modExp(10, T, P) == 1) {
					PFac pf = PFac.make(T, ps);
					TreeSet<Long> divisors = pf.divisors();
					for (Long d : divisors)
						if (MathLib.modExp(10, d, P) == 1 && d != T)
							continue L;
					System.out.println((T / 2) * 9);
				}
			}
		}
	}
}
