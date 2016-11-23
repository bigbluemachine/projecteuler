package s300;

import java.util.Arrays;
import java.util.Scanner;

import core.IOLib;

public class P345 {
	static int f(int v, int k, int[][] M, int[] F) {
		if (F[v] >= 0) {
			return F[v];
		}
		if (k == M.length) {
			return F[v] = 0;
		}
		int ans = 0;
		for (int i = 0; i < M.length; i++) {
			if ((v & (1 << i)) > 0) {
				int w = v & ~(1 << i);
				ans = Math.max(ans, M[k][i] + f(w, k + 1, M, F));
			}
		}
		return F[v] = ans;
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/345.txt");
		int[][] M = new int[15][15];
		int[] F = new int[1 << 15];
		Arrays.fill(F, -1);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				M[i][j] = in.nextInt();
			}
		}
		in.close();
		System.out.println(f((1 << 15) - 1, 0, M, F));
	}
}
