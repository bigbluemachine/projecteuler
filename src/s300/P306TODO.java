package s300;

public class P306TODO {
	public static void main(String[] args) {
		// TODO ~5m
		int n = 1000000;

		int[] Z = new int[n + 1];
		for (int i = 2; i <= n; i++) {
			if (i % 1000 == 0) {
				System.out.println(i);
			}

			int t = i - 2;
			long v = 1 - t % 2;
			for (int a = 0; a + a < t; a++) {
				v |= 1 << (Z[a] ^ Z[t - a]);
			}
			for (int a = 0;; a++) {
				if ((v & (1L << a)) == 0) {
					Z[i] = a;
					break;
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (Z[i] != 0) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
