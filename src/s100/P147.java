package s100;

public class P147 {
	static int f(int W, int H) {
		int sum = (W * (W + 1) * H * (H + 1)) / 4;
		int T = W + H - 2, M = H + H - 1, F = 2, S = 1;
		int i, j, k;
		for (i = 1; i <= H; M -= 2, T -= S, F = 3 - F, S = 4 - S, i++) {
			if (F > M)
				return sum + T;
			int[] A = new int[T];
			for (j = 0, k = F; k <= M; k += 2, j++)
				A[j] = A[T - j - 1] = k;
			for (; j + j < T; j++)
				A[j] = A[T - j - 1] = M;
			for (int a : A)
				sum += a * a;
		}
		return sum;
	}

	public static void main(String[] args) {
		int n = 47, m = 43, sum = 0;
		for (int w = n; w >= 1; w--)
			for (int h = m; h >= 1; h--)
				sum += w >= h ? f(w, h) : f(h, w);
		System.out.println(sum);
	}
}
