package s200;

public class P201 {
	public static void main(String[] args) {
		int c = 50;
		int m = 0;
		for (int i = 0; i < c; i++) {
			int t = 100 - i;
			m += t * t;
		}

		int[][] S = new int[c + 1][m + 1];
		for (int i = 1; i <= 100; i++) {
			int x = i * i;
			for (int k = c; k >= 2; k--) {
				for (int j = m; j >= x; j--) {
					S[k][j] = S[k][j] + S[k - 1][j - x];
					if (S[k][j] > 2) {
						S[k][j] = 2;
					}
				}
			}
			S[1][x]++;
		}

		int ans = 0;
		for (int i = 1; i <= m; i++) {
			if (S[50][i] == 1) {
				ans += i;
			}
		}
		System.out.println(ans);
	}
}
