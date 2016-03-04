package s200;

import java.util.LinkedList;

public class P254 {
	static int[] F = new int[10];
	static {
		F[1] = 1;
		for (int i = 2; i <= 9; i++) {
			F[i] = F[i - 1] * i;
		}
	}

	static S BEST;
	static int[] STACK = new int[20];
	static int COUNT = 0;
	static LinkedList<Long> L = new LinkedList<Long>();

	static class S {
		long len, sum;
		long[] seq;

		S(long len, long sum, long[] seq) {
			this.len = len;
			this.sum = sum;
			this.seq = seq;
		}

		boolean isBetter(S other) {
			if (len < other.len) {
				return true;
			} else if (len == other.len) {
				for (int d = 1; d <= 9; d++) {
					if (seq[d] > other.seq[d]) {
						return true;
					} else if (seq[d] < other.seq[d]) {
						return false;
					}
				}

				return false;
			} else {
				return false;
			}
		}
	}

	static S test(long v) {
		long len = 0;
		long sum = 0;
		long[] seq = new long[10];

		for (int d = 9; v > 0; d--) {
			long t = v / F[d];
			len += t;
			sum += t * d;
			seq[d] = t;
			v = v % F[d];
		}

		return new S(len, sum, seq);
	}

	// find all k-digit numbers that sum to s
	static void findAll(int k, int s, boolean leading) {
		if (k == 0) {
			if (s != 0) {
				return;
			}

			long x = 0;
			for (int i = 0; i < COUNT; i++) {
				x = 10L * x + STACK[i];
			}

			L.add(x);
			return;
		}

		if (9 * k < s) {
			return;
		}

		int min = leading ? 1 : 0;
		int max = Math.min(9, s);

		for (int d = min; d <= max; d++) {
			STACK[COUNT++] = d;
			findAll(k - 1, s - d, false);
			COUNT--;
		}
	}

	static void sweep(int sum, int digits) {
		L.clear();
		findAll(digits, sum, true);

		for (long x : L) {
			S s = test(x);
			if (s.isBetter(BEST)) {
				BEST = s;
			}
		}
	}

	static long sg(int n) {
		BEST = new S(Long.MAX_VALUE, Long.MIN_VALUE, new long[0]);

		int minDigits = (n + 8) / 9;
		sweep(n, minDigits);
		for (int i = minDigits + 1; i <= 7; i++) {
			sweep(n, i);
		}

		return BEST.sum;
	}

	public static void main(String[] args) throws Exception {
		long ans = 0;
		for (int i = 1; i <= 150; i++) {
			long sg = sg(i);
			System.out.printf("sg(%d) = %d (%d digits)\n", i, sg, BEST.len);
			ans += sg;
		}
		System.out.println(ans);
	}
}
