package s200;

import java.util.List;
import java.util.TreeSet;

import core.MathLib;
import core.NTLib;
import core.PFac;

public class P203 {
	public static void main(String[] args) {
		List<Integer> ps = NTLib.primeList(10);
		PFac[] pf = new PFac[52];
		for (int i = 1; i <= 51; i++) {
			pf[i] = PFac.make(i, ps);
		}

		TreeSet<Long> S = new TreeSet<Long>();
		for (int r = 2; r < 51; r++) {
			int[] v = new int[51];
			for (int d = 1, m = r; d < m; d++, m--) {
				PFac mul = pf[m];
				PFac div = pf[d];
				v[2] += mul.twos - div.twos;
				for (long p : mul.M.keySet()) {
					v[(int) p] += mul.M.get(p);
				}
				for (long p : div.M.keySet()) {
					v[(int) p] -= div.M.get(p);
				}
				long prod = 1;
				boolean sqf = true;
				for (int p = 2; p < 51; p++) {
					if (v[p] > 1) {
						sqf = false;
						break;
					}
					if (v[p] > 0) {
						prod *= MathLib.pow32(p, v[p]);
					}
				}
				if (sqf) {
					S.add(prod);
				}
			}
		}

		long ans = 1;
		for (long s : S) {
			ans += s;
		}
		System.out.println(ans);
	}
}
