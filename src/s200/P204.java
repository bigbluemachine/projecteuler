package s200;

import java.util.List;

import core.NTLib;

public class P204 {
	static int ham(long n, int[] ps, int i, long u) {
		int ans = 1;
		for (int j = i; j < ps.length; j++) {
			long m = n * ps[j];
			if (m > u) {
				break;
			}
			ans += ham(m, ps, j, u);
		}
		return ans;
	}

	public static void main(String[] args) {
		int z = 100;
		int u = 1000000000;

		List<Integer> ps = NTLib.primeList(z + 1);
		int[] a = new int[ps.size()];
		int i = 0;
		for (int p : ps) {
			a[i++] = p;
		}

		System.out.println(ham(1, a, 0, u));
	}
}
