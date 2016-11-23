package s300;

import core.MathLib;
import core.NTLib;

public class P333 {
	static int N;

	static void search(long[][] T, int i, int j, int n, int[] A) {
		A[n]++;
		for (int ii = 0; ii < i; ii++) {
			for (int jj = j + 1; jj < T[0].length; jj++) {
				if (n + T[ii][jj] < N) {
					search(T, ii, jj, (int) (n + T[ii][jj]), A);
				}
			}
		}
	}

	static int search(int n) {
		return 2;
	}

	public static void main(String[] args) {
		N = 1000000;

		long[][] T = new long[20][13];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 13; j++) {
				T[i][j] = MathLib.pow64(2, i) * MathLib.pow64(3, j);
			}
		}

		int[] A = new int[N];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 13; j++) {
				if (T[i][j] < N) {
					search(T, i, j, (int) T[i][j], A);
				}
			}
		}

		int ans = 0;
		boolean[] P = NTLib.simpleSieve(N);
		for (int i = 2; i < N; i++) {
			if (P[i] && A[i] == 1) {
				ans += i;
			}
		}
		System.out.println(ans);
	}
}
