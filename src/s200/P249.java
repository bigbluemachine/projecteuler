package s200;

import java.util.List;

import core.NTLib;

public class P249 {
	public static void main(String[] args) {
		List<Integer> P = NTLib.primeList(5000);
		int max = 0;
		for (int p : P) {
			max += p;
		}

		long M = 10000000000000000L;
		long[] S = new long[max + 1];
		S[0] = 1;
		for (int p : P) {
			for (int i = max; i >= p; i--) {
				S[i] = (S[i] + S[i - p]) % M;
			}
		}

		boolean[] Q = NTLib.simpleSieve(max + 1);
		long ans = 0;
		for (int i = 2; i <= max; i++) {
			if (Q[i]) {
				ans = (ans + S[i]) % M;
			}
		}

		System.out.println(ans);
	}
}
