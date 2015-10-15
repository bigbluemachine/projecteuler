package s000;

import java.util.Arrays;
import java.util.Scanner;

import core.IOLib;

public class P083 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/081_matrix.txt");
		int[][] M = new int[S][S];
		for (int i = 0; i < S; i++) {
			String[] s = in.nextLine().split(",");
			for (int j = 0; j < S; j++) {
				M[i][j] = Integer.parseInt(s[j]);
			}
		}
		IOLib.close(in);

		int[][] F = new int[S][S];
		for (int[] f : F) {
			Arrays.fill(f, INF);
		}

		F[S - 1][S - 1] = M[S - 1][S - 1];

		for (int t = 0; t < S * S; t++) {
			for (int i = S - 1; i >= 0; i--) {
				for (int j = S - 1; j >= 0; j--) {
					int test = INF;
					if (i - 1 >= 0) {
						test = Math.min(test, M[i][j] + F[i - 1][j]);
					}
					if (i + 1 < S) {
						test = Math.min(test, M[i][j] + F[i + 1][j]);
					}
					if (j - 1 >= 0) {
						test = Math.min(test, M[i][j] + F[i][j - 1]);
					}
					if (j + 1 < S) {
						test = Math.min(test, M[i][j] + F[i][j + 1]);
					}
					F[i][j] = Math.min(F[i][j], test);
				}
			}
		}

		System.out.println(F[0][0]);
	}

	static int INF = 100000000;
	static int S = 80;
}
