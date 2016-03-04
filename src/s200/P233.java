package s200;

public class P233 {
	static int M = 4733728, N = 165549, O = 300000;
	static int[] P = new int[N];
	static boolean[] C = new boolean[M], S = new boolean[O];
	static long U = 100000000000L, V = U >> 1, W = U / 125;
	static long SUM = 0;

	static boolean check(long... L) {
		long R = 1, B, E;
		for (int i = 0; i < L.length; i += 2) {
			B = L[i];
			E = L[i + 1];
			while (E-- > 0)
				if ((R *= B) > U)
					return false;
		}

		B = U / R;
		for (int i = 1; i <= B; i++)
			if (!S[i]) {
				if ((E = R * i) <= V)
					SUM += E + E;
				if ((E & 1) > 0)
					SUM += E;
			}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 2; i * i <= M; i++)
			if (!C[i])
				for (int j = i * i; j < M; j += i)
					C[j] = true;
		for (int i = 5, j = 0; i < M; i += 4)
			if (!C[i])
				for (int k = P[j++] = i; k < O; k += i)
					S[k] = true;

		long I, J, K;
		for (int i = 2; i < N; i++) {
			I = P[i];
			for (int j = 1; j < i; j++) {
				J = P[j];
				if (I * J * J > W)
					break;
				for (int k = 0; k < j; k++) {
					if (!check(I, 1, J, 2, K = P[k], 3))
						break;
					check(I, 1, K, 2, J, 3);
					check(J, 1, I, 2, K, 3);
					check(J, 1, K, 2, I, 3);
					check(K, 1, I, 2, J, 3);
					check(K, 1, J, 2, I, 3);
				}
			}
		}

		for (int i = 1; (I = P[i]) <= 101; i++)
			for (int j = 0; j < i; j++) {
				if (!check(I, 2, J = P[j], 10))
					break;
				check(J, 2, I, 10);
			}

		for (int i = 1; (I = P[i]) <= 108; i++)
			for (int j = 0; j < i; j++) {
				if (!check(I, 3, J = P[j], 7))
					break;
				check(J, 3, I, 7);
			}

		System.out.println(SUM);
	}
}
