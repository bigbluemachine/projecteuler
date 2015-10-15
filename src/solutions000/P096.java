package s000;

import java.util.Scanner;

import core.IOLib;

public class P096 {
	static class Solver {
		private static final int e = 81;
		private static boolean[][] r = new boolean[e][e];
		private static int limit, count;
		public static int[][] solutions;

		private static void init() {
			boolean[] z;
			int i, j, k, m;
			for (i = 0; i < e; i++) {
				for (z = r[i], j = m = i % 9; j < e; j += 9) {
					z[j] = true;
				}
				for (j = i - m, k = 0; k++ < 9;) {
					z[j++] = true;
				}
				j = i - (i % 27) + m - (i % 3);
				z[j] = z[j + 1] = z[j + 2] = z[j + 9] = z[j + 10] = z[j + 11] = z[j + 18] = z[j + 19] = z[j + 20] = true;
				z[i] = false;
			}
		}

		public static int solve(int[] v, int[] d) {
			int c, i, j, k, m;
			do {
				for (c = i = 0; i < e; i++) {
					if (d[i] >= 0) {
						continue;
					}
					for (d[i] = j = 0; j < e; j++) {
						if (!r[i][j]) {
							continue;
						}
						if (v[i] == v[j]) {
							return 0;
						}
						if (v[j] == 0) {
							k = d[j] &= ~(1 << v[i]);
							if ((k & (k - 1)) == 0) {
								while ((k >>= 1) > 0) {
									v[j]++;
								}
								d[j] = -1;
								c++;
							}
						}
					}
				}
			} while (c > 0);

			for (i = 0; i < e; i++) {
				if (v[i] != 0) {
					continue;
				}
				for (i = m = 0, k = 9; m < e; m++) {
					for (c = 0, j = d[m]; j > 0; c++) {
						j &= j - 1;
					}
					if (c > 1 && c < k) {
						k = c;
						i = m;
					}
				}
				k = 0;
				for (j = 1; j <= 9; j++) {
					if ((d[i] & (1 << j)) > 0) {
						int[] v_ = new int[e], d_ = new int[e];
						System.arraycopy(v, 0, v_, 0, e);
						System.arraycopy(d, 0, d_, 0, e);
						v_[i] = j;
						d_[i] = -1;
						c = solve(v_, d_);
						if (c < 0) {
							return -1;
						}
						k += c;
					}
				}
				return k;
			}
			if (count >= limit) {
				return -1;
			}
			solutions[count++] = v;
			return 1;
		}

		public static int solve(int[] board, int lim) {
			init();
			solutions = new int[limit = lim][e];
			int i = count = 0;
			int[] d = new int[81], v = new int[81];
			for (; i < e;) {
				d[i] = (v[i] = board[i++]) > 0 ? -1 : 1022;
			}
			return solve(v, d);
		}
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/096.txt");
		int ans = 0;
		while (in.hasNext()) {
			in.nextLine();
			int[] board = new int[81];
			int k = 0;
			for (int i = 0; i < 9; i++) {
				String s = in.nextLine();
				for (int j = 0; j < 9; j++) {
					board[k++] = s.charAt(j) - '0';
				}
			}
			Solver.solve(board, 1);
			int[] s = Solver.solutions[0];
			int q = 100 * s[0] + 10 * s[1] + s[2];
			ans += q;
		}
		in.close();
		System.out.println(ans);
	}
}
