package s300;

import java.math.BigInteger;
import java.util.List;
import java.util.TreeSet;

import core.DeferredCall;
import core.DeferredCall.Call;
import core.NTLib;
import core.PFac;

public class P342 {
	static long MAX;
	static List<Integer> P;
	static TreeSet<Long> S = new TreeSet<Long>();
	static DeferredCall calls = new DeferredCall();

	static class F implements Call {
		PFac n;
		PFac r;
		int i;

		public F(PFac n, PFac r, int i) {
			this.n = n;
			this.r = r;
			this.i = i;
		}

		public void call() {
			f(n, r, i);
		}
	}

	static boolean tooBig(PFac pf) {
		return pf.bigValue().compareTo(BigInteger.valueOf(MAX)) > 0;
	}

	static void f(PFac n, PFac r, int i) {
		if (r.twos % 3 == 0) {
			boolean bad = false;
			for (long p : r.M.keySet()) {
				if (r.M.get(p) % 3 > 0) {
					bad = true;
					break;
				}
			}
			if (!bad) {
				S.add(n.longValue());
			}
		}

		if (i > 0) {
			int p = P.get(i - 1);
			for (int e = 1;; e++) {
				PFac a = new PFac();
				a.multiply(p, e);
				PFac n_ = n.multiply(a);
				if (tooBig(n_)) {
					break;
				}
				PFac b = PFac.make(p - 1, P);
				b.multiply(p, 2 * e - 1);
				PFac r_ = r.multiply(b);
				Integer e_ = r_.M.get((long) p);
				if (e_ != null) {
					if (e_ % 3 > 0) {
						continue;
					}
					r_.M.remove((long) p);
				}
				calls.add(new F(n_, r_, i - 1));
			}
			calls.add(new F(n, r, i - 1));
		}
	}

	public static void main(String[] args) {
		MAX = 10000000000L;
		P = NTLib.primeList((int) Math.sqrt(MAX) + 1);

		for (int i = 0; i < P.size(); i++) {
			int p = P.get(i);
			for (int e = 2;; e += 3) {
				PFac n = new PFac();
				n.multiply(p, e);
				if (tooBig(n)) {
					break;
				}
				PFac r = PFac.make(p - 1, P);
				calls.add(new F(n, r, i));
			}
		}

		while (!calls.isEmpty()) {
			calls.call();
		}

		long ans = 0;
		for (long s : S) {
			ans += s;
		}
		System.out.println(ans);
	}
}
