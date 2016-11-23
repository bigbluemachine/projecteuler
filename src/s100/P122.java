package s100;

import java.util.Arrays;

public class P122 {
	public static void main(String[] args) {
		int max = 200;
		int[] m = new int[max + 1];
		Arrays.fill(m, 1000);

		int[] s = new int[15];
		s[0] = 1;
		search(s, 1, max, m);

		int ans = 0;
		for (int i = 1; i <= max; i++) {
			ans += m[i];
		}
		System.out.println(ans);
	}

	static void search(int[] s, int c, int max, int[] m) {
		if (c > 14) {
			return;
		}

		int last = s[c - 1];
		if (m[last] < c - 1) {
			return;
		}
		m[last] = c - 1;

		for (int i = 0; i < c; i++) {
			for (int j = i; j < c; j++) {
				int x = s[i] + s[j];
				if (x > last && x <= max) {
					s[c] = x;
					search(s, c + 1, max, m);
				}
			}
		}
	}
}
