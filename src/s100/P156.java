package s100;

public class P156 {
	public static void main(String[] args) {
		System.out.println(run());
	}

	static long f(long n, int d) {
		long ans = 0;
		for (long rad = 1; rad <= n; rad *= 10) {
			long k = (n - rad) / rad - d;
			long m = k < 0 ? 0 : rad * (k / 10 + 1);
			if ((n / rad) % 10 == d) {
				m += n % rad + 1;
			}
			ans += m;
		}
		return ans;
	}

	static long s(int d, long A, long B) {
		long[] I = new long[22];
		long D = (B - A) / 10;
		for (int i = 0; A <= B; A += D) {
			long U = Math.min(A + D - 1, B);
			I[i++] = Math.max(A, f(A, d));
			I[i++] = Math.min(U, f(U, d));
		}

		long sum = 0;
		for (int i = 0; i < 22;) {
			long min = I[i++], max = I[i++], diff = max - min;
			if (diff < 0) {
				continue;
			}
			if (diff < 100) {
				for (long n = min; n <= max; n++) {
					if (f(n, d) == n) {
						sum += n;
					}
				}
			} else {
				sum += s(d, min, max);
			}
		}
		return sum;
	}

	static long run() {
		long sum = 0;
		for (int d = 1; d <= 9; d++) {
			sum += s(d, 1, 1000000000000L);
		}
		return sum;
	}
}
