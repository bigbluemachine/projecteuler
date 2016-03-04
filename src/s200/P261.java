package s200;

import java.util.TreeSet;

public class P261 {
	static long MAX = 10000000000L, MAX_A = 70710;
	static TreeSet<Long> PIVOTS = new TreeSet<Long>();

	static final long T(long n) {
		return (n * (n + 1)) >> 1;
	}

	static void process(long a) {
		long[] S = new long[15];
		S[0] = a;
		S[1] = (a * (a + 1)) << 2;

		long alpha = (a << 2) + 2, beta = a << 1, temp;
		int i = 2;
		for (;; i++) {
			temp = alpha * S[i - 1] - S[i - 2] + beta;
			if (temp > MAX)
				break;
			S[i] = temp;
		}

		process(S, i);
	}

	static void process(long[] S, int n) {
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				long a = S[i], b = S[j];
				if (a * b <= MAX >> 1) {
					long s = (long) Math.sqrt(T(a) * T(b));
					long x = a * (b + 1) + s + s;
					if (x <= MAX)
						PIVOTS.add(x);
				}
			}
	}

	public static void main(String[] args) {
		for (int i = 1; i <= MAX_A; i++)
			process(i);

		for (int a = 1;; a++) {
			long x = 2L * a * (a + 1);
			if (x > MAX)
				break;
			PIVOTS.add(x);
		}

		long sum = 0;
		for (long s : PIVOTS)
			sum += s;
		System.out.println(sum);
	}
}
