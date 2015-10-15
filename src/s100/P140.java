package s100;

import java.util.TreeSet;

public class P140 {
	public static void main(String[] args) {
		// Prove that this works :)
		TreeSet<Long> S = new TreeSet<Long>();

		long a = 1;
		long b = 2;
		for (int i = 1; i <= 15; i++) {
			long x = a * b + 3 * a * a;
			long y = b * b - a * b - a * a;
			S.add(x / y);
			a = a + b;
			b = a + b;
		}

		a = 2;
		b = 5;
		for (int i = 1; i <= 15; i++) {
			long x = a * b + 3 * a * a;
			long y = b * b - a * b - a * a;
			S.add(x / y);
			a = a + b;
			b = a + b;
		}

		long ans = 0;
		int i = 0;
		for (long s : S) {
			ans += s;
			if (++i >= 30) {
				break;
			}
		}
		System.out.println(ans);
	}
}
