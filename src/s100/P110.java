package s100;

import java.math.BigInteger;
import java.util.Arrays;

import core.ArrayLib;
import core.PList;

// 1/x + 1/y = 1/n
// => n(x + y) = xy
// => ...
// => k(x - n) = n^2 where k = y - n
// f(n) = 1/2 * d(n^2) + 1 where d is the number of divisors
// Initial solution: n = product of first 14 primes
public class P110 {
	static int D = 4000000;
	static int Z = 14;
	static int[] P = new int[Z];
	static int[] bestV;
	static BigInteger bestP;

	static long divSquared(int[] v) {
		long ans = 1;
		for (int i : v) {
			ans *= 2 * i + 1;
		}
		return ans;
	}

	static BigInteger product(int[] v) {
		BigInteger ans = BigInteger.ONE;
		for (int i = 0; i < v.length; i++) {
			ans = ans.multiply(BigInteger.valueOf(P[i]).pow(v[i]));
		}
		return ans;
	}

	static void search(int[] v) {
		BigInteger p = product(v);
		long d = divSquared(v);

		if (v.length == Z || d / 2 + 1 > D) {
			if (p.compareTo(bestP) < 0) {
				bestV = v;
				bestP = p;
			}
			return;
		}

		for (int i = 1; i <= v[v.length - 1]; i++) {
			BigInteger q = p.multiply(BigInteger.valueOf(P[v.length]).pow(i));
			if (p.compareTo(q) > 0) {
				break;
			}

			int[] w = ArrayLib.concat(v, new int[] { i });
			search(w);
		}
	}

	public static void main(String[] args) {
		PList ps = new PList();
		for (int i = 0; i < Z; i++) {
			P[i] = ps.get(i);
		}

		bestP = product(ArrayLib.repeat(1, Z));
		for (int i = 2; i <= 32; i++) {
			search(new int[] { i });
		}

		System.out.println(product(bestV));
		System.out.println(Arrays.toString(bestV));
	}
}
