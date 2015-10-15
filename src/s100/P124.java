package s100;

import java.util.Arrays;
import java.util.List;

import core.NTLib;
import core.PFac;

public class P124 {
	static class Q implements Comparable<Q> {
		int n, r;

		public Q(int n, int r) {
			this.n = n;
			this.r = r;
		}

		@Override
		public int compareTo(Q q) {
			if (r > q.r) {
				return 1;
			}
			if (r < q.r) {
				return -1;
			}
			return Integer.compare(n, q.n);
		}

		public String toString() {
			return n + " " + r;
		}
	}

	public static void main(String[] args) {
		List<Integer> ps = NTLib.primeList((int) (Math.sqrt(100000)) + 1);
		Q[] qs = new Q[100000];
		for (int i = 1; i <= 100000; i++) {
			PFac pf = PFac.make(i, ps);
			int r = pf.twos > 0 ? 2 : 1;
			for (int p : pf.M.keySet()) {
				r *= p;
			}
			qs[i - 1] = new Q(i, r);
		}
		Arrays.sort(qs);

		System.out.println(qs[9999].n);
	}
}
