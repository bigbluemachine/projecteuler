package s200;

import java.math.BigDecimal;
import java.math.BigInteger;

public class P267 {
	static int N = 1000;
	static double L = Math.log(1000000000.0);

	static int f(double p) {
		double la = Math.log(1.0 - p);
		double lb = Math.log(1.0 + p + p);
		double t = N * la;
		for (int i = 0; i < N; i++) {
			t += lb - la;
			if (t >= L) {
				return i + 1;
			}
		}
		return N + 1;
	}

	static double[] get(double lo, double hi, int samples) {
		int[] z = new int[samples];
		double delta = (hi - lo) / (samples - 1);
		for (int i = 0; i < samples; i++) {
			z[i] = f(lo + i * delta);
		}
		int min = N;
		for (int i = 0; i < z.length; i++) {
			min = Math.min(min, z[i]);
		}
		int il = 0;
		for (int i = 0; i < samples; i++) {
			if (z[i] == min) {
				il = i;
				break;
			}
		}
		int ir = 0;
		for (int i = samples - 1; i >= 0; i--) {
			if (z[i] == min) {
				ir = i;
				break;
			}
		}
		return new double[] { lo + il * delta, lo + ir * delta };
	}

	static int search(int samples, int iter) {
		double[] z = { 0.0, 1.0 };
		for (int i = 0; i < iter; i++) {
			z = get(z[0], z[1], samples);
		}
		return Math.min(f(z[0]), f(z[1]));
	}

	public static void main(String[] args) {
		BigInteger[] r = { BigInteger.ONE };
		for (int i = 1; i <= N; i++) {
			BigInteger[] s = new BigInteger[i + 1];
			s[0] = BigInteger.ONE;
			for (int j = 1; j < i; j++) {
				s[j] = r[j - 1].add(r[j]);
			}
			s[i] = BigInteger.valueOf(2).pow(i);
			r = s;
		}

		int k = search(200, 200);
		BigDecimal ans = new BigDecimal(r[N - k]).divide(new BigDecimal(r[N]));
		System.out.printf("%.12f\n", ans.doubleValue());
	}
}
