package s000;

import java.util.Arrays;
import java.util.TreeSet;

public class P088 {
	static long[] F;

	static void search(int[] a, int n, long p, long s) {
		if (n > 1 && p == s) {
			if (F[n] == -1) {
				F[n] = p;
			} else {
				F[n] = Math.min(F[n], p);
			}
			return;
		}

		if (n == a.length) {
			return;
		}

		int next = n == 0 ? 2 : Math.max(2, a[n - 1]);
		for (int i = next; i + i <= a.length; i++) {
			if (p * i > s + i) {
				break;
			}
			a[n] = i;
			search(a, n + 1, p * i, s + i);
		}
	}

	public static void main(String[] args) {
		int max = 12000;
		int[] a = new int[max];
		F = new long[max + 1];
		Arrays.fill(F, -1);
		for (int n = 0; n < max; n++) {
			Arrays.fill(a, 1);
			search(a, n, 1, n);
		}

		TreeSet<Long> S = new TreeSet<Long>();
		for (int i = 2; i <= max; i++) {
			S.add(F[i]);
		}

		long ans = 0;
		for (long s : S) {
			ans += s;
		}
		System.out.println(ans);
	}
}
