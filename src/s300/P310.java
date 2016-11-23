package s300;

import java.util.TreeSet;

public class P310 {
	static int mex(TreeSet<Integer> S) {
		int i = 0;
		for (; i <= S.last(); i++)
			if (!S.contains(i))
				return i;
		return i;
	}

	public static void main(String[] args) {
		int U = 100000;
		int[] F = new int[U + 1];
		F[0] = 0;

		int max = 0;
		for (int i = 1; i <= U; i++) {
			TreeSet<Integer> S = new TreeSet<Integer>();
			for (int j = 1; j * j <= i; j++)
				S.add(F[i - j * j]);
			F[i] = mex(S);
			max = Math.max(max, F[i]);
		}

		int[] V = new int[max + 1];
		for (int i = 0; i <= U; i++)
			V[F[i]]++;

		long count = 0;
		for (int k = 0; k <= max; k++) {
			long r = V[k];
			for (int j = 0; j <= k; j++) {
				long q = V[j];
				for (int i = 0, t = k ^ j; i <= j; i++) {
					if ((t ^ i) == 0) {
						long p = V[i];

						if (i == j) {
							if (j == k)
								count += (p * (p + 1) * (p + 2)) / 6;
							else
								count += r * ((p * (p + 1)) / 2);
						} else {
							if (j == k)
								count += p * ((q * (q + 1)) / 2);
							else if (i == k)
								count += q * ((p * (p + 1)) / 2);
							else
								count += p * q * r;
						}
					}
				}
			}
		}

		System.out.println(count);
	}
}
