package s000;

public class P068 {
	static int N = 10;
	static int[] C = { 0, 5, 6, 1, 6, 7, 2, 7, 8, 3, 8, 9, 4, 9, 5 };
	static int[] A = new int[N];
	static int k = 0;
	static long ans = 0;

	static void gen(int v) {
		if (v == 0) {
			int sum = A[C[0]] + A[C[1]] + A[C[2]];
			for (int i = 3; i < C.length; i += 3) {
				int test = A[C[i]] + A[C[i + 1]] + A[C[i + 2]];
				if (test != sum) {
					return;
				}
			}

			int min = A[C[0]];
			for (int i = 3; i < C.length; i += 3) {
				min = Math.min(min, A[C[i]]);
			}
			if (min != A[C[0]]) {
				return;
			}

			String s = "";
			for (int i = 0; i < C.length; i++) {
				s += A[C[i]];
			}

			if (s.length() > 16) {
				return;
			}

			long test = Long.parseLong(s);
			ans = Math.max(ans, test);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if ((v & (1 << i)) > 0) {
				int w = v & ~(1 << i);
				A[k++] = i;
				gen(w);
				k--;
			}
		}
	}

	public static void main(String[] args) {
		gen((1 << (N + 1)) - 2);
		System.out.println(ans);
	}
}
