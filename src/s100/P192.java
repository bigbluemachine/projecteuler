package s100;

import java.math.BigInteger;
import java.util.ArrayList;

import core.BigRatio;
import core.Ratio;

public class P192 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static ArrayList<Integer> sqrtCF(int n) {
		ArrayList<Integer> seq = new ArrayList<Integer>();
		double s = Math.sqrt(n);
		int k = 0;
		int d = 1;
		do {
			int x = (int) ((k + s) / d);
			seq.add(x);
			k = x * d - k;
			d = (n - k * k) / d;
		} while (d != 1);
		seq.add(2 * (int) s);
		return seq;
	}

	static BigRatio diff(int x, long n, long d) {
		BigRatio x2 = new BigRatio(big(x), big(1));
		BigRatio r = new BigRatio(big(n), big(d));
		r = r.mul(r);
		r = r.sub(x2);
		r.n = r.n.abs();
		return r;
	}

	static long f(int x, long limit) {
		ArrayList<Integer> cf = sqrtCF(x);

		long prevA = 1;
		long prevB = 0;
		long a = cf.get(0);
		long b = 1;

		for (int i = 1;; i++) {
			int index = (i - 1) % (cf.size() - 1) + 1;
			long mul = cf.get(index);
			long nextA = mul * a + prevA;
			long nextB = mul * b + prevB;

			if (nextB == limit) {
				return nextB;
			}

			if (nextB > limit) {
				Ratio ans = new Ratio(a, b);
				BigRatio diff = diff(x, a, b);

				long tempA = prevA;
				long tempB = prevB;
				for (;;) {
					tempA += a;
					tempB += b;
					if (tempB > limit) {
						break;
					}
					BigRatio tempDiff = diff(x, tempA, tempB);
					if (tempDiff.compareTo(diff) < 0) {
						diff = tempDiff;
						ans = new Ratio(tempA, tempB);
					}
				}

				return ans.d;
			}

			prevA = a;
			prevB = b;
			a = nextA;
			b = nextB;
		}
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int x = 2; x <= 100000; x++) {
			int s = (int) Math.sqrt(x);
			if (s * s != x) {
				ans += f(x, 1000000000000L);
			}
		}
		System.out.println(ans);
	}
}
