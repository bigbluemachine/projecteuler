package s200;

import core.MathLib;

public class P279 {
	static long gen_90(int p) {
		long ans = 0;
		int maxN = (int) Math.sqrt(p / 2);
		for (int n = 2; n <= maxN; n++) {
			for (int m = (n & 1) + 1; m < n; m += 2) {
				int a = n * n - m * m;
				int b = 2 * n * m;
				int c = n * n + m * m;
				if (a + b + c > p) {
					break;
				}
				if (MathLib.gcd32(m, n) == 1) {
					ans += p / (a + b + c);
				}
			}
		}
		return ans;
	}

	static long gen_120(int p) {
		long ans = 0;
		int maxN = (int) Math.sqrt(p / 2);
		for (int n = 2; n <= maxN; n++) {
			for (int m = 1; m < n; m++) {
				int a = n * n - m * m;
				int b = 2 * n * m + m * m;
				int c = n * n + m * m + n * m;
				if (a + b + c > p) {
					break;
				}
				if ((m - n) % 3 != 0 && MathLib.gcd32(m, n) == 1) {
					ans += p / (a + b + c);
				}
			}
		}
		return ans;
	}

	static long gen_60(int p) {
		long ans = 0;
		int maxN = (int) Math.sqrt(p / 2);
		for (int n = 2; n <= maxN; n++) {
			for (int m = 1; m < n; m++) {
				int a1 = n * n - m * m;
				int a2 = 2 * n * m + m * m;
				int b = 2 * n * m + n * n;
				int c = n * n + m * m + n * m;
				if (Math.min(a1, a2) + b + c > p) {
					break;
				}
				if ((m - n) % 3 == 0 || MathLib.gcd32(m, n) != 1) {
					continue;
				}
				if (a1 + b + c <= p) {
					ans += p / (a1 + b + c);
				}
				if (a2 + b + c <= p) {
					ans += p / (a2 + b + c);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int p = 100000000;
		long ans = gen_90(p) + gen_120(p) + gen_60(p) + p / 3;
		System.out.println(ans);
	}
}
