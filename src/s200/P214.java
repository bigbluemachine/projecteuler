package s200;

import core.NTLib;

public class P214 {
	public static void main(String[] args) {
		int M = 40000000;
		int N = 25;

		boolean[] P = NTLib.simpleSieve(M);
		int[] totient = NTLib.totient(M);
		int[] chain = new int[M];
		chain[1] = 1;
		chain[2] = 2;
		for (int i = 3; i < M; i += 2) {
			if (P[i]) {
				int k = i;
				int n = 2;
				while (chain[k] == 0) {
					k = totient[k];
					n++;
				}
				chain[i] = n;
			}
		}

		long ans = 0;
		for (int i = 3; i < M; i += 2) {
			if (chain[i] == N) {
				ans += i;
			}
		}
		System.out.println(ans);
	}
}
