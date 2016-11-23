package s400;

public class P473TODO {
	static double P = 0.5 * (1.0 + Math.sqrt(5));
	static long U;
	static long ANS;

	static long[] F = new long[60];
	static {
		F[0] = 0;
		F[1] = 1;
		for (int i = 2; i < 60; i++) {
			F[i] = F[i - 1] + F[i - 2];
		}
	}

	static void f(long integ, long frac, int last) {
		if (frac == 0 && integ <= U) {
			ANS += integ;
		}

		int m = last % 2 == 0 ? 1 : -1;

		for (int i = last + 2;; i++) {
			long integ_ = integ + F[i - 2] + m * F[i + 1];
			long frac_ = frac + F[i - 1] - m * F[i];

			if ((long) (P * frac_ + integ_) > U) {
				break;
			}

			f(integ_, frac_, i);
			m = -m;
		}
	}

	public static void main(String[] args) {
		// TODO ~2 min
		U = 10000000000L;
		f(0, 0, 0);

		System.out.println(ANS + 1);
	}
}
