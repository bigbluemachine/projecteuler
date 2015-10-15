package s100;

import java.util.Arrays;
import java.util.List;

import core.MathLib;
import core.NTLib;

public class P127TODO {
	// TODO slow
	public static void main(String[] args) {
		int M = 120000;

		int[] R = new int[M];
		Arrays.fill(R, 1);
		List<Integer> P = NTLib.primeList(M);
		for (int p : P) {
			for (int i = p; i < M; i += p) {
				R[i] *= p;
			}
		}

		long ans = 0;
		for (int c = 3; c < M; c++) {
			if ((long) R[c - 1] * R[c] < c) {
				ans += c;
			}
			if (6 * R[c] >= c) {
				continue;
			}
			int t = c % 2 == 0 ? 2 : 1;
			for (int a = 3 - c % 2; a < c - a; a += t) {
				if (MathLib.gcd32(a, c % a) != 1) {
					continue;
				}
				if ((long) R[a] * R[c - a] * R[c] < c) {
					ans += c;
				}
			}
		}
		System.out.println(ans);
	}
}
