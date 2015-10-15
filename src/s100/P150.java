package s100;

public class P150 {
	static long[][] gen(int n) {
		long t = 0;

		long[][] a = new long[n][];
		for (int i = 0; i < n; i++) {
			a[i] = new long[i + 1];

			for (int j = 0; j <= i; j++) {
				t = (615949 * t + 797807) % (1 << 20);
				long s = t - (1 << 19);

				a[i][j] = s;
			}
		}

		return a;
	}

	static long[][] sum(long[][] a, int n) {
		long[][] s = new long[n][];

		for (int i = n - 1; i >= 0; i--) {
			s[i] = new long[i + 1];

			long sum = 0;
			for (int j = i; j >= 0; j--) {
				sum += a[i][j];
				s[i][j] = sum;
			}
		}

		return s;
	}

	static long best(long[][] a, long[][] s, int n, int i, int j) {
		long ans = a[i][j];
		long temp = ans;

		for (int ii = i + 1, jj = j + 2; ii < n; ii++, jj++) {
			temp += s[ii][j];
			if (jj <= ii) {
				temp -= s[ii][jj];
			}

			ans = Math.min(ans, temp);
		}

		return ans;
	}

	public static void main(String[] args) {
		int n = 1000;
		long[][] a = gen(n);
		long[][] s = sum(a, n);

		long ans = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				ans = Math.min(ans, best(a, s, n, i, j));
			}
		}

		System.out.println(ans);
	}
}
