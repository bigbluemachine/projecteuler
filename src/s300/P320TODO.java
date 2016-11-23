package s300;

import java.math.BigInteger;
import java.util.List;

import core.NTLib;

public class P320TODO {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	static long countFac(long n, long d) {
		long result = 0;
		for (long i = d; i <= n; i *= d) {
			result += n / i;
		}
		return result;
	}

	static final long pow(int b, int e) {
		long i = 1;
		while (e-- > 0) {
			i *= b;
		}
		return i;
	}

	public static void main(String[] args) {
		// TODO 20 minutes!!
		int u = 1000000;

		List<Integer> P = NTLib.primeList(u);
		long L = 1234567890L;

		long[] A = new long[u + 1];
		for (int p : P) {
			long f = p < 9 ? L * countFac(9, p) : 0;
			long z = p + 1;
			int e = 1;

			while (z <= f) {
				z += pow(p, ++e);
			}

			int init = 10 - 10 % p;
			if (init < 10) {
				init += p;
			}

			long m = 0;
			for (int i = init; i <= u; i++) {
				if (i % p == 0) {
					int j = i;
					do {
						f += L;
					} while ((j /= p) % p == 0);

					while (z <= f) {
						z += pow(p, ++e);
					}

					long d = pow(p, e - 1);
					long c = (p * d - 1) / (p - 1);
					long g = f;

					m = 0;
					while (g > 0) {
						m += (long) (g / c) * d;
						g %= c;
						c -= d;
						d /= p;
					}
				}
				A[i] = Math.max(A[i], p * m);
			}
		}

		BigInteger sum = big(0);
		for (int i = 10; i <= u; i++) {
			sum = sum.add(big(A[i]));
		}

		System.out.println(sum.mod(big(1000000000000000000L)));
	}
}
