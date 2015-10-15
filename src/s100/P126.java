package s100;

public class P126 {
	static long half(long a, long b, long c, long n) {
		long ans = a * b + a * c + b * c;
		return 2 * (n - 1) * (a + b + c + n - 2) + ans;
	}

	public static void main(String[] args) {
		int N = 1000;
		int L = 100000;
		int[] T = new int[L];

		for (int a = 1;; a++) {
			if (6L * a * a >= L) {
				break;
			}
			for (int b = a;; b++) {
				if (2L * b * (2L * a + b) >= L) {
					break;
				}
				for (int c = b;; c++) {
					if (2L * (a * b + a * c + b * c) >= L) {
						break;
					}
					for (int n = 1;; n++) {
						long x = half(a, b, c, n);
						if (x >= L) {
							break;
						}
						T[(int) x]++;
					}
				}
			}
		}

		for (int i = 0; i < L; i++) {
			if (T[i] == N) {
				System.out.println(2 * i);
				break;
			}
		}
	}
}
