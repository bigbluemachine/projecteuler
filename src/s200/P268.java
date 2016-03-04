package s200;

import java.util.List;

import core.NTLib;

public class P268 {
	public static void main(String[] args) {
		List<Integer> p = NTLib.primeList(100);
		int L = p.size(), U = 1 << L;
		Integer[] P = p.toArray(new Integer[L]);
		long MAX = 10000000000000000L - 1;

		long ans = 0;
		x: for (int i = 0; i < U; i++) {
			int c = 0, v = i;
			for (; v > 0; c++)
				v &= v - 1;

			if (c >= 4 && c <= 14) {
				long d = 1;
				for (int j = 0; j < L; j++)
					if ((i & (1 << j)) > 0) {
						d *= P[j];
						if (d > MAX)
							continue x;
					}

				int m = ((c - 1) * (c - 2) * (c - 3)) / 6;
				ans += (MAX / d) * (c % 2 > 0 ? -m : m);
			}
		}

		System.out.println(ans);
	}
}
