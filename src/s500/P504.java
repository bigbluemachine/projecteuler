package s500;

import core.MathLib;

public class P504 {
	public static void main(String[] args) {
		int m = 100;
		int[][] g = new int[m + 1][m + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
				g[i][j] = MathLib.gcd32(i, j);
			}
		}

		int ans = 0;
		for (int a = 1; a <= m; a++) {
			for (int b = 1; b <= m; b++) {
				for (int c = 1; c <= m; c++) {
					for (int d = 1; d <= m; d++) {
						int b2 = g[a][b] + g[b][c] + g[c][d] + g[d][a];
						int a2 = (a + c) * (b + d);
						int i2 = a2 - b2 + 2;
						int i = i2 / 2;
						int s = (int) Math.sqrt(i);
						if (s * s == i) {
							ans++;
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
}
