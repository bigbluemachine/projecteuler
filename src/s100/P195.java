package s100;

import java.util.Arrays;

import core.NTLib;

public class P195 {
	public static void main(String[] args) {
		int r = 1053779;

		int u = (int) (Math.sqrt(12) * r);

		int[] k = new int[u];
		Arrays.fill(k, 1);
		for (int x = 9; x < u; x += x << 3) {
			for (int y = x; y < u; y += x) {
				k[y] *= 3;
			}
		}
		for (int x = 2; x < u; x <<= 2) {
			for (int y = x; y < u; y += x) {
				k[y] *= 2;
			}
		}

		long ans = 0;

		boolean[] p = NTLib.simpleSieve(u);
		for (int i = 5; i < u; i++) {
			if (p[i]) {
				long q2 = (long) i * i;
				for (long x = i; x < u && x > 0; x *= q2) {
					for (long y = x; y < u; y += x) {
						k[(int) y] *= i;
					}
				}
			}
		}

		for (int i = u - 4; i-- > 1;)
			ans += (u - i) / (3 * k[i]);

		System.out.println(ans);
	}
}
