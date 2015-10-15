package s000;

public class P061 {
	static int[] stack = new int[6];
	static int count = 0;

	static void search(int[][] v, int m, int last) {
		if (m == 0) {
			if (stack[0] / 100 == stack[count - 1] % 100) {
				int ans = 0;
				for (int i = 0; i < count; i++) {
					ans += stack[i];
				}
				System.out.println(ans);
			}
			return;
		}
		for (int i = 0; i < 6; i++) {
			if ((m & (1 << i)) == 0) {
				continue;
			}
			int l = v[i][0];
			for (int j = 1; j <= l; j++) {
				int next = v[i][j];
				if (next / 100 == last % 100) {
					stack[count++] = next;
					search(v, m & ~(1 << i), next);
					count--;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] v = new int[6][100];
		for (int n = 1; n < 150; n++) {
			int[] w = { (n * (n + 1)) / 2, n * n, (n * (3 * n - 1)) / 2, n * (2 * n - 1), (n * (5 * n - 3)) / 2,
					n * (3 * n - 2) };
			for (int i = 0; i < 6; i++) {
				if (1000 <= w[i] && w[i] <= 9999) {
					v[i][0]++;
					v[i][v[i][0]] = w[i];
				}
			}
		}

		int mask = 1 << 6;
		mask--;
		mask &= ~(1 << 0);

		for (int i = 1; i <= v[0][0]; i++) {
			count = 1;
			stack[0] = v[0][i];
			search(v, mask, v[0][i]);
		}
	}
}
