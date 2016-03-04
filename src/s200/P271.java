package s200;

import java.math.BigInteger;
import java.util.ArrayList;

import core.EEA;

public class P271 {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	public static void main(String[] args) {
		int[] x = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43 };
		int k = x.length;
		boolean[][] a = new boolean[k][];

		for (int i = 0; i < k; i++) {
			int m = x[i];
			boolean[] b = new boolean[m];
			for (int j = 0; j < m; j++)
				if ((j * j * j) % m == 1)
					b[j] = true;
			a[i] = b;
		}

		ArrayList<Long> u = new ArrayList<Long>();
		ArrayList<Long> v = new ArrayList<Long>();
		ArrayList<Long> w = new ArrayList<Long>();
		for (int j = 0; j < a[0].length; j++)
			if (a[0][j])
				v.add(0L + j);

		long M = x[0];
		for (int i = 1; i < k; i++) {
			w.clear();
			for (int j = 0; j < a[i].length; j++)
				if (a[i][j])
					w.add(0L + j);

			u.clear();
			for (Long p : v)
				for (Long q : w)
					u.add(EEA.crt(M, p, x[i], q).longValue());
			v.clear();
			v.addAll(u);

			M *= x[i];
		}

		BigInteger result = big(0);
		for (Long z : u)
			result = result.add(big(z));

		System.out.println(result.subtract(big(1)));
	}
}
