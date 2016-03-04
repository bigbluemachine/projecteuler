package s200;

public class P240 {
	static long[] F;
	static long X = 0;
	static final int SUM = 70;
	static final int NUM = 10, TOT = 20;
	static final int MAX_ROLL = 12;

	static void g(int n, int r, int[] v) {
		if (n == TOT) {
			long x = F[TOT];
			for (int i = 1; i <= MAX_ROLL; i++)
				x /= F[v[i]];
			X += x;
			return;
		}

		for (int x = r; x >= 1; x--) {
			v[x]++;
			g(n + 1, x, v);
			v[x]--;
		}
	}

	static void f(int n, int r, int s, int[] v) {
		if (n == NUM) {
			if (s == SUM)
				g(n, r, v);
			return;
		}

		int min = Math.max((SUM - s) / (NUM - n), 1);
		for (int x = r; x >= min; x--) {
			v[x]++;
			f(n + 1, x, s + x, v);
			v[x]--;
		}
	}

	public static void main(String[] args) {
		F = new long[21];
		F[0] = F[1] = 1;
		for (int i = 2; i <= 20; i++)
			F[i] = F[i - 1] * i;

		X = 0;
		f(0, MAX_ROLL, 0, new int[MAX_ROLL + 1]);

		System.out.println(X);
	}
}
