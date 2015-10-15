package s100;

public class P171 {
	static long[] F;
	static long X = 0;
	static final int M = 1000000000, N = 111111111;
	static final boolean[] S = new boolean[1621];

	static void q(int[] v) {
		int n = 0;
		for (int i : v)
			n += i;

		long c = F[19] / F[20 - n], s = 0;
		int x;

		for (int d = 1; d <= 9; d++) {
			if (v[d] == 0)
				continue;
			long t = c;
			for (x = 1; x < d; x++)
				t /= F[v[x]];
			if (v[d] > 1)
				t /= F[v[d] - 1];
			for (x = d + 1; x <= 9; x++)
				t /= F[v[x]];
			s += t * d;
		}

		X = (X + ((s % M) * N)) % M;
	}

	static void p(int n, int d, int s, int[] v) {
		if (n == 0) {
			if (S[s])
				q(v);
			return;
		}

		for (; d <= 9; d++) {
			v[d]++;
			p(n - 1, d, s + d * d, v);
			v[d]--;
		}
	}

	public static void main(String[] args) {
		F = new long[20];
		F[0] = F[1] = 1;
		for (int i = 2; i <= 19; i++)
			F[i] = F[i - 1] * i;

		for (int i = 1; i <= 40; i++)
			S[i * i] = true;

		X = 0;
		for (int i = 1; i <= 20; i++)
			p(i, 1, 0, new int[10]);

		System.out.println(X);
	}
}
