package s200;

public class P213 {
	static int R, C, N;
	static double[][][] F;
	static int dr, dc;

	static double f(int r, int c, int n) {
		if (F[r][c][n] >= 0) {
			return F[r][c][n];
		}

		double ans = 0;
		if ((r + c + dr + dc + n) % 2 == 0 && Math.abs(r - dr) + Math.abs(c - dc) <= n) {
			int choices = 0;
			if (r > 0) {
				choices++;
				ans += f(r - 1, c, n - 1);
			}
			if (c > 0) {
				choices++;
				ans += f(r, c - 1, n - 1);
			}
			if (r < R - 1) {
				choices++;
				ans += f(r + 1, c, n - 1);
			}
			if (c < C - 1) {
				choices++;
				ans += f(r, c + 1, n - 1);
			}
			ans /= choices;
		}

		return F[r][c][n] = ans;
	}

	static double f(int x, int y) {
		dr = x;
		dc = y;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int n = 0; n <= N; n++) {
					F[r][c][n] = -1;
				}
			}
		}

		F[dr][dc][0] = 1;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (r != dr || c != dc) {
					F[r][c][0] = 0;
				}
			}
		}

		double ans = 1;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				ans *= 1 - f(r, c, N);
			}
		}

		return ans;
	}

	static double run(int r, int c, int n) {
		R = 30;
		C = 30;
		N = 50;
		F = new double[R][C][N + 1];

		double ans = 0;
		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				ans += f(x, y);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		double ans = run(30, 30, 50);
		System.out.printf("Answer: %.7f\n", ans);
		System.out.printf("Time: %d ms\n", System.currentTimeMillis() - t);
	}
}
