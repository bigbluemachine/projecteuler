package s300;

import java.util.TreeSet;

public class P346 {
	public static void main(String[] args) {
		long M = 1000000000000L;
		TreeSet<Long> R = new TreeSet<Long>();
		for (long b = 2; b * b <= M; b++) {
			TreeSet<Long> T = new TreeSet<Long>();
			long x = b + 1;
			while (true) {
				x = b * x + 1;
				if (x >= M) {
					break;
				}
				T.add(x);
			}
			for (long t : T) {
				R.add(t);
			}
		}

		long ans = 1;
		for (long r : R) {
			ans += r;
		}
		System.out.println(ans);
	}
}
