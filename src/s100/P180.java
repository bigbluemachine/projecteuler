package s100;

import java.math.BigInteger;
import java.util.TreeSet;

import core.Ratio;

public class P180 {
	static boolean valid(Ratio r) {
		return r.n < r.d && r.d <= 35;
	}

	static Ratio sqrt(Ratio r) {
		long n = (long) Math.sqrt(r.n);
		long d = (long) Math.sqrt(r.d);
		return new Ratio(n, d);
	}

	public static void main(String[] args) {
		TreeSet<Ratio> R = new TreeSet<Ratio>();
		TreeSet<Ratio> U = new TreeSet<Ratio>();

		for (int k = 2; k <= 35; k++) {
			for (int b = 2; b <= k; b++) {
				for (int a = 1; a < b; a++) {
					Ratio r = new Ratio(a, b);
					R.add(r);
					U.add(r.mul(r));
				}
			}
		}

		Ratio[] S = R.toArray(new Ratio[0]);
		R.clear();

		for (int i = 0; i < S.length; i++) {
			Ratio x = S[i];
			Ratio xi = x.inv();
			Ratio x2 = x.mul(x);
			Ratio xi2 = x2.inv();

			for (int j = 0; j < S.length; j++) {
				Ratio y = S[j];
				Ratio yi = y.inv();
				Ratio y2 = y.mul(y);
				Ratio yi2 = y2.inv();

				Ratio z;
				Ratio z2;

				z = x.add(y);
				if (valid(z)) {
					R.add(x.add(y).add(z));
				}

				z = xi.add(yi).inv();
				if (valid(z)) {
					R.add(x.add(y).add(z));
				}

				z2 = x2.add(y2);
				if (U.contains(z2)) {
					R.add(x.add(y).add(sqrt(z2)));
				}

				z2 = xi2.add(yi2).inv();
				if (U.contains(z2)) {
					R.add(x.add(y).add(sqrt(z2)));
				}
			}
		}

		BigInteger n = BigInteger.valueOf(0);
		BigInteger d = BigInteger.valueOf(1);
		for (Ratio r : R) {
			n = n.multiply(BigInteger.valueOf(r.d)).add(BigInteger.valueOf(r.n).multiply(d));
			d = d.multiply(BigInteger.valueOf(r.d));
			BigInteger g = n.gcd(d);
			n = n.divide(g);
			d = d.divide(g);
		}

		System.out.println(n.add(d));
	}
}
