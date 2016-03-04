package s200;

import java.util.List;
import java.util.TreeSet;

import core.NTLib;

public class P234 {
	static void lps(long a, long b, TreeSet<Long> S, long Z) {
		for (long i = a * a + a; i < b * b; i += a) {
			if (i > Z) {
				break;
			}
			S.add(i);
		}
	}

	static void ups(long a, long b, TreeSet<Long> S, long Z) {
		for (long i = b * b - b; i > a * a; i -= b) {
			if (i > Z) {
				continue;
			}
			S.add(i);
		}
	}

	public static void main(String[] args) {
		long Z = 999966663333L;
		int s = (int) Math.sqrt(Z);

		TreeSet<Long> L = new TreeSet<Long>();
		TreeSet<Long> U = new TreeSet<Long>();

		List<Integer> ps = NTLib.primeList(2 * s);
		for (int i = 1; i < ps.size(); i++) {
			lps(ps.get(i - 1), ps.get(i), L, Z);
			ups(ps.get(i - 1), ps.get(i), U, Z);
		}

		TreeSet<Long> S = new TreeSet<Long>();
		for (long i : L) {
			if (!U.contains(i)) {
				S.add(i);
			}
		}
		for (long i : U) {
			if (!L.contains(i)) {
				S.add(i);
			}
		}

		long ans = 0;
		for (long i : S) {
			if (i > Z) {
				break;
			}
			ans += i;
		}
		System.out.println(ans);
		System.out.println(S.size());
	}
}
