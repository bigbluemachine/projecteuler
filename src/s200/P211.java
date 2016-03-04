package s200;

import java.util.Arrays;

public class P211 {
	public static void main(String[] args) {
		int M = 64000000;

		long[] q = new long[M];
		Arrays.fill(q, 1);
		for (int i = 2; i < M; i++) {
			for (int j = i; j < M; j += i) {
				q[j] += (long) i * i;
			}
		}

		long ans = 0;
		for (int n = 1; n < M; n++) {
			long s = (long) Math.sqrt(q[n]);
			if (s * s == q[n]) {
				ans += n;
			}
		}
		System.out.println(ans);
	}
}
