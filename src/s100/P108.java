package s100;

import java.util.List;

import core.NTLib;
import core.PFac;

public class P108 {
	public static void main(String[] args) {
		List<Integer> P = NTLib.primeList(1000);
		for (int i = 100;; i++) {
			PFac pf = PFac.make(i, P);
			PFac pf2 = pf.multiply(pf);
			long d = (pf2.divisorCount() - 1) / 2 + 1;
			if (d > 1000) {
				System.out.println(i);
				break;
			}
		}
	}
}
